package shop.mtcoding.blog.board;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private  final EntityManager em;
    public List<Board> findAll(){
        Query query =
                em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return (List<Board>) query.getResultList(); // 앞에 다운 캐스팅
        // 조인시 DTO 만들어서 받아야 함

    }

    @Transactional
    public void save(String title, String content, String username){
        Query query =
                em.createNativeQuery("insert into board_tb (title, content, username, created_at) values (?, ?, ?, now())");

        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }





}
