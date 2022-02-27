# Bean Validation
블로그 : [Bean Validation 정리](https://jddng.tistory.com/262)



### 1. 구현 내용

---

- 표준 인터페이스(JSR-303, JSR-380) 사용
- 간단한 상품 등록 페이지의 검증 구현
- 적용 애노테이션
    - 상품명(itemName) : @NotBlank
    - 가격(price) : @NotNull, @Range(min, max)
    - 수량(quantity) : @Max
- 각 검증 에러에 대한 에러 메시지 적용
- ObjectError는 Controller에 직접 구현
- 단위 테스트
  - 검증
  - 에러 메시지 적용



### 2. 구현 이미지

--- 


![image](https://user-images.githubusercontent.com/97331219/155844160-dc3e9f08-e8c0-4c9d-aad6-0ac4efdf69b3.png)


![image](https://user-images.githubusercontent.com/97331219/155844563-540a5cb5-82d1-4cd4-b0c4-e76d4f7fc0f9.png)