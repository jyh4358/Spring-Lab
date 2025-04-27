package com.jdong.lab.java.thread.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * InvokeAnyTimeoutExample 클래스는 {@link ExecutorService}를 활용하여
 * {@code invokeAny} 메서드에 타임아웃을 설정하고, 주어진 작업 중 가장 빠른 결과를 얻는 방법을 시연합니다.
 *
 * <p>이 클래스는 다수의 작업(task)이 비동기적으로 수행되는 환경에서,
 * 설정된 시간 동안에 완료된 첫 번째 작업의 결과를 가져오는 데 사용됩니다.
 * 만약 지정된 시간 내에 작업이 완료되지 않을 경우, {@link TimeoutException}이 발생합니다.
 *
 * <p>또한, {@link InterruptedException} 및 {@link java.util.concurrent.ExecutionException} 예외를 처리하며,
 * 작업 수행 중 발생할 수 있는 다양한 상황을 제어할 수 있습니다.
 */
public class InvokeAnyTimeoutExample {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(3);

    List<Callable<Integer>> tasks = Arrays.asList(
        new CallableTask("task 1")
    );

    try {
      // 최대 2초 동안만 가장 빠른 결과를 리턴, timeout 보다 오래 걸리면 TimeoutException 예외 발생
      // InterruptedException, ExecutionException 또한 catch 하여 컨트롤 할 수 있음
      Integer result = executor.invokeAny(tasks, 2, TimeUnit.SECONDS);
      System.out.println("결과: " + result);
    } catch (TimeoutException e) {
      System.out.println("시간 초과로 실패");
    }

    executor.shutdown();
  }
}
