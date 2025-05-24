package com.jdong.lab.java.input_output.java11_convenience_file;

import static org.junit.jupiter.api.Assertions.*;

import com.jdong.lab.java.input_output.java7_nio_file.NioFileExample;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConvenienceFileExampleTest {

  private final Path file = Paths.get("src/test/resources/convenience_file_test/data.txt");
  private final Path targetFile = Paths.get("src/test/resources/convenience_file_test/convenience-target-file.txt");
  private final Path movedFile = Paths.get("src/test/resources/convenience_file_test/convenience-move-file.txt");
  private final ConvenienceFileExample example = new ConvenienceFileExample();

  private static final String INIT_FILE_CONTENT = "Convenience 파일";


  @BeforeEach
  void setUp() throws Exception {
    Files.createDirectories(file.getParent());
    example.writeText(file, INIT_FILE_CONTENT, StandardOpenOption.CREATE);
  }

  @Test
  void testReadAndWrite() throws Exception {
    example.writeText(targetFile, "test", StandardOpenOption.CREATE);
    assertEquals("test", example.readText(targetFile));
  }

  @Test
  void testDeleteAndMove() throws Exception {
    example.moveFile(file, movedFile);
    assertFalse(Files.exists(file));
    assertTrue(Files.exists(movedFile));
    example.deleteFile(movedFile);
    assertFalse(Files.exists(movedFile));
  }
}