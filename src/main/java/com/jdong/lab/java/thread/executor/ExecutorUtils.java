package com.jdong.lab.java.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ExecutorUtils {

  public static void printState(ExecutorService executorService) {
    if (executorService instanceof ThreadPoolExecutor poolExecutor) {
      int pool = poolExecutor.getPoolSize();
      int active = poolExecutor.getActiveCount();
      int queuedTasks = poolExecutor.getQueue().size();
      long completedTask = poolExecutor.getCompletedTaskCount();
      log.info("[pool={}, active={}, queuedTasks={}, completedTask={}]", pool, active, queuedTasks, completedTask);
    } else {
      log.info("{}",executorService);
    }
  }

  // 추가
  public static void printState(ExecutorService executorService, String taskName) {
    if (executorService instanceof ThreadPoolExecutor poolExecutor) {
      int pool = poolExecutor.getPoolSize();
      int active = poolExecutor.getActiveCount();
      int queuedTasks = poolExecutor.getQueue().size();
      long completedTask = poolExecutor.getCompletedTaskCount();
      log.info("{} -> [pool={}, active={}, queuedTasks={}, completedTask={}]", taskName, pool, active, queuedTasks, completedTask);
    } else {
      log.info("{}",executorService);
    }
  }
}

