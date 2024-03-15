package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/jogin")
    public String join(UserRequest.JoinDTO reqDTO) {
        User sessionUser = userRepository.save(reqDTO.toEntity());

        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }




    @PostMapping("/login")
    public String login() {

        return "redirect:/login-form";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/loginForm")
    public String loginForm() {

        return "user/login-form";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
