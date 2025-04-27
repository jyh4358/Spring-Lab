package com.jdong.lab.java.thread.memory_visibility;

import java.util.concurrent.atomic.AtomicReference;

public class ImmutableObject {

  // 불변 객체에 대한 스레드 안전한 참조
  private AtomicReference<ImmutableConfig> currentConfig = new AtomicReference<>(
      new ImmutableConfig("initial.url", 1000)
  );

  public ImmutableConfig getImmutableConfig() {
    return currentConfig.get(); // AtomicReference로 참조 읽기 (가시성 보장)
  }

  public void updateConfig(String serverUrl, int timeout) {
    ImmutableConfig newConfig = new ImmutableConfig(serverUrl, timeout); // 새로운 불변 객체 생성
    currentConfig.set(newConfig); // AtomicReference로 참조 업데이트 (원자성 및 가시성 보장)
  }

  public static void main(String[] args) throws InterruptedException {
    ImmutableObject example = new ImmutableObject();

    // 설정을 읽는 스레드
    Thread reader = new Thread(() -> {
      for (int i = 0; i < 5; i++) {
        ImmutableConfig config = example.getImmutableConfig();
        System.out.println("Reader: " + config);
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
    });

    // 설정을 업데이트하는 스레드 (예: 관리 기능)
    Thread updater = new Thread(() -> {
      try {
        Thread.sleep(1200);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      example.updateConfig("updated.url", 5000);
      System.out.println("Updater: 설정 업데이트 완료");
    });

    reader.start();
    updater.start();

    reader.join();
    updater.join();
  }
}
