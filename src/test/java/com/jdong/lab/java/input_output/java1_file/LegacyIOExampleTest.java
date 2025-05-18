package com.jdong.lab.java.input_output.java1_file;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LegacyIOExampleTest {

  private final File file = new File("src/test/resources/java1_legacy_file_test/data.txt");
  private final File tartgetFile = new File("src/test/resources/java1_legacy_file_test/target-file.txt");
  private final File movedFile = new File("src/test/resources/java1_legacy_file_test/move-file.txt");
  private final LegacyIOExample example = new LegacyIOExample();

  @BeforeEach
  public void fileSetUp() throws IOException {
    // 리소스 디렉터리가 없을 경우 생성
    boolean isSuccess = file.getParentFile().mkdirs();
    if (!isSuccess) {
      throw new SecurityException();
    }


    // 파일이 없을 경우 생성
    if (!file.exists()) {
      boolean isFileSuccess = file.createNewFile();
      if (!isFileSuccess) {
        throw new IOException();
      }
    }
    if (!tartgetFile.exists()) {
      boolean isFileSuccess = tartgetFile.createNewFile();
      if (!isFileSuccess) {
        throw new IOException();
      }
    }
    Files.writeString(file.toPath(), "테스트 데이터");
  }

  @DisplayName("파일 복사 테스트")
  @Test
  public void testCopyFile() throws IOException {
    example.copyFile(file, tartgetFile);
    assertArrayEquals(
        Files.readAllBytes(file.toPath()),
        Files.readAllBytes(tartgetFile.toPath())
    );
  }

  @DisplayName("파일 이어쓰기, 덮어쓰기 테스트")
  @Test
  void testWriteAndAppend() throws IOException {
    // 파일 쓰기
    final String text = "파일 문자열 쓰기";
    example.writeFile(tartgetFile, text, false);
    String result = Files.readString(tartgetFile.toPath(), StandardCharsets.UTF_8);
    assertEquals(text, result);

    // 파일 이어 쓰기
    final String appendText = " 이어 쓰기";
    example.writeFile(tartgetFile, appendText, true);
    String appendResult = Files.readString(tartgetFile.toPath(), StandardCharsets.UTF_8);
    assertEquals(text + appendText, appendResult);

    // 파일 덮어 쓰기
    final String writeText = "파일 덮어 쓰기";
    example.writeFile(tartgetFile, writeText, false);
    String writeResult = Files.readString(tartgetFile.toPath(), StandardCharsets.UTF_8);
    assertEquals(writeResult, writeText);
  }

  @DisplayName("파일 이동, 삭제 테스트")
  @Test
  void testMoveAndDelete() {
    assertTrue(example.moveFile(file, movedFile), "이동에 성공해야 합니다");
    assertFalse(file.exists());
    assertTrue(movedFile.exists());
    assertTrue(example.deleteFile(movedFile), "삭제에 성공해야 합니다");
    assertFalse(movedFile.exists());
  }

}