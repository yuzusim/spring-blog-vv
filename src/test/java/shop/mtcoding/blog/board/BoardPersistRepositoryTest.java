package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired // DI
    private BoardPersistRepository boardPersistRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test(){
        // given
//        String title = "제목5";
//        String content = "내용수정5";
//        String username = "ssar";

        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardPersistRepository.save(board);
        System.out.println("save_test : "+board);

        // then

    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardPersistRepository.findById(id);
        // em.clear();
        System.out.println("findById_test " + board);
        Board board2 = boardPersistRepository.findById(2);
        System.out.println("findById_test " + board2);

        // then
        // org.assertj.core.api
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
        assertThat(board2.getTitle()).isEqualTo("제목2");
        assertThat(board2.getContent()).isEqualTo("내용2");
    }

    // @Transactional
    @Test
    public void deleteByIdV2_test(){
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteByIdV2(id);

        // 강제로 쿼리 날림
        // 버퍼에 쥐고 있는 쿼리를 즉시 전송
        em.flush();

    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteById(id);

    }




}
