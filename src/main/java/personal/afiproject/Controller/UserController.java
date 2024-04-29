package personal.afiproject.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/")
    public String main() {return "main";}

    @GetMapping("/user/login")
    public String loginForm() {return "login"; } // login 페이지 이동

    @GetMapping("/user/register")
    public String registerForm() {return "register"; } //회원가입 페이지 이동

}
