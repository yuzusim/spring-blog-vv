package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static  class saveDTO{
        private String title;
        private String content;
        private String username;

        // 메서드 만들기 인서트 할때만 필요
        public Board toEntitiy(){ // 비영속 객체 동기화 되지 않음
            return new Board(title, content, username);
        }


    }

}
