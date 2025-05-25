package com.jdong.domain.sample.service;

import com.jdong.domain.sample.controller.dto.FooResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class SampleService {

  private final WebClient webClient;

  /**
   * 외부 API를 호출하고, Mono<Foo>를 Foo 반환
   */
  public Mono<FooResponse> fetchFooSync(Long id) {
    Mono<FooResponse> fooResponseMono = webClient.get()
        .uri("http://localhost:8080/external/api/foos/{id}", id)
        .retrieve()                         // 4xx/5xx는 WebClientResponseException
        .bodyToMono(FooResponse.class);// Mono<Foo> 생성

    String name = Thread.currentThread().getName();
    System.out.println("Request Thread Name = " + name);


    // subscribe()를 사용하면 인텔리제이에서 경고 표시를 준다.
    // 따라서 대신 flatMap(), zip(), then() 등과 같은 컴포지션 연산자를 사용하는 것이 좋습니다.
//    fooResponseMono.subscribe(path -> {
//          System.out.println("path = " + path);
//          String name2 = Thread.currentThread().getName();
//          System.out.println("name = " + name2);
//        }
//    );

    // 또는 아래와 같이 subscribeOn을 사용하여 경고 표시 제거
    fooResponseMono.subscribeOn(Schedulers.boundedElastic()).subscribe(response -> {
      String reactorNettyThreadName = Thread.currentThread().getName();
      System.out.println("reactorNettyThreadName = " + reactorNettyThreadName);
    });

    return fooResponseMono;
  }


}
