package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.utils.ApiUtil;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final HttpSession session;

  // TODO: 회원정보 조회 API 필요 -> @GetMapping("/api/users/{id}") 유저의 1번 정보 줘
  @GetMapping("/api/users/{id}")
  public ResponseEntity<?> userinfo(@PathVariable Integer id){
      UserResponse.DTO respDTO = userService.회원조회(id);
      return ResponseEntity.ok(new ApiUtil(respDTO));
  }

    // 액션은 나눠야함 페이지줘만 필요 없음
    @PutMapping("/api/users/{id}") // 유저의 특정 id를 업데이트 해줘
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserRequest.UpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.회원수정(sessionUser.getId(), requestDTO);
        session.setAttribute("sessionUser", newSessionUser);
        return ResponseEntity.ok(new ApiUtil(newSessionUser));
    }

    @PostMapping("/join") // 인증이 필요 없는 부분은 앞에 붙이지 않음 원래는 인증과 관련된 컨트롤러 따로 만들어야 함
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO reqDTO) {
        User user = userService.회원가입(reqDTO);
        return ResponseEntity.ok(new ApiUtil(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.로그인(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

}
