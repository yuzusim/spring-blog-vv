package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@Data
@Table(name = "board_tb")
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;

    public String getTime(){
        return MyDateUtil.timestampoFormat(createdAt);
        //  나중에 머스태치에 ,time이라고 적으면 불러온다.
    }


}
