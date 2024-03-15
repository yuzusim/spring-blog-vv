package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
// import shop.mtcoding.blog.user UserRepository;


@Import(UserRepository.class) // ioc 등록 코드
@DataJpaTest // Datasource(connection pool), EntityManager -> 필요한 것만 띄운다.
public class UserRepositoryTest {

    @Autowired // DI
    private UserRepository userRepository;


    @Test
    public void findByUsername_test() {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");

//        String username = "ssar";
//        String password = "1234";

        // when
     //   User user = userRepository.findByUsernameAndpassword(reqDTO);

        //then assertThat
      //  Assertions.assertThat(user.getUsername()).isEqualTo("ssar");

    }

}
