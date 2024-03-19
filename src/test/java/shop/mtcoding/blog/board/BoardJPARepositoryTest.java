package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;


    @Test
    public void findByIdJoinUserAAndReplies_test() {
        // given
        int id = 4; // 더미를 알고 넣기

        // when
        Board board = boardJPARepository.findByIdJoinUserAAndReplies(id).get();

        // then

    }

    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        System.out.println("save_test : id" + board.getId()); // lazy 전략

    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Optional<Board> boardrOP = boardJPARepository.findById(id);
        if (boardrOP.isPresent()) {
            Board board = boardrOP.get();
            System.out.println("findById_test : " + board.getTitle());
        }

        // then

    }


    // save
    // findById
    // findAll
    // deleteById

    @Test
    public void findByIdJoinUser_test() {
        // given
        int id = 1;

        // when
        Board board = boardJPARepository.findByIdJoinUser(id).get();

        // then
        System.out.println("findByIdJoinUser_test : " + board.getTitle());
        System.out.println("findByIdJoinUser_test : " + board.getUser().getUsername());

    }


    @Test
    public void findAll_test() {
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // when
        List<Board> boardList = boardJPARepository.findAll(sort);

        // then
        System.out.println("findAll_test : " + boardList);
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardJPARepository.deleteById(id);
        em.flush();

        // then


    }


}
