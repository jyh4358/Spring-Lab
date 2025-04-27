package com.jdong.lab.java.thread.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * {@code InvokeAllExample} 클래스는 Java의 {@code ExecutorService}를 활용하여
 * 여러 개의 작업을 병렬로 실행하고, 해당 작업들이 모두 완료될 때까지 대기한 후
 * 결과를 처리하는 방법을 설명합니다.
 *
 * <p>이 클래스는 {@code invokeAll()} 메서드를 이용해 주어진 작업 리스트를
 * 동시에 실행하며, 가장 오래 걸리는 작업이 종료될 때까지 블로킹됩니다.
 * 모든 작업이 완료된 후에는 작업 결과들이 {@code List<Future>} 형태로 반환됩니다.
 * 리스트의 각 {@code Future} 객체는 제출된 작업의 결과를 포함하며
 * {@code get()} 메서드를 통해 순서대로 접근할 수 있습니다.</p>
 *
 * <p>다음과 같은 특징을 가집니다:</p>
 * <ul>
 *   <li>작업 제출 순서대로 결과를 반환합니다.</li>
 *   <li>각 작업은 지정된 {@code Callable} 구현을 통해 수행됩니다.</li>
 *   <li>{@code ExecutorService}는 고정된 스레드 풀을 이용합니다.</li>
 *   <li>모든 작업이 완료된 이후 {@code shutdown()} 메서드를 호출하여 스레드 풀이 종료됩니다.</li>
 * </ul>
 * */
public class InvokeAllExample {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(3);

    List<Callable<Integer>> tasks = Arrays.asList(
        new CallableTask("task 1"),
        new CallableTask("task 2", 2000),
        new CallableTask("task 3", 4000)
    );

    // invokeAll 호출 → 작업 1, 2, 3 모두 제출
    // 3초 동안 invokeAll()은 블로킹됨 (가장 오래 걸리는 작업 기준)
    // ❗️ 모든 작업이 완료되면 List<Future>를 반환, 모든 작업이 완료 되어야 List<Future> 반환
    List<Future<Integer>> results = executor.invokeAll(tasks);

    // 출력 순서는 tasks에 제출한 순서대로 나온다.
    // (즉, 작업 2가 먼저 끝났어도 결과는 작업 1 → 작업 2 → 작업 3 순서로 출력)
    // ExecutionException, InterruptedException catch 하여 컨트롤 할 수 있다.
    for (Future<Integer> future : results) {
      System.out.println(future.get());
    }

    executor.shutdown();
  }
}
