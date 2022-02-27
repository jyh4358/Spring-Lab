# Session을 이용한 로그인 처리
블로그 : [Session 정리](https://jddng.tistory.com/268)

HttpSession을 이용한 로그인 처리 방법

### 1.구현 내용

---

- HttpSession을 이용하여 현재 로그인 상태인지 판별
- 로그인을 확인하기 위한 Session이 존재하지 않을 때 Session을 생성하지 않아도 되는 경우는 @SessionAttribute를 이용하여 처리
  
### 2. 구현 화면

---

- 로그인을 하지 않은 경우
  ![image](https://user-images.githubusercontent.com/97331219/155878408-acfe102b-cc1f-426c-9ed9-ebd6a2a8c555.png)


- 로그인을 한 경우
![image](https://user-images.githubusercontent.com/97331219/155878426-0c2c1c4b-c495-47b6-a643-9c66d15746ba.png)




