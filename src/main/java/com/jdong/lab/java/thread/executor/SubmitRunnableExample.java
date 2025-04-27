package com.jdong.lab.java.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class SubmitRunnableExample {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    Future<?> future = executor.submit(new RunnableTask("task1"));

    future.get(); // 완료 여부만 확인 (반환값은 없음)

    executor.shutdown();
  }
}
