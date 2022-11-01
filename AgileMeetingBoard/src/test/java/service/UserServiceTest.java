package service;

import model.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import repo.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;
    
    private UserService userService;
    
    @Before("")
    public void init(){
        this.userService = new UserService(userRepository);
    }
    
    @Test
    public void getAllCommentsForToday_HappyPath_ShouldReturn1Comments(){
        
        // given
        User user = new User();
        user.setUsername("sium");
        user.setPassword("sium123");
        user.setRole("USER");
        when(userRepository.findByUsername("sium")).thenReturn(user);
        
        // when
        UserDetails actual = userService.loadUserByUserName("sium");
        
        // then
        verify(userRepository, times(1)).findByUsername("sium");
        
    }
}