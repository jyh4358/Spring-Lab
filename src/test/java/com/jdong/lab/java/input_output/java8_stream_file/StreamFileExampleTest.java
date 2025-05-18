package com.jdong.lab.java.input_output.java8_stream_file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StreamFileExampleTest {

  private final Path file = Paths.get("src/test/resources/stream_file_test/data.txt");
  private final Path targetFile =
      Paths.get("src/test/resources/stream_file_test/stream-target-file.txt");
  private final Path movedFile =
      Paths.get("src/test/resources/stream_file_test/stream-move-file.txt");
  private final StreamFileExample example = new StreamFileExample();

  private static final List<String> INIT_FILE_CONTENT =
      new ArrayList<>(List.of("STREAM 파일 ", "두 번째 라인 ", ""));

  @BeforeEach
  void setUp() throws Exception {
    Files.createDirectories(file.getParent());
    example.writeLines(file, INIT_FILE_CONTENT, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }

  @DisplayName("STREAM 파일 읽기 테스트")
  @Test
  void testReadLines() throws Exception {
    List<String> lines = example.readLines(file);
    assertEquals(INIT_FILE_CONTENT, lines);
  }

  @DisplayName("STREAM 파일 쓰기/삭제 테스트")
  @Test
  void testWriteAndDelete() throws Exception {

    // 덮어 쓰기
    List<String> writeTestList = List.of("첫 번째 라인", "두 번째 라인", "새 번째 라인");
    example.writeLines(targetFile, writeTestList, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    assertEquals(writeTestList, Files.readAllLines(targetFile));

    // 이어 쓰기
    List<String> appendTextList = List.of("네 번째 라인", "다섯 번째 라인");
    example.writeLines(targetFile, appendTextList, StandardOpenOption.APPEND);
    ArrayList<String> appendFileTextList = new ArrayList<>(writeTestList);
    appendFileTextList.addAll(appendTextList);
    assertEquals(appendFileTextList, Files.readAllLines(targetFile));

    example.deleteFile(targetFile);
    assertFalse(Files.exists(targetFile));
  }

  @DisplayName("STREAM 파일 이동 테스트")
  @Test
  void testMoveFile() throws Exception {
    example.moveFile(file, movedFile);
    assertFalse(Files.exists(file));
    assertTrue(Files.exists(movedFile));
  }


}