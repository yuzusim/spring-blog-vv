package shop.mtcoding.blog.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/*
 * 1. one 관계 조인하고 Many 조회 한번 더 하기 -> DTO 담기
 * 2. Many 관계를 양방향 매핑한다.
 */


@DataJpaTest
public class ReplyJPARepositoryTest {
    @Autowired
    private ReplyJPARepository replyJPARepository;
}
