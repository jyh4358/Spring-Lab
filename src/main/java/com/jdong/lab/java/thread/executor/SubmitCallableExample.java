package com.jdong.lab.java.thread.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * {@code SubmitCallableExample} 클래스는 Java에서 {@link ExecutorService}와 {@link java.util.concurrent.Callable}을 활용하여
 * 비동기 작업을 실행하고 결과를 처리하는 방법을 보여주는 예제입니다.
 *
 * <p>이 클래스는 {@code CallableTask}를 제출(submit)하여 {@link Future} 객체를 통해 실행 결과를
 * 반환받는 과정을 설명합니다. 이를 통해 비동기적으로 실행되는 태스크의 결과를 확인하거나 예외를
 * 처리하는 방법을 배울 수 있습니다.
 *
 * <p>사용된 주요 기능:
 * <ul>
 *   <li>{@link ExecutorService}를 사용한 스레드 관리</li>
 *   <li>{@link java.util.concurrent.Callable}를 통해 태스크 정의 및 실행</li>
 *   <li>{@link Future}를 사용해 비동기 계산 결과 처리</li>
 *   <li>예외 처리 메커니즘을 활용하여 {@link ExecutionException}과
 *       {@link InterruptedException}을 처리</li>
 * </ul>
 *
 * <p>이 클래스는 {@code main} 메서드를 통해 프로젝트 진입점으로 실행되며, 비동기 작업 실행 시
 * 발생할 수 있는 다양한 시나리오를 다룹니다.
 *
 * <p>주의사항:
 * <ul>
 *   <li>{@code ExecutorService}는 사용 후 반드시 {@code shutdown()} 메서드를 통해 종료해야 합니다.</li>
 *   <li>비동기 작업의 결과에 {@link Future#get()}를 호출하기 전, 작업이 완료될 때까지 대기합니다.</li>
 *   <li>작업이 지연되거나 실패하는 경우 예외를 적절히 처리할 필요가 있습니다.</li>
 * </ul>
 */
@Slf4j
public class SubmitCallableExample {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    // 나중에 결과를 받을 수 있는 Future 객체를 반환한다.
    // Callable은 Runnable과 달리 반환값과 예외를 가질 수 있다.
    Future<Integer> future = executor.submit(new CallableTask("task1"));

    // future.get()은 Checked Exception인 ExecutionException, InterruptedException을
    // catch 하여 컨트롤 할 수 있다.
    try {
      log.info("결과: {}", future.get());
    } catch (ExecutionException e) {
      // 실제 cause(원인 예외)를 가져올 수 있음
      System.out.println("ExecutionException 발생: " + e.getCause().getMessage());
    } catch (InterruptedException e) {
      System.out.println("InterruptedException 발생");
    } finally {
      executor.shutdown();
    }
  }
}
