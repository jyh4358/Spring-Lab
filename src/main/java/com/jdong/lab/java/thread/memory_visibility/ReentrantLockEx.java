package com.jdong.lab.java.thread.memory_visibility;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {
  private int count = 0;
  // 명시적인 락 객체 생성
  private final Lock lock = new ReentrantLock();
  // 공정 모드 락
//  private final Lock lock = new ReentrantLock(trye);

  public void increment() {
    lock.lock(); // 락 획득 (synchronized 블록 진입과 유사)
    try {
      // 이 블록 내의 코드는 한 번에 하나의 스레드만 실행 가능
      // 가시성 및 원자성 보장
      count++;
      System.out.println(Thread.currentThread().getName() + ": count = " + count);
    } finally {
      lock.unlock(); // 락 해제 (synchronized 블록 탈출과 유사) - 필수적으로 finally에서 호출
    }
  }

  public int getCount() {
    lock.lock(); // 읽기 시에도 락 획득하여 가시성 보장
    try {
      return count; // 최신 값 읽기
    } finally {
      lock.unlock();
    }
  }


  public static void main(String[] args) throws InterruptedException {
    ReentrantLockEx example = new ReentrantLockEx();

    Runnable task = () -> {
      for (int i = 0; i < 1000; i++) {
        example.increment();
      }
    };

    Thread t1 = new Thread(task, "Thread-1");
    Thread t2 = new Thread(task, "Thread-2");

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("최종 카운트 값: " + example.getCount());
  }
}
