package com.jdong.lab.java.thread.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InvokeAllTimeoutExample {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    List<Callable<Integer>> tasks = Arrays.asList(
        new CallableTask("task 1"),
        new CallableTask("task 2", 2000),
        new CallableTask("task 3", 4000)
    );

    // invokeAll 호출 → 작업 1, 2, 3 모두 제출
    // 5초 이내로 모든 작업이 완료 되어야 한다.
    // 3초 동안 invokeAll()은 블로킹됨 (가장 오래 걸리는 작업 기준)
    // ❗️ 모든 작업이 완료되면 List<Future>를 반환, 모든 작업이 완료 되어야 List<Future> 반환
    List<Future<Integer>> results = executor.invokeAll(tasks, 5, TimeUnit.SECONDS);

    // 출력 순서는 tasks에 제출한 순서대로 나온다.
    // (즉, 작업 2가 먼저 끝났어도 결과는 작업 1 → 작업 2 → 작업 3 순서로 출력)
    // ExecutionException, InterruptedException catch 하여 컨트롤 할 수 있다.
    for (Future<Integer> future : results) {
      System.out.println(future.get());
    }

    executor.shutdown();
  }
}
