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
    public void deleteByIdV2(int id) {
        // 비영속 객체를 하나 만든다.
        Board board = findById(id); // id만 넣어서 테스트도 해보고 함
        em.remove(board); // PC 에서 객체지우고 (트랜잭션 종료시) 삭제 쿼리를 전송함
        // 조회 먼저 동기 화 시킴
        // 있으면 리무브
        // 없으면 null

    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createQuery("delete from Board b where b.id = :id");
//        Board board = findById(id);
//        em.remove(board);
        query.setParameter("id", id);
        query.executeUpdate();

        // 비영속 객체를 하나 만든다.
        //Board board = findById(id);
        // 조회 먼저 동기 화 시킴
        // 있으면 리무브
        // 없으면 null
        //em.remove(board);

    }



    public Board findById(int id){
        Board board = em.find(Board.class, id); // 조회가 끝남
        return board;

        // em PC에서 조회가 끝난다.

    }



    public List<Board> findAll(){
        Query query =
                em.createQuery("select b from Board b order by b.id desc", Board.class);

        return query.getResultList();
    }


    @Transactional
    public Board save(Board board) {
        // 1. 비영속 객체
        // Board board = new Board(title, content, username);
        em.persist(board);
        // board -> 영속 객체
        return board; // 래퍼런스니까 동기화가 되어 있다. board 그 자체!
    }


    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO requestDTO) {
        Board board = findById(id);
        board.update(requestDTO);
    } // 더티체킹
    // 데이터 베이스에 동기화된 pc


}
