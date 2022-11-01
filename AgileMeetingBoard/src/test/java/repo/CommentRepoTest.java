package repo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import model.Comment;
import model.CommentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepoTest {
    
    @Autowired
    private TestEntityManager testEntityManager;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Test
    public void findByCreatedYearAndMonthAndDay_HappyPath_ShouldReturn1Comment(){
        // given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        testEntityManager.persist(comment);
        testEntityManager.flush();
        
        // when
        LocalDate now = LocalDate.now();
        List<Comment> comments = commentRepository.findByCreatedYearAndMonthAndDay(now.getYear(),
                now.getMonth().getValue(), now.getDayOfMonth());
        
        // then
        Assertions.assertThat(comments).hasSize(1);
        Assertions.assertThat(comments.get(0)).hasFieldOrPropertyWithValue("comment", "Test");
    }
    
    @Test
    public void save_happyPath_shouldSave1Comment(){
        // given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        
        // when
        Comment saved = commentRepository.save(comment);
        
        // then
        Assertions.assertThat(testEntityManager.find(comment.getClass(),
                saved.getId())).isEqualTo(saved);
    }
    
}