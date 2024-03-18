package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errs.exception.Exception400;

import java.util.Optional;

@RequiredArgsConstructor
@Service // ioc 등록 됨
public class UserService {
    private final UserJPARepository userJPARepository;
    // 이제 컨트롤러는 서비스에 의존
    // 서비스는 레포에 의존

    @Transactional // userJPARepository 가 들고 있으면 안돼!! 한번에 여러개를 할 수 있으니까 서비스에서 통으로 관리해야 함
    public void 회원가입(UserRequest.JoinDTO reqDTO){
        // 1. 유효성 검사 (컨트롤러 책임)
        // save 하다가 날 수있는 오류의 종류가 엄청나게 많다
        // 트라이캐치로 퉁치면 어디서 오류가 나는지 알 수 없다.

        // 2. 유저네임 중복검사 (서비스 체크) DB 연결이 필요함
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername()); // 유저네임으로 조회
        if (userOP.isPresent()) { // 유저 중복
            throw new Exception400("중복된 유저 네임입니다."); // 예외처리
        }
        userJPARepository.save(reqDTO.toEntity());
    }
}
