package com.jdong.lab.java.thread.executor;

import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallableTask implements Callable<Integer> {

    private final String name;
    private int sleepMs = 1000;

    public CallableTask(String name) {
        this.name = name;
    }

    public CallableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public Integer call() throws Exception {
        log.info("{} 실행", name);
        try {
            Thread.sleep(sleepMs + 3000);
        } catch (InterruptedException e) {
            log.info("인터럽트 발생, {}", e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("{} 완료", name);
        return sleepMs;
    }
}
