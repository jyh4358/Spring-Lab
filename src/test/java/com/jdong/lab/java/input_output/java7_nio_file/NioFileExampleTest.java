package com.jdong.lab.java.input_output.java7_nio_file;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NioFileExampleTest {
  private final Path file = Paths.get("src/test/resources/nio_file_test/data.txt");
  private final Path targetFile = Paths.get("src/test/resources/nio_file_test/nio-target-file.txt");
  private final Path movedFile = Paths.get("src/test/resources/nio_file_test/nio-move-file.txt");
  private final NioFileExample example = new NioFileExample();

  private static final String INIT_FILE_CONTENT = "NIO 파일";

  @BeforeEach
  public void fileSetUp() throws Exception {
    Files.createDirectories(file.getParent());
    example.writeFile(file, INIT_FILE_CONTENT, StandardOpenOption.CREATE);
  }

  @DisplayName("NIO 파일 복사 테스트")
  @Test
  void copyFileTest() throws IOException {
    example.copyFile(file, targetFile);
    assertArrayEquals(
        Files.readAllBytes(file),
        Files.readAllBytes(targetFile)
    );
  }

  @DisplayName("파일 쓰기, 삭제 테스트")
  @Test
  void testWriteAndDelete() throws Exception {
    example.writeFile(targetFile, INIT_FILE_CONTENT, StandardOpenOption.CREATE);
    String result = Files.readString(targetFile, StandardCharsets.UTF_8);
    assertEquals(INIT_FILE_CONTENT, result);

    // 파일 이어 쓰기
    final String appendText = " 이어쓰기";
    example.writeFile(targetFile, appendText, StandardOpenOption.APPEND);
    String appendResult = Files.readString(targetFile, StandardCharsets.UTF_8);
    assertEquals(INIT_FILE_CONTENT + appendText, appendResult);


    // 덮어 쓰기
    final String newText = "덮어 쓰기";
    example.writeFile(targetFile, newText, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    String writeResult = Files.readString(targetFile, StandardCharsets.UTF_8);
    assertEquals(newText, writeResult);

    example.deleteFile(targetFile);
    assertFalse(Files.exists(targetFile));
  }

  @DisplayName("파일 이동 테스트")
  @Test
  void testMoveFile() throws Exception {
    example.moveFile(file, movedFile);
    assertFalse(Files.exists(file));
    assertTrue(Files.exists(movedFile));
  }
}