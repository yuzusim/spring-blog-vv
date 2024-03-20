package shop.mtcoding.blog._core.errs;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog._core.errs.exception.*;
import shop.mtcoding.blog.utils.ApiUtil;

// 런타임 익셉션이 터지면 해당 파일로 오류가 모인다
@RestControllerAdvice // 데이터 응답
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> ex400(Exception e){
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage()); //
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); //
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(Exception e){
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage()); //
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED); //
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(Exception e){
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(Exception e){
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(Exception e){
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}


// 쓰로우가 발생하면 여기로 모임

// 구분 ㅅ시켜야 한다
