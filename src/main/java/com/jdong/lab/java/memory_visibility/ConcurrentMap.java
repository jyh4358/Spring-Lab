package com.jdong.lab.java.memory_visibility;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {
  // 여러 스레드가 동시에 접근해도 안전한 Map
  private Map<String, Integer> sharedMap = new ConcurrentHashMap<>();

  public void addOrUpdate(String key, Integer value) {
    sharedMap.put(key, value); // 스레드 안전한 쓰기 (가시성 및 원자성 보장)
  }

  public Integer getValue(String key) {
    return sharedMap.get(key); // 스레드 안전한 읽기 (가시성 보장)
  }

  public static void main(String[] args) throws InterruptedException {
    ConcurrentMap example = new ConcurrentMap();

    Runnable task = () -> {
      for (int i = 0; i < 1000; i++) {
        example.addOrUpdate("key" + i % 10, i);
      }
    };

    Thread t1 = new Thread(task);
    Thread t2 = new Thread(task);

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Map 크기: " + example.sharedMap.size());
    System.out.println("마지막 key9 값: " + example.getValue("key9")); // 마지막으로 put된 값 중 하나
  }
}
