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
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/")
    public String main(HttpSession session, Model model) {
        String loginUser = (String) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);
        return "main";
    }

    @GetMapping("/user/login")
    public String loginForm() {return "login"; } // login 페이지 이동

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO,
                        @RequestParam("userId") String userId,
                        @RequestParam("userPw") String userPw,
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
    /*@PostMapping("/user/login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("userPw") String userPw,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        Optional<UserEntity> userOptional = userRepository.findByUserId(userId);

        if(userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            String hashedPassword = user.getUserPw();

            if(BCrypt.checkpw(userPw, hashedPassword)) {
                session.setAttribute("loginUser", user.getUserId()); // 키 값 수정
                // 비밀번호 정보를 제외한 DTO로 세션 정보 설정
                *//*UserDTO loginUserInfo = UserDTO.toUserDTO(user);
                // 비밀번호 정보는 세션에 저장하지 않습니다.
                loginUserInfo.setUserPw(null); // 안전을 위해 null 처리하는 것을 추천하지만, 이 필드는 이미 DTO에서 제거됨
                session.setAttribute("loginUserInfo", loginUserInfo);*//*
                redirectAttributes.addFlashAttribute("error", "로그인 성공! 환영합니다:)");
                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("error", "로그인 실패: 비밀번호가 잘못되었습니다.");
                return "redirect:/user/login";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "로그인 실패: 아이디가 잘못되었습니다.");
            return "redirect:/user/login";
        }
    } MVC패턴 / 보안상의 문제로 해당 코드 권장하지 않음.  */

    @GetMapping("/user/loginError")
    public String loginError(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "로그인이 필요한 서비스입니다.");
        return "redirect:/user/login";
    }

    @GetMapping("/user/register")
    public String registerForm() {return "register"; } //회원가입 페이지 이동

    @PostMapping("/user/register")
    public String registering(@ModelAttribute UserDTO userDTO,
                              @RequestParam("userPw") String userPw,
                              @RequestParam("userPwConfirm") String userPwConfirm,
                              RedirectAttributes redirectAttributes) {
        if(userPw.equals(userPwConfirm)){
            userService.save(userDTO);
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "redirect:/user/register";
        }
        return "redirect:/user/registerSuccess";
    } // 회원가입 중 비밀번호 비밀번호 확인이 같은 경우에만 회원가입 진행

    @PostMapping("/user/idCheck")
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
    @PostMapping("/user/phoneCheck")
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

    /*@PostMapping("/user/idCheck")
    @ResponseBody // ajax를 쓸 때 컨트롤러에서 반환되는 값을 html에 보내기 위한 용도로 사용된(body에 직접적으로 값을 포함해야한다는 의미)
    public String idCheck(@RequestParam("userId") String userId) { // ajax로 부터 userId를 받아서 서비스에 넘기며 로직을 요청한다
        System.out.println("Controller");
        return userService.idCheck(userId);
    }// 이미 있는 아이디의 경우 등록하지 않도록 함*/

    /*@PostMapping("/user/phoneCheck")
    @ResponseBody
    public String phoneCheck(@RequestParam("userPhone") String userPhone) {
        return userService.phoneCheck(userPhone);
    }// 이미 있는 전화번호의 경우 등록하지 않도록 함*/
    @GetMapping("/user/registerSuccess")
    public String registerSuccess(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error", "회원가입에 성공했습니다.\n로그인 페이지로 이동합니다.");
        return "redirect:/user/login";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //세션삭제
        System.out.println("로그아웃하였습니다.");
        return "redirect:/";
    }

    @GetMapping("/user/mypage")
    public String mypage(HttpSession session, Model model){
        String loginUser = (String) session.getAttribute("loginUser");
        //세션에서 로그인 정보 가져옴
        UserDTO userDTO = userService.mypageForm(loginUser);
        //서비스를 통해 loginUser에 해당하는 정보 가져옴
        model.addAttribute("loginUserDTO", userDTO);
        //유저정보 전송.
        return "mypage";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute("userDTO") UserDTO userDTO,
                             RedirectAttributes redirectAttributes){
        userService.updateUser(userDTO);
        redirectAttributes.addFlashAttribute("error", "회원정보 수정이 되었습니다..");
        return "redirect:/user/mypage";
    }

    @GetMapping("/user/delete")
    public String deleteUser(HttpSession session,
                             RedirectAttributes redirectAttributes){
        String loginUser = (String) session.getAttribute("loginUser");
        userService.deleteUser(loginUser);
        session.invalidate();
        redirectAttributes.addFlashAttribute("error", "회원탈퇴 되었습니다..");
        return "redirect:/";
    }
}
