package shop.mtcoding.blog.board;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    private final BoardNativeRepository boardNativeRepository;

    @PostMapping("/board/save")
    public String save(String title, String content, String username) {
        boardNativeRepository.save(title, content, username);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList", boardList);


        // Model은 리퀘스트
        // 세션에 담는거는 인증 용도로만 쓴다.
        // A페이지 갔다가 B로 갔다가 A 가고 싶을때 세션에 저장
        // 리퀘스트는 오래 기억이 안된다. 잠시 뿌리는 것만 가능
        // HttpServletRequest request 담아야 꺼내어 쓸 수 있음


        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {
        return "board/detail";
    } // Integer쓰는 이유 안들어 오면 null 이니까

}
