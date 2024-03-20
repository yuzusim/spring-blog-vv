package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class DTO{
        private Integer id;
        private String username;
        private String email;

        // 풀생성자 만든다. -> 편하려고 엔티티를 생성자로 만듬
        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }
}
