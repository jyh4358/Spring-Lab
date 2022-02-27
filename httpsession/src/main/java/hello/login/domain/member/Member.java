package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class Member {

    private Long id;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    public Member() {
    }

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
