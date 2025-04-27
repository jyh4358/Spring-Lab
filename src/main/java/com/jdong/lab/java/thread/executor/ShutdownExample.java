package com.jdong.lab.java.thread.executor;

import static com.jdong.lab.java.thread.executor.ExecutorUtils.printState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutdownExample {

  /**
   * 애플리케이션의 진입점으로 ExecutorService를 활용하여 다중 스레드 작업을 처리하고 종료 관리 로직을 수행합니다.
   *
   * @param args 명령어 실행 시 전달받는 인자 배열
   */
  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    executor.execute(new RunnableTask("task1"));  // 1초
    executor.execute(new RunnableTask("task2"));  // 1초
    executor.execute(new RunnableTask("task3", 3_000)); // 3초
    executor.execute(new RunnableTask("task4", 3_000)); // 3초
    executor.execute(new RunnableTask("task5", 5_000));

    printState(executor);

    // 더 이상 새로운 작업은 받지 않음
    // 처리 중이거나, 이미 큐에 대기중인 작업만 처리
    executor.shutdown();
    log.info("shutdown 호출");

    try {
      // 이미 처리 중 및 대기중인 작업들이 모두 완료될 때 까지 최대 3초 기다린다.
      if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
        // 3초 동안 처리가 되지 않은 경우 처리 로직
        executor.shutdownNow(); // 실행 중 작업을 인터럽트
        // 작업이 취소될 때 까지 대기한다.
        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
          log.info("서비스가 종료되지 않았습니다.");
        }
        log.info("서비스가 강제로 종료되었습니다.");
      } else {
        log.info("처리 중 및 대기 중인 모든 작업 종료 완료");
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    log.info("shutdown 완료");
    printState(executor);
  }

}
