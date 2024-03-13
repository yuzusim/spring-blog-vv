package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board { // Entity 무조건 기본 생성자가 있어야 오류 나지 않음 @NoArgsConstructor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;

    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;

    // 생성자 만들기
    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    // 오브젝트 지향 프로그램이라서 업데이트 메서드를 만들어 주고 한번에 변경
    public void update(BoardRequest.UpdateDTO reqDTO){
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.username = reqDTO.getUsername();

    }


}
