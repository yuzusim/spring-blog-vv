package shop.mtcoding.blog.board;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
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
    private final BoardPersistRepository boardPersistRepository;


    // 트랜잭션 시간이 너무 길어져서 지금은 이렇게 나중에는 서비스 할 수 있는 서비스 로직으로 따로 만들기
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        // 조회해서 영속화 시킴. 객체의 상태만 바꾸면 끝!
        boardPersistRepository.updateById(id, reqDTO);
        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardPersistRepository.findById(id);
        request.setAttribute("board", board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        boardPersistRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.saveDTO reqDTO) { // 값을 받는 건 DTO로 받음
        boardPersistRepository.save(reqDTO.toEntitiy()); // toEntitiy
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardPersistRepository.findAll();
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
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardPersistRepository.findById(id);
        request.setAttribute("board", board);

        return "board/detail";
    } // Integer쓰는 이유 안들어 오면 null 이니까

}
