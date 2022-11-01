package service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import model.Comment;
import model.CommentType;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import repo.CommentRepository;

@RunWith(SpringRunner.class)
public class CommentServiceTest {
    
    @MockBean
    private CommentRepository commentRepository;
    
    private CommentService commentService;
    
    @Before("")
    public void init(){
        commentService = new CommentService(commentRepository);
    }
    
    @Test
    public void getAllCommentsForToday_HappyPath_ShouldReturn1Comment(){
        
        // given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        List<Comment> comments = Arrays.asList(comment);
        LocalDate now = LocalDate.now();
        when(commentRepository.findByCreatedYearAndMonthAndDay(now.getYear(),
                now.getMonth().getValue(), now.getDayOfMonth())).thenReturn(comments);
        
        // when
        List<Comment> actualComments = commentService.getAllCommentsForToday();
        
        // then
        verify(commentRepository, times(1)).findByCreatedYearAndMonthAndDay(
        now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());
        Assertions.assertThat(comments).isEqualTo(actualComments);
        
    }
    
    @Test
    public void saveAll_HappyPath_ShouldSave2Comments(){
        
        // given
        Comment comment = new Comment();
        comment.setComment("Test Plus");
        comment.setType(CommentType.PLUS);
        comment.setCreatedBy("Sium");
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        
        Comment comment2 = new Comment();
        comment2.setComment("Test Star");
        comment2.setType(CommentType.STAR);
        comment2.setCreatedBy("Raju");
        comment2.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        
        List<Comment> comments = Arrays.asList(comment, comment2);
        when(commentRepository.saveAll(comments)).thenReturn(comments); 
        
        // when
        List<Comment> saved = commentService.saveAll(comments);
        
        // then 
        Assertions.assertThat(saved).isNotEmpty();
        verify(commentRepository, times(1)).saveAll(comments);
        
    }
    
}