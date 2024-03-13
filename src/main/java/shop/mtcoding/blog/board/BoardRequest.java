package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class saveDTO {
        private String title;
        private String content;
        private String username;

        // 메서드 만들기 인서트 할때만 필요
        public Board toEntitiy() { // 비영속 객체 동기화 되지 않음
            return new Board(title, content, username);
        }

    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String username;
        // 애를 엔티티로 바꿔서 save 하지 않을거니 toEntity 필요 없다!
        // 업데이트 필드는 같아도 로직은 다를 수 있음
    }

}
