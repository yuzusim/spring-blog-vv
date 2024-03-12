package shop.mtcoding.blog.board;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private  final EntityManager em;

    @Transactional
    public void save(String username, String title, String content){
        Query query =
                em.createNativeQuery("insert into board_tb (title, content, username, created_at) values (?, ?, ?, now())");

        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }

}
