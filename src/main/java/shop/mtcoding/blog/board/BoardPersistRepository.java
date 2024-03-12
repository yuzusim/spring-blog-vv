package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;


    @Transactional
    public Board save(Board board) {
        // 1. 비영속 객체
        // Board board = new Board(title, content, username);
        em.persist(board);
        // board -> 영속 객체
        return board; // 래퍼런스니까 동기화가 되어 있다. board 그 자체!
    }

    public Board findById(int id) {
        Query query =
                em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);
        return (Board) query.getSingleResult();
    }

    public List<Board> findAll() {
        Query query =
                em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return (List<Board>) query.getResultList(); // 앞에 다운 캐스팅
        // 조인시 DTO 만들어서 받아야 함

    }


    @Transactional
    public void deleteById(int id) {
        Query query =
                em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }


    @Transactional
    public void updateById(int id, String title, String content, String username) {
        Query query =
                em.createNativeQuery("update board_tb set title=?, content=?, username=? where id=?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);
        query.setParameter(4, id);

        query.executeUpdate();
    }


}
