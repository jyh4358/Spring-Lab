package com.jdong.lab.java.thread.executor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableTask implements Runnable {

  private final String name;
  private int sleepMs = 1000;

  public RunnableTask(String name) {
    this.name = name;
  }

  public RunnableTask(String name, int sleepMs) {
    this.name = name;
    this.sleepMs = sleepMs;
  }

  @Override
  public void run() {
    log.info("{} 시작", name);
    int i = 0;
    while (i < sleepMs) {
      i += 1000;
      log.info("{} 실행중 : {} ms", name, i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    log.info("{} 완료", name);
  }
}
