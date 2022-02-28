# 예외 처리와 오류 페이지
블로그 
- [서블릿의 예외 처리에 따른 오류 페이지 등록](https://jddng.tistory.com/267)
- [스프링 부트의 오류 페이지 처리](https://jddng.tistory.com/274?category=1043529)

스프링 부트 내부에서 예외에 따른 오류 페이지를 호출하는 방법을 알기위해 직접 오류페이지 등록과 호출을 구현


### 1.구현 내용

---

- 서브릿으로 오류 페이지 등록과 호출 구현
  - 상태코드와 Exception에 따른 오류 페이지 호출
  - 컨테이너에 WebServerCustomizer 등록
    - 상태코드(404, 500), exception에 따른 오류 페이지 등록
  - 스프링 부트에서 제공하는 BasicErrorController를 이용한 간단한 오류 페이지 구현


- 스프링 부트의 오류 페이지 처리 간단한 구현
  - BasicErrorController 사용

### 2. 구현 화면

---

- 서브릿으로 등록한 오류 페이지 호출
 

![image](https://user-images.githubusercontent.com/97331219/155973118-29c9fefc-b66b-4b52-9c2c-25b045099069.png)

![image](https://user-images.githubusercontent.com/97331219/155973213-dc15c166-f92a-4455-ad90-8ee8fa173c1f.png)


- 스프링 부트에서 제공하는 오류 페이지 처리 이용

![image](https://user-images.githubusercontent.com/97331219/155973469-675f39a8-c285-4b9b-8350-2ea6d840e5e1.png)

![img_1.png](img_1.png)