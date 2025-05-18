package com.jdong.lab.java.input_output.java8_stream_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 파일 처리와 관련된 유틸리티 클래스.
 * <p>파일 내용 읽기, 쓰기, 삭제, 이동과 같은 작업을 수행합니다.</p>
 */
public class StreamFileExample {

  /**
   * 파일의 모든 줄을 읽어 반환합니다.
   *
   * @param path 읽을 파일의 경로
   * @return 공백이 아닌 줄들로 이루어진 문자열 리스트
   * @throws IOException 파일 접근 중 오류가 발생한 경우 예외 발생
   */
  public List<String> readLines(Path path) throws IOException {
    // StandardCharsets default - UTF_8
    try (Stream<String> lines = Files.lines(path)) {
      return lines.collect(Collectors.toList());
    }
  }

  /**
   * 문자열 리스트를 파일에 라인 단위로 씁니다.
   * <p>파일이 존재하지 않는 경우 새로 생성되며, 지정된 옵션에 따라 작성 방식이 달라집니다.</p>
   *
   * @param path 파일을 작성할 경로
   * @param lines 파일에 기록할 문자열 리스트(라인 단위), List 이외에 다양한 메서드 제공한다.
   * @param openOptions 쓰기 작업 옵션 (예: 파일 열기 모드). 생략 시 기본값 사용.
   *                    <ul>
   *                      <li>{@link StandardOpenOption#CREATE}: 파일이 존재하지 않으면 새로 생성.</li>
   *                      <li>{@link StandardOpenOption#APPEND}: 파일 끝에 데이터를 추가.</li>
   *                      <li>{@link StandardOpenOption#TRUNCATE_EXISTING}: 기존 파일 내용을 지우고 새로 작성.</li>
   *                      <li>{@link StandardOpenOption#WRITE}: 파일을 작성 모드로 엽니다 (기본값).</li>
   *                    </ul>
   * @throws IOException 디렉터리 생성 및 쓰기 작업 중 오류가 발생한 경우 예외 발생
   */
  public void writeLines(Path path, List<String> lines, StandardOpenOption... openOptions) throws IOException {
    Files.createDirectories(path.getParent());
    // StandardCharsets default - UTF_8
    Files.write(path, lines, openOptions);
  }

  /**
   * 지정된 파일을 삭제합니다.
   * <p>파일이 존재하지 않는 경우 작업이 무시됩니다.</p>
   *
   * @param path 삭제할 파일의 경로
   * @throws IOException 파일 삭제 작업 중 오류가 발생한 경우 예외 발생
   */
  public void deleteFile(Path path) throws IOException {
    Files.deleteIfExists(path);
  }

  /**
   * 파일을 지정된 경로로 이동합니다.
   * <p>이동 중 대상 경로의 디렉터리가 없으면 자동으로 생성됩니다.
   * 파일이 이미 존재하는 경우 덮어씁니다.</p>
   *
   * StandardCopyOption 옵션:
   * <ul>
   *   <li>{@link StandardCopyOption#REPLACE_EXISTING}: 대상 파일이 이미 존재할 경우 덮어쓰기.</li>
   *   <li>{@link StandardCopyOption#ATOMIC_MOVE}: 원자적(Atomic) 이동으로 파일 상태의 일관성을 유지.</li>
   * </ul>
   *
   * @param src 이동할 소스 파일의 경로
   * @param dest 파일이 이동될 대상 경로
   * @throws IOException 디렉터리 생성 및 파일 이동 작업 중 오류가 발생한 경우 예외 발생
   * @see StandardCopyOption
   */
  public void moveFile(Path src, Path dest) throws IOException {
    Files.createDirectories(dest.getParent());
    Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
  }
}