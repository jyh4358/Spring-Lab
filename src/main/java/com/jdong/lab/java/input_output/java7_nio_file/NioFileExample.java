package com.jdong.lab.java.input_output.java7_nio_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class NioFileExample {

  /**
   * 파일을 대상 경로로 복사합니다.
   *
   * <p>StandardCopyOption:
   * <ul>
   *   <li>{@code REPLACE_EXISTING} - 대상 파일이 이미 존재하면 덮어씁니다 (기본 동작은 예외 발생).</li>
   *   <li>{@code COPY_ATTRIBUTES} - 원본 파일의 속성(타임스탬프, POSIX 권한 등)을 복사합니다.</li>
   *   <li>{@code ATOMIC_MOVE} - 원자적(atomic) 이동을 시도합니다. 파일 시스템이 지원하지 않을 경우 예외가 발생합니다.</li>
   * </ul>
   *
   * @param file 원본 파일 경로
   * @param targetFile 대상 파일 경로
   * @throws IOException 입출력 오류가 발생한 경우
   */
  public void copyFile(Path file, Path targetFile) throws IOException {
    Files.createDirectories(targetFile.getParent());
    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);

    // 아래와 같이 직접 파일 스트림을 사용하는 경우 try-with-resources를 사용해야 한다.
    // 직접 연 스트림은 try-with-resources로 관리
//    try (InputStream in = Files.newInputStream(file, StandardOpenOption.READ);
//        OutputStream out = Files.newOutputStream(targetFile,
//            StandardOpenOption.CREATE,
//            StandardOpenOption.TRUNCATE_EXISTING)) {
//      // JDK 9+ 메서드: in.transferTo(out);
//      byte[] buffer = new byte[8 * 1024];
//      int read;
//      while ((read = in.read(buffer)) != -1) {
//        out.write(buffer, 0, read);
//      }
//    }
  }


  /**
   * 문자열 콘텐츠를 파일에 작성합니다.
   *
   * <p>{@link StandardOpenOption} 예시:
   * <ul>
   *   <li>{@code CREATE} - 파일이 없으면 새로 생성합니다.</li>
   *   <li>{@code TRUNCATE_EXISTING} - 파일이 이미 존재하면 파일 내용을 초기화한 뒤 새로 작성합니다.</li>
   *   <li>{@code CREATE} {@code TRUNCATE_EXISTING} - 파일이 없으면 새로 생성, 파일이 이미 존재하면 파일 내용을 초기화한 뒤 새로 작성합니다.</li>
   * </ul>
   * <p>{@link StandardOpenOption} 값:
   * <ul>
   *   <li>{@code APPEND} - 기존 파일의 끝에 데이터를 추가합니다.</li>
   *   <li>{@code CREATE} - 파일이 없으면 새로 생성합니다.</li>
   *   <li>{@code CREATE_NEW} - 파일이 없을 때만 새로 생성합니다. 파일이 이미 존재하면 예외가 발생합니다.</li>
   *   <li>{@code DELETE_ON_CLOSE} - 파일을 닫을 때 삭제합니다.</li>
   *   <li>{@code DSYNC} - 데이터를 원본 스토리지에 동기화하며, OS 캐시에 저장하지 않습니다.</li>
   *   <li>{@code READ} - 파일을 읽기 전용으로 엽니다.</li>
   *   <li>{@code SPARSE} - 희소 파일(sparse file)을 생성하려는 힌트를 제공합니다.</li>
   *   <li>{@code SYNC} - 모든 데이터와 메타 데이터를 디스크로 동기화합니다.</li>
   *   <li>{@code TRUNCATE_EXISTING} - 파일이 이미 존재하면 길이를 0으로 설정한 뒤 데이터를 새로 작성합니다.</li>
   *   <li>{@code WRITE} - 파일을 쓰기 전용으로 엽니다.</li>
   * </ul>
   * @param path 파일 경로
   * @param content 파일에 작성할 문자열 콘텐츠
   * @param openOptions 파일 작성 시 사용할 {@link StandardOpenOption} 배열
   * @throws IOException 입출력 오류가 발생한 경우
   */
  public void writeFile(Path path, String content, StandardOpenOption... openOptions)
      throws IOException {
    Files.createDirectories(path.getParent());
    Files.writeString(path, content, openOptions);
  }

  /**
   * 파일을 삭제합니다 (존재하지 않을 경우 예외는 발생하지 않습니다).
   *
   * @param path 삭제할 파일 경로
   * @throws IOException 입출력 오류가 발생한 경우
   */
  public void deleteFile(Path path) throws IOException {
    Files.deleteIfExists(path);
  }

  /**
   * 파일을 다른 디렉토리로 이동하거나 이름을 변경합니다.
   *
   * <p>StandardCopyOption:
   * <ul>
   *   <li>{@code REPLACE_EXISTING} - 대상 파일이 이미 존재하면 덮어씁니다 (기본 동작은 예외 발생).</li>
   *   <li>{@code COPY_ATTRIBUTES} - 원본 파일의 속성(타임스탬프, POSIX 권한 등)을 복사합니다.</li>
   *   <li>{@code ATOMIC_MOVE} - 원자적(atomic) 이동을 시도합니다. 파일 시스템이 지원하지 않을 경우 예외가
   * </ul>
   *
   * @param file 원본 파일 경로
   * @param targetFile 대상 디렉토리 또는 파일 경로
   * @throws IOException 입출력 오류가 발생한 경우
   */
  public void moveFile(Path file, Path targetFile) throws IOException {
    Files.createDirectories(targetFile.getParent());
    Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
  }
}
