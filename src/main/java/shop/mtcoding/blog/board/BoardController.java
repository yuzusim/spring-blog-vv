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
import shop.mtcoding.blog._core.errs.exception.Exception403;
import shop.mtcoding.blog._core.errs.exception.Exception404;
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
        // toEntitiy 인서트 할때만 만든다.
        boardRepository.save(reqDTO.toEntitiy(sessionUser));

        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);

        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        boardRepository.updeteById(id, reqDTO.getTitle(), reqDTO.getContent());
        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {

        Board board = boardRepository.findById(id);

        if (board == null) {
            throw new Exception404("해당 게시글을 찾을 수 없습니다.");
        }

        request.setAttribute("board", board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findById(id);

        if (sessionUser.getId() != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }

        boardRepository.deleteById(id);
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
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findByIdJoinUser(id);

        // 로그인으, ㄹ하고 게시글의 주인이면 isOwner가 true가 된다.
        boolean isOwner = false; // 게시글 주인
        if (sessionUser != null) { // 세션 유저가 null이 아니면
            if (sessionUser.getId() == board.getUser().getId()) {
                isOwner = true;
            }
        }

        request.setAttribute("isOwner", isOwner);
        request.setAttribute("board", board);
        return "board/detail";
    } // Integer쓰는 이유 안들어 오면 null 이니까


}
