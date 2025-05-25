package com.jdong.domain.sample.controller;

import com.jdong.domain.sample.controller.dto.FooResponse;
import com.jdong.domain.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SampleController {

  private final SampleService sampleService;

  /**
   * 동기 방식으로 외부 API 결과를 받아서 그대로 반환
   */
  @GetMapping("/api/foos/{id}")
  public Mono<ResponseEntity<FooResponse>> getFoo(@PathVariable Long id) {
    Mono<ResponseEntity<FooResponse>> responseEntityMono = sampleService.fetchFooSync(id)
        // 데이터가 있을 때 200 OK + 바디
        .map(ResponseEntity::ok)
        // Mono가 빈 값일 때 404 Not Found
        .defaultIfEmpty(ResponseEntity.notFound().build());

    System.out.println("responseEntityMono = " + responseEntityMono);
    return responseEntityMono;
  }


  @GetMapping("/external/api/foos/{id}")
  public ResponseEntity<FooResponse> getExternalFoo(@PathVariable Long id) {

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return ResponseEntity.ok(new FooResponse(id, "푸우"));
  }
}
