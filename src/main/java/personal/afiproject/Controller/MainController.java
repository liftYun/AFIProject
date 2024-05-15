package personal.afiproject.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(HttpSession session, Model model) {
        String loginUser = (String) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
        return "main";
    }
}
