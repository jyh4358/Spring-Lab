package com.jdong.lab.java.input_output.java7_async_file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * 파일 작업 관련 유용한 비동기 및 동기 메서드를 제공하는 유틸리티 클래스.
 */
public class AsyncFileExample {

  /**
   * 비동기 방식으로 파일을 읽습니다.
   * <p>
   * 지정된 파일 경로로부터 데이터를 읽어 ByteBuffer에 저장하며, 결과는 {@link Future} 객체로 반환됩니다.
   * </p>
   *
   * @param path   읽을 파일의 {@link Path} 경로
   * @param buffer 읽은 데이터를 저장할 {@link ByteBuffer}
   * @return 읽기 작업 상태를 나타내는 {@link Future} 객체 (완료 후 읽은 바이트 수 반환)
   * @throws IOException 파일 열기나 읽기 작업 중 에러가 발생한 경우
   * @see AsynchronousFileChannel#read(ByteBuffer, long)
   * @see StandardOpenOption#READ
   */
  public Future<Integer> readAsync(Path path, ByteBuffer buffer) throws IOException {
    AsynchronousFileChannel ch = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
    return ch.read(buffer, 0);
  }

  /**
   * 비동기 방식으로 파일에 데이터를 씁니다.
   * <p>
   * 지정된 파일 경로에 데이터를 쓰며, 파일이 없으면 새로 생성합니다.
   * </p>
   *
   * @param path   파일의 {@link Path} 경로
   * @param buffer 저장할 데이터를 포함하는 {@link ByteBuffer}
   * @param openOptions 쓰기 작업 옵션 (예: 파일 열기 모드). 생략 시 기본값 사용.
   *                    <ul>
   *                      <li>{@link StandardOpenOption#CREATE}: 파일이 존재하지 않으면 새로 생성.</li>
   *                      <li>{@link StandardOpenOption#APPEND}: 파일 끝에 데이터를 추가.</li>
   *                      <li>{@link StandardOpenOption#TRUNCATE_EXISTING}: 기존 파일 내용을 지우고 새로 작성.</li>
   *                      <li>{@link StandardOpenOption#WRITE}: 파일을 작성 모드로 엽니다 (기본값).</li>
   *                    </ul>
   * @return 쓰기 작업 상태를 나타내는 {@link Future} 객체 (완료 후 쓴 바이트 수 반환)
   * @throws IOException 파일 열기나 쓰기 작업 중 에러가 발생한 경우
   * @see AsynchronousFileChannel#write(ByteBuffer, long)
   * @see StandardOpenOption#WRITE
   * @see StandardOpenOption#CREATE
   */
  public Future<Integer> writeAsync(Path path, ByteBuffer buffer, StandardOpenOption... openOptions) throws IOException {
    AsynchronousFileChannel ch = AsynchronousFileChannel.open(path, openOptions);

    // 만약 끝에 이어 쓰고 싶다면 아래 주석 추가
//    long size = ch.size();
//    return ch.write(buffer, size);
    return ch.write(buffer, 0);
  }

  /**
   * 파일을 동기적으로 삭제합니다.
   * <p>해당 파일이 존재하지 않는 경우 작업은 예외 없이 무시됩니다.</p>
   *
   * @param path 삭제할 파일의 {@link Path} 경로
   * @throws IOException 파일 삭제 작업 중 에러가 발생한 경우
   * @see Files#deleteIfExists(Path)
   */
  public void deleteFile(Path path) throws IOException {
    Files.deleteIfExists(path);
  }

  /**
   * 파일을 동기적으로 다른 경로로 이동합니다.
   * <p>이동 중 대상 경로의 디렉터리가 없으면 자동으로 생성됩니다.</p>
   *
   * @param src  이동할 원본 파일의 {@link Path}
   * @param dest 파일이 이동될 대상 경로
   * @throws IOException 디렉터리 생성 및 파일 이동 작업 중 에러가 발생한 경우
   * @see Files#move(Path, Path, CopyOption...)
   * @see StandardCopyOption#REPLACE_EXISTING
   */
  public void moveFile(Path src, Path dest) throws IOException {
    Files.createDirectories(dest.getParent());
    Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
  }
}
