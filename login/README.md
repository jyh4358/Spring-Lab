# Session을 직접 구현하여 로그인 처리
블로그 : [Session 구현 정리](https://jddng.tistory.com/267)

Session을 직접 구현하여 실제로 적용

### 1.구현 내용

---


- 회원 가입 검증
- 로그인 검증 애노테이션
  - 아이디(loginId) : @NotEmpty, @Pattern(regexp = "[a-zA-Z0-9]*")
  - 비밀번호(password) : @NotEmpty
  - 에러 메시지 적용
- Session 저장소를 관리해주는 SessionManager 구현
  - 세션 생성 : UUID.randomUUID() 이용
  - 세션 조회 : Map 컬렉션에 저장한 Session 조회
  - 세션 만료 : Map 컬렉션에 저장한 Session 제거
- 구현한 세션 단위 테스트 
  
### 2. 구현 화면

---

- 회원 가입 검증

![image](https://user-images.githubusercontent.com/97331219/155874944-4f3b8f1b-2631-411f-9980-0d3632be6d3f.png)



- 로그인 검증


  ![image](https://user-images.githubusercontent.com/97331219/155875030-17f2874c-2dcd-4a43-ae37-941bf552a103.png)
 

  ![image](https://user-images.githubusercontent.com/97331219/155875047-8df7717c-6a35-4fb6-a271-964b2a2e36a5.png)


