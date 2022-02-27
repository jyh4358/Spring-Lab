package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        // 응답으로 생성된 세션을 전달
        sessionManager.createSession(member, response);

        // 요청 메시지에 세션 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());
        
        // 요청 메시지의 세션을 조회하여 value 값을 가져옴
        Object sessionResult = sessionManager.getSession(request);

        // 응답으로 보낸 세션과 요청 메시지에 담긴 세션 값이 같은지 테스트
        Assertions.assertThat(sessionResult).isEqualTo(member);

        // 세션 만료 테스트
        sessionManager.expireSession(request);
        Object expiredSession = sessionManager.getSession(request);
        Assertions.assertThat(expiredSession).isNull();


    }
}