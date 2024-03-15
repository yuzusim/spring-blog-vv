package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void updeteById(int id, String title, String content){
        Board board = findById(id);
        board.setTitle(title);
        board.setContent(content); // 객체 상태를 변경하고
    } // 더티체킹

    @Transactional
    public void deleteById(int id){
        Query query = em.createQuery("delete from  Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public Board save(Board board){
        em.persist(board);
        return board;
    }


    public List<Board> findAllV2() {
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList(); // 여기서 레이지 로딩하면 망함 동적 쿼리 만들어 내야 함

        String q2 = "select u from User u where u.id in :id";

        List<User> userList = em.createQuery(q2, User.class).getResultList();

        return boardList; // user가 채워져 있어야 함

        // userList 크기 3
        // 유저 아이디와 같으면 넣어준다
        // 스트림의 필터 Api

    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
        // 알아서 pk, fk 키 연결
    }

    public Board findById(int id) {
        // 일단 조회
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);
        return board;
    }
}
