package com.jdong.lab.java.input_output.java1_file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Legacy I/O를 활용한 파일 작업 유틸리티 클래스.
 * 이 클래스는 파일 복사, 쓰기, 삭제, 이동과 같은 작업을 수행합니다.
 */
public class LegacyIOExample {
  /**
   * 바이트 단위 복사
   */
  public void copyFile(File file, File copyFile) throws IOException {
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
      // FileInputStream을 버퍼링하여 디스크 I/O 호출 횟수 최소화
      bis = new BufferedInputStream(new FileInputStream(file), 16_384);
      // FileOutputStream을 버퍼링하여 출력 효율성 향상
      bos = new BufferedOutputStream(new FileOutputStream(copyFile), 16_384);
      byte[] buf = new byte[16_384];
      int len;

      // 읽어들인 바이트 수만큼 출력 스트림에 쓰기
      while ((len = bis.read(buf)) != -1) {
        bos.write(buf, 0, len);
      }
      // 플러시
      bos.flush();
    } finally {
      // 리소스 누수 방지를 위해 스트림 닫기
      if (bis != null) try { bis.close(); } catch (IOException ignore) {}
      if (bos != null) try { bos.close(); } catch (IOException ignore) {}
    }
  }

  /**
   * 파일에 문자열 쓰기
   */
  public void writeFile(File file, String content, boolean isAppend) throws IOException {
    // default CharSet  : UTF-8
    // append
    //  - true : 파일 끝에 이어 쓰기
    //  - false : 덮어쓰기
    try (FileWriter writer = new FileWriter(file, isAppend)) {
      writer.write(content);
    }
  }

  /**
   * 파일 삭제
   * @return 삭제 성공 여부
   */
  public boolean deleteFile(File file) {
    return file.delete();
  }

  /**
   * 파일 이동(또는 이름 변경)
   * @return 이동(이름 변경) 성공 여부
   */
  public boolean moveFile(File file, File moveFile) {
    return file.renameTo(moveFile);
  }
}
