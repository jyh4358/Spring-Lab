# Converter와 Formatter
블로그 
- [Converter 구현 및 사용](https://jddng.tistory.com/290)
- [Formatter 구현 및 사용](https://jddng.tistory.com/291)

스프링 부트에서 제공하는 Type Converter 이해를 위한 Converter와 Formatter 구현

## 구현 내용

---


### 1. Converter


- Converter 구현
  - StringToIntegerConverter : String -> Integer 타입 변환 컨버터 구현
  - IntegerToStringConverter : Integer -> String 타입 변환 컨버터 구현
  - StringToIpPortConverter : String -> IpPort 타입 변환 컨버터 구현
    - IpPort : String ip, int port 필드를 가진 클래스
  - IpPortConverterToString : IpPort -> String 타입 변환 컨버터 구현
  - 위에서 구현한 컨버터들이 제대로 변환이 이루어지는지 테스트 코드 작성


- DefaultConversionService을 이용하여 컨터버 등록 후 사용하는 테스트 코드 작성


- WebConfig에 구현한 converter들을 등록 후 사용
  - addFormatters 메서드를 오버라이드하여 컨버터 등록


- 뷰 템플릿에서 컨버터 적용 방법
  - ${..} : 컨버터 적용 x
  - ${{ .. }} : 컨버터 적용
  - th:value : 컨버터 적용 x
  - th:field : 컨버터 적용

### 2. Formatter

- Formatter 구현
  - NumberFormatter
    - T parse : Number -> String : 1000 단위로 쉼표가 들어가는 포맷 구현
    - String print : String -> Number : 쉼표가 들어가있는 문자열을 Number 타입으로 변환하는 포맷 구현
  - 포맷 변환이 잘 이루어지는지 테스트 코드 작성


- DefaultFormattingConversionService를 이용하여 포맷터 등록 후 테스트 코드 작성


- WebConfig에 구현한 Formatter 등록 후 사용
  - addFormatters 메서드를 오버라이드하여 포맷터 등록


- 스프링이 제공하는 기본 포맷터 사용하기
  - @NumberFormat : 숫자 관련 포맷터
  - DateTimeFormat : 날짜 관련 포맷터