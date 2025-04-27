package com.jdong.lab.java.thread.memory_visibility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
final class ImmutableConfig {

  private final String serverUrl;
  private final int timeout;

  @Override
  public String toString() {
    return "AppConfig{serverUrl='" + serverUrl + "', timeout=" + timeout + '}';
  }
}
