package shop.mtcoding.blog.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.blog.user.User;

public interface ReplyJPARepository extends JpaRepository<Reply, Integer> {

    // 댓글쓰기 삭제 목록보기 세개 필요

}
