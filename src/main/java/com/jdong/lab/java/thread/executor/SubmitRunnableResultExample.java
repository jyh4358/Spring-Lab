package com.jdong.lab.java.thread.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;


/**
 * {@code SubmitRunnableResultExample} 클래스는 {@link ExecutorService}를 사용하여 {@link Runnable} 구현체를 실행하고,
 * 그 결과를 {@link Future} 객체를 통해 반환받는 예제를 보여줍니다.
 *
 * <p>{@code Runnable}은 반환 값을 가지지 않지만, {@link ExecutorService#submit(Runnable, Object)} 메서드를 통해
 * 실행된 작업에 대해 성공적으로 완료된 경우 특정 반환 값을 지정할 수 있습니다.
 *
 * <p>{@link Future#get()} 메서드를 통해 실행 결과를 가져올 수 있으며, 이 과정에서 {@link ExecutionException} 또는
 * {@link InterruptedException} 예외가 발생할 수 있으므로, 적절한 예외 처리가 필요합니다.
 *
 * <h2>주요 작업 흐름</h2>
 * <ul>
 *   <li>{@link ExecutorService} 인스턴스 생성</li>
 *   <li>{@link RunnableTask} 실행 및 결과 반환</li>
 *   <li>{@link Future}를 통해 작업 상태 및 결과 확인</li>
 *   <li>예외 처리 및 쓰레드 풀 종료</li>
 * </ul>
 *
 * <h2>주의사항</h2>
 * <ul>
 *   <li>{@link ExecutionException} 발생 시, {@code getCause()} 메서드를 통해 실제 원인 예외를 확인할 수 있습니다.</li>
 *   <li>항상 {@link ExecutorService#shutdown()}을 호출하여 자원을 명시적으로 해제해야 합니다.</li>
 * </ul>
 *
 * <p>이 클래스는 {@code RunnableTask}를 사용하므로 {@code RunnableTask} 클래스에 대한 사전 정의가 필요합니다.
 */
@Slf4j
public class SubmitRunnableResultExample {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    Future<Boolean> future = executor.submit(new RunnableTask("task1"), true);

    // Runnable 구현 객체의 run 은 반환 값이 없지만,
    // 성공일 경우 반환 값을 지정하여 반환값을 얻을 수 있다.
    // future.get()은 Checked Exception인 ExecutionException, InterruptedException을
    // catch 하여 컨트롤 할 수 있다.
    try {
      log.info("결과: {}", future.get());
    } catch (ExecutionException e) { // 내부에서 예외가 발생한 경우
      // 실제 cause(원인 예외)를 가져올 수 있음
      System.out.println("ExecutionException 발생: " + e.getCause().getMessage());
    } catch (InterruptedException e) { // 현재 스레드가 대기 중 인터럽트된 경우
      System.out.println("InterruptedException 발생");
      Thread.currentThread().interrupt(); // 상태 복구
    } finally {
      executor.shutdown();
    }
  }
}
