package personal.afiproject.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import personal.afiproject.dto.UserDTO;
import personal.afiproject.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm() {return "login"; } // login 페이지 이동

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        UserDTO loginResult = userService.login(userDTO);

        if(loginResult != null) {
            session.setAttribute("loginUser", loginResult.getUserId());
            System.out.println("로그인 성공: 사용자 " + loginResult.getUserId() + "으로 로그인하였습니다.");
            redirectAttributes.addFlashAttribute("error", "로그인 성공! 환영합니다:)");
            return "redirect:/"; // 로그인 성공 시 메인 페이지로 리다이렉트
        } else {
            System.out.println("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
            redirectAttributes.addFlashAttribute("error", "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/loginError")
    public String loginError(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "로그인이 필요한 서비스입니다.");
        return "redirect:/user/login";
    }

    @GetMapping("/register")
    public String registerForm() {return "register"; } //회원가입 페이지 이동

    @PostMapping("/register")
    public String registering(@ModelAttribute UserDTO userDTO,
                              @RequestParam("userPw") String userPw,
                              @RequestParam("userPwCheck") String userPwCheck,
                              RedirectAttributes redirectAttributes) {
        if(userPw.equals(userPwCheck)){
            userService.save(userDTO);
        }
        else {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/user/register";
        }
        return "redirect:/user/registerSuccess";
    } // 회원가입 중 비밀번호 비밀번호 확인이 같은 경우에만 회원가입 진행

    @PostMapping("/idCheck")
    @ResponseBody
    public ResponseEntity<?> idCheck(@RequestBody Map<String, String> payload) {
        String userId = payload.get("userId");
        boolean exists = userService.existsByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        if (exists) {
            response.put("message", "이미 존재하는 ID입니다.");
            response.put("status", "error");
        } else {
            response.put("message", "사용가능한 ID입니다.");
            response.put("status", "success");
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/phoneCheck")
    @ResponseBody
    public ResponseEntity<?> phoneCheck(@RequestBody Map<String, String> payload) {
        String userPhone = payload.get("userPhone");
        boolean exists = userService.existsByUserPhone(userPhone);
        Map<String, Object> response = new HashMap<>();
        if (exists) {
            response.put("message", "이미 가입된 전화번호입니다.");
            response.put("status", "error");
        } else {
            response.put("message", "가입가능한 전화번호입니다.");
            response.put("status", "success");
        }

        return ResponseEntity.ok(response);
    }
    @GetMapping("/registerSuccess")
    public String registerSuccess(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "회원가입에 성공했습니다.\n로그인 페이지로 이동합니다.");
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //세션삭제
        System.out.println("로그아웃하였습니다.");
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model){
        String loginUser = (String) session.getAttribute("loginUser");
        //세션에서 로그인 정보 가져옴
        UserDTO userDTO = userService.mypageForm(loginUser);
        //서비스를 통해 loginUser에 해당하는 정보 가져옴
        model.addAttribute("loginUserDTO", userDTO);
        //유저정보 전송.
        return "mypage";
    }

    @PutMapping("/update")
    public String updateUser(@ModelAttribute("userDTO") UserDTO userDTO,
                             RedirectAttributes redirectAttributes){
        userService.updateUser(userDTO);
        redirectAttributes.addFlashAttribute("error", "회원정보 수정이 되었습니다..");
        return "redirect:/user/mypage";
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(HttpSession session){
        String loginUser = (String) session.getAttribute("loginUser");
        userService.deleteUser(loginUser);
        session.invalidate();
        return ResponseEntity.ok().build(); // 성공 상태 코드 200 반환, 본문은 비워짐
    }
}
