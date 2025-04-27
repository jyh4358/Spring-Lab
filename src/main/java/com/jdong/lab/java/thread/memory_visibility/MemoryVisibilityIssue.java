package com.jdong.lab.java.thread.memory_visibility;

public class MemoryVisibilityIssue {

//  private static boolean flag = false;
  private static volatile boolean flag = false; // volatile 키워드 추가


  public static void main(String[] args) throws InterruptedException {
    // 작업 스레드 생성
    Thread worker = new Thread(() -> {
      while (!flag) {
        // flag가 true가 될 때까지 대기
      }
      System.out.println("작업 스레드: flag가 true로 변경되어 루프를 종료합니다.");
    });

    worker.start(); // 작업 스레드 시작

    System.out.println("메인 스레드: 2초 대기 후 flag를 true로 변경합니다.");
    Thread.sleep(100); // 2초 대기

    flag = true; // flag 값 변경
    System.out.println("메인 스레드: flag를 true로 변경했습니다.");

    // 작업 스레드가 종료될 때까지 대기
    System.out.println("메인 스레드 종료");
  }
}
