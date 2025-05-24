package com.jdong.lab.java.input_output.java11_convenience_file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class ConvenienceFileExample {

  /**
   * 파일을 읽어 문자열로 반환
   */
  public String readText(Path path) throws IOException {
    // default : StandardCharsets.UTF_8
    return Files.readString(path);
  }

  /**
   * 문자열을 파일에 기록 (덮어쓰기)
   */
  public void writeText(Path path, String content, StandardOpenOption... openOptions) throws IOException {
    Files.createDirectories(path.getParent());
    Files.writeString(path, content, StandardCharsets.UTF_8, openOptions);
  }

  /**
   * 파일 삭제
   */
  public void deleteFile(Path path) throws IOException {
    Files.deleteIfExists(path);
  }

  /**
   * 파일 이동
   */
  public void moveFile(Path src, Path dest) throws IOException {
    Files.createDirectories(dest.getParent());
    Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
  }
}
