package hello.login.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class LoginForm {

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String loginId;

    @NotEmpty
    private String password;
}
