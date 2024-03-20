package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    // TODO: 글조회 API 필요 -> @GetMapping("/") // 메인 데이터 줘
    // TODO: 글상세보기 API 필요 -> @GetMapping("/api/boards/{id}/detail") // 인증 필요하지 않아, 유저정보 댓글까지 다 있음
    // TODO: 글목록조회 API 필요 -> @GetMapping("/api/boards/{id}") // 기본 글 조회


    @PostMapping("/api/boards")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // toEntitiy 인서트 할때만 만든다.
        boardService.글쓰기(reqDTO, sessionUser);
        return "redirect:/";
    }

    @PutMapping("/api/boards/{id}") //
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정(id, sessionUser.getId(), reqDTO);
        return "redirect:/board/" + id;
    }

    @DeleteMapping("/api/boards")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

}
