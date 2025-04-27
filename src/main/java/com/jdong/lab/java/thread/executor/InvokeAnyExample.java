package com.jdong.lab.java.thread.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@code InvokeAnyExample} 클래스는 Java의 {@link ExecutorService}와 {@link Callable} 인터페이스를 사용하여 여러 비동기 작업 중
 * 가장 먼저 완료된 작업의 결과를 반환하는 예제를 보여줍니다.
 *
 * <p>이 클래스는 {@code ExecutorService.invokeAny} 메서드를 활용하여 여러 {@code Callable} 작업을 병렬로 처리하며,
 * 진행 중인 작업들 중 가장 빠르게 완료된 작업의 결과를 받아옵니다.
 *
 * <p>{@code invokeAny} 메서드는 아래의 특성을 갖습니다:
 * <ul>
 *   <li>여러 {@code Callable} 작업을 실행하지만, 가장 먼저 완료된 작업의 결과만 반환합니다.</li>
 *   <li>나머지 작업들은 중단되거나 무시됩니다.</li>
 *   <li>{@code InterruptedException}, {@code ExecutionException}과 같은 예외가 발생할 수 있으며, 이에 대한 처리가 필요합니다.</li>
 * </ul>
 *
 * <p>이 클래스는 작은 작업 집합에 대해 병렬 처리를 구현하며, {@code ExecutorService}의 올바른 종료를 위해 {@code shutdown()} 호출이 필요합니다.
 */
public class InvokeAnyExample {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(3);

    List<Callable<Integer>> tasks = Arrays.asList(
        new CallableTask("task 1"),
        new CallableTask("task 2", 2000)
    );

    // 가장 먼저 완료된 작업 결과를 리턴
    // InterruptedException, ExecutionException catch 하여 컨트롤 할 수 있음
    Integer result = executor.invokeAny(tasks);

    System.out.println("가장 빠른 결과: " + result);

    executor.shutdown();
  }
}
