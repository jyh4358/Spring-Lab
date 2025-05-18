package com.jdong.lab.java.input_output.java7_async_file;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncFileExampleTest {
  private final Path file = Paths.get("src/test/resources/async_file_test/data.txt");
  private final Path targetFile =
      Paths.get("src/test/resources/async_file_test/async-target-file.txt");
  private final Path movedFile =
      Paths.get("src/test/resources/async_file_test/async-move-file.txt");
  private final AsyncFileExample example = new AsyncFileExample();

  private static final int BUFFER_SIZE = 4 * 1024;

  @BeforeEach
  void setUp() throws Exception {
    Files.createDirectories(file.getParent());
    Files.writeString(file, "Async 파일", StandardCharsets.UTF_8);
  }

  /**
   * 참고로 쓰기와 읽기를 버퍼만큼 쓰고, 읽을려면 추가 로직이 필요하다. (position 계산 및 루프 추가)
   */
  @DisplayName("Asyc 파일 쓰기, 읽기 테스트")
  @Test
  void testReadAndWriteAsync() throws Exception {
    // 덮어 쓰기
    // 이어 쓰기의 경우 서비스 로직을 변경해야한다(주석 보면됩니다). StandardOpenOption.APPEND 는 내부에서 예외가 발생한다.
    String writeText = "Async file write";
    ByteBuffer writeBuf = ByteBuffer.wrap(writeText.getBytes(StandardCharsets.UTF_8));
    Future<Integer> wf = example.writeAsync(file, writeBuf, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    int bytesWritten = wf.get();
    assertEquals(bytesWritten, writeText.getBytes(StandardCharsets.UTF_8).length);

    ByteBuffer readBuf = ByteBuffer.allocate(BUFFER_SIZE);
    Future<Integer> rf = example.readAsync(file, readBuf);
    int bytesRead = rf.get();
    assertEquals(bytesRead, writeText.getBytes(StandardCharsets.UTF_8).length);

    if (bytesRead > 0) {
      // 6) 읽은 바이트만큼 버퍼 읽기 모드로 전환
      readBuf.flip();
      // 7) UTF-8 디코딩 → 문자열로 변환
      String content = StandardCharsets.UTF_8.decode(readBuf).toString();
      // 8) 화면에 출력
      System.out.println("읽은 바이트 수: " + bytesRead);
      System.out.println("파일 내용:\n" + content);
    } else {
      System.out.println("파일이 비어 있거나 읽을 수 없습니다.");
    }
  }

  @DisplayName("Asyc 파일 이동, 삭제 테스트")
  @Test
  void testDeleteAndMove() throws Exception {
    example.moveFile(file, movedFile);
    assertFalse(Files.exists(file));
    assertTrue(Files.exists(movedFile));
    example.deleteFile(movedFile);
    assertFalse(Files.exists(movedFile));
  }
}