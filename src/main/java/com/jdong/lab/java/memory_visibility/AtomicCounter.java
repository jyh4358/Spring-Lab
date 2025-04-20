package com.jdong.lab.java.memory_visibility;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
  // 여러 스레드가 동시에 접근해도 안전한 카운터
  private AtomicInteger counter = new AtomicInteger(0);

  public int increment() {
    // 원자적으로 값을 1 증가시키고 현재 값을 반환
    return counter.incrementAndGet();
  }

  public int getCounter() {
    return counter.get(); // 현재 값을 가져옴 (가시성 보장)
  }

  public static void main(String[] args) throws InterruptedException {
    AtomicCounter example = new AtomicCounter();

    Runnable incrementTask = () -> {
      for (int i = 0; i < 1000; i++) {
        example.increment();
      }
    };

    Thread t1 = new Thread(incrementTask);
    Thread t2 = new Thread(incrementTask);

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    // 예상 결과: 2000 (두 스레드가 각각 1000번씩 증가)
    System.out.println("최종 카운터 값: " + example.getCounter());
  }
}
