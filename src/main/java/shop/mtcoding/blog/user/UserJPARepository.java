package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// @Repository 없이 자동 컴퍼넌트 스캔이 된다.
public interface UserJPARepository extends JpaRepository<User, Integer> { // 제네릭의 타입과 PK 타입을 알려줘야 함

    // 복잡한 쿼리는 레포 하나 더 만들어서 사용한다. (엔티티명 쿼리 레포)
    // 간단한거는 여기다가 쓰고
    // @Query("select  u from User u where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password")  String password);
}

