package com.jdong.lab;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles("local")
public class FileDownloadAndSaveTest {

  @Autowired
  WebClient webClient;

  private final Path baseDir = Paths.get("/Users/younho/Desktop/data/files").toAbsolutePath().normalize();

  private static final String FILE_URL ="https://www.g2b.go.kr/pn/pnp/pnpe/UntyAtchFile/downloadFile.do?bidPbancNo=R25BK00823651&bidPbancOrd=000&fileType=&fileSeq=1";

  @Test
  void 다운로드_테스트() {

    Mono<Path> pathMono = webClient.get()
        .uri(FILE_URL)
        .accept(MediaType.APPLICATION_OCTET_STREAM)
        // Mono<ResponseSpec> → Mono<Path>
        .exchangeToMono(response -> {
          // 헤더에서 파일명 추출 로직
          HttpHeaders headers = response.headers().asHttpHeaders();
          String filename = resolveFileName(headers, FILE_URL);
          String ext = StringUtils.getFilenameExtension(filename);
          Path target = resolvePath(ext, filename);

          // 본문을 DataBuffer 스트림으로 받고
          Flux<DataBuffer> body = response.body(BodyExtractors.toDataBuffers());

          // 1) 파일에 스트리밍 쓰기
          // 2) 모든 청크가 완료되면 target Path 리턴
          return DataBufferUtils.write(body, target,
                  StandardOpenOption.CREATE,
                  StandardOpenOption.WRITE)
              // (선택) 각 버퍼의 바이트 수를 보고 싶다면:
              // .map(db -> (long) db.readableByteCount())
              // .reduce(Long::sum)
              // .map(totalBytes -> target)

              // 단순히 완료 시점에 Path를 리턴
              .thenReturn(target);
        });
    StepVerifier.create(pathMono)
        .assertNext(path -> assertTrue(Files.exists(path)))
        .expectComplete()
        .verify(Duration.ofSeconds(10));
  }

  // Content-Disposition 헤더 우선, 없으면 URL 경로에서 추출
  private String resolveFileName(HttpHeaders headers, String fileUrl) {
    String cd = headers.getFirst(HttpHeaders.CONTENT_DISPOSITION);
    if (cd != null && cd.contains("filename=")) {
      return cd.split("filename=")[1].trim().replace("\"", "");
    }
    String path = URI.create(fileUrl).getPath();
    String name = StringUtils.getFilename(path);
    if (name != null && name.contains(".")) {
      return name;
    }
    return UUID.randomUUID() + ".bin";
  }

  // /{baseDir}/{ext}/{yyyy}/{MM}/{dd}/{filename}
  private Path resolvePath(String type, String filename) {
    LocalDate d = LocalDate.now();
    Path dir = baseDir
        .resolve(type)
        .resolve(String.valueOf(d.getYear()))
        .resolve(String.format("%02d", d.getMonthValue()))
        .resolve(String.format("%02d", d.getDayOfMonth()));
    try {
      Files.createDirectories(dir);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    return dir.resolve(filename);
  }
}
