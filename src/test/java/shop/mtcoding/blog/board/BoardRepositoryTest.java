package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;


    @Test
    public void findAll_inqueryv2_test() {
        String q = "SELECT DISTINCT b FROM Board b JOIN FETCH b.user";
        List<Board> boards = em.createQuery(q, Board.class).getResultList();

        for (Board board : boards) {
            System.out.println("Board ID: " + board.getId());
            System.out.println("User ID: " + board.getUser().getId());
            // 사용자(User) 객체에 대한 다른 정보도 필요하다면 여기서 출력하거나 작업할 수 있습니다.
        }
    }



    @Test
    public void randomquery_test(){
        int[] ids = {1,2};

        // select u from User u where u.id in (?,?);
        String q = "select u from User u where u.id in (";
        for (int i=0; i<ids.length; i++){
            if(i==ids.length-1){
                q = q + "?)";
            }else{
                q = q + "?,";
            }
        }
        System.out.println(q);
    }

   @Test
   public void findAll_custom_inquery_test(){
       List<Board> boardList = boardRepository.findAll();

       int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
       for (int i : userIds){
           System.out.println(i);
       }
       // 찌그러뜨리고 다시 수집
       // select * from user_tb where id in (3,2,1,1);
       // select * from user_tb where id in (3,2,1);

       // then

   }


    @Test
    public void findAll_lazyloding_test() {
        // given

        // when Board board =
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername()); //여기서 레이지 오딩 발동
        });

        // then
    }

    @Test
    public void findAll_test() {
        // given

        // when Board board =
        List<Board> boardList = boardRepository.findAll();

        // then
    }

    @Test
    public void finById_test() {
        int id = 1;
        System.out.println("start - 1");
        Board board = boardRepository.findById(1);
        System.out.println("start - 2");
        System.out.println(board.getUser().getId());
        System.out.println("start - 3");
        String username = board.getUser().getUsername();
        System.out.println(username);
    }

    @Test
    public void findById_test() {
        int id = 1;
        System.out.println("start - 1");
        Board board = boardRepository.findById(id);
        System.out.println("start - 2");
        System.out.println(board.getUser().getId());
        System.out.println("start - 3");
        System.out.println(board.getUser().getUsername());
    }

}
