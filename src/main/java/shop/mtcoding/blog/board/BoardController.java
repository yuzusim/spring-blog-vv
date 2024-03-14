package shop.mtcoding.blog.board;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // toEntitiy 인서트 할때만 만든다
        boardRepository.save(reqDTO.toEntitiy(sessionUser));

        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id) {

        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        return "redirect:/";
    }



    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardRepository.findByIdJoinUser(id);

        request.setAttribute("board", board);
        return "board/detail";
    } // Integer쓰는 이유 안들어 오면 null 이니까


}
