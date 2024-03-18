package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errs.exception.Exception403;
import shop.mtcoding.blog._core.errs.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    @Transactional
    public Board 글조회(int boardId) {
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 2. 권한처리 -> 나중에 뺏음
//        if (sessionUserId != board.getUser().getId()) {
//            throw new Exception403("게시글을 수정할 권한이 없습니다.");
//        }
        // 단순한 조회로는 다를 수 있다.
        // 조회해서ㅏ 세션 윶저와 맞아야한다.
        return board;
    }

    @Transactional
    public void 글수정(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        // 1. 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 2. 권한처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
    }

    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        boardJPARepository.save(reqDTO.toEntitiy(sessionUser));
    }


    @Transactional
    public void 글삭제(Integer boardId, Integer sessionUserId) {
        // 1. 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 2. 권한처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        boardJPARepository.deleteById(boardId);

    }

    public List<Board> 글목록조회() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepository.findAll(sort);
    }

    // board, isOwner
//    public void 글상세보기(Integer boardId, User sessionUser) {
//                Board board = boardJPARepository.findByIdJoinUser(boardId)
//                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
//
//        // 로그인을 하고, 게시글의 주인이면 isOwner가 true가 된다.
//        boolean isOwner = false; // 게시글 주인
//        if (sessionUser != null) { // 세션 유저가 null이 아니면
//            if (sessionUser.getId() == board.getUser().getId()) {
//                isOwner = true;
//            }
//        }

//    // board, isOwner
//    public BoardResponse.DetailDTO 글상세보기(int boardId, User sessionUser) {
//        Board board = boardJPARepository.findByIdJoinUser(boardId)
//                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
//
//        // 로그인을 하고, 게시글의 주인이면 isOwner가 true가 된다.
//
//        return new BoardResponse.DetailDTO(board, sessionUser);
//    }

    // board, isOwner
    public Board 글상세보기(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        boolean isOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId() == board.getUser().getId()){
                isOwner = true;
            }
        }

        board.setOwner(isOwner);

        return board;
    }

}
