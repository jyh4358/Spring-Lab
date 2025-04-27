package com.jdong.lab.java.thread.memory_visibility;

public class SynchronizedMemoryVisibility {

  private boolean flag = false; // 공유 변수 (volatile 아님)

  public synchronized void setFlag(boolean value) {
    this.flag = value; // 변경
    // synchronized 블록을 나갈 때 캐시 내용이 메인 메모리에 반영됨
  }

  public synchronized boolean isFlag() {
    // synchronized 블록에 진입할 때 메인 메모리에서 최신 값을 가져옴
    return this.flag;
  }

  public static void main(String[] args) throws InterruptedException {
    SynchronizedMemoryVisibility example = new SynchronizedMemoryVisibility();

    Thread worker = new Thread(() -> {
      // flag 값을 읽기 위해 synchronized 메서드 호출
      while (!example.isFlag()) {
        // 대기
      }
      System.out.println("작업 스레드: flag가 true로 변경되어 루프를 종료합니다.");
    });

    worker.start();

    System.out.println("메인 스레드: 2초 대기 후 flag를 true로 변경합니다.");
    Thread.sleep(2000);

    // flag 값을 변경하기 위해 synchronized 메서드 호출
    example.setFlag(true);
    System.out.println("메인 스레드: flag를 true로 변경했습니다.");

    worker.join();
    System.out.println("메인 스레드 종료");
  }
}
