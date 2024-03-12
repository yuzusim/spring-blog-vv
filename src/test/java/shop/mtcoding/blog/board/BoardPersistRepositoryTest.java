package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {


    @Autowired // DI
    private BoardPersistRepository boardPersistRepository;


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

}
