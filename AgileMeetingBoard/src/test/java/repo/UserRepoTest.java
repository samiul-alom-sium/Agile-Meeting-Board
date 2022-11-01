package repo;

import model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoTest {
    
    @Autowired
    private TestEntityManager testEntityManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void findByUsername_HappyPath_ShouldReturn1User() throws Exception {
       
        // given
        User user = new User();
        user.setUsername("sium");
        user.setPassword("sium123");
        user.setRole("USER");
        testEntityManager.persist(user);
        testEntityManager.flush();
        
        // when
        User actual = userRepository.findByUsername("sium");
        
        // then
        Assertions.assertThat(actual).isEqualTo(user);
        
    }
    
    @Test
    public void save_HappyPath_ShouldSave1User() throws Exception {
        
        // given
        User user = new User();
        user.setUsername("sium");
        user.setPassword("sium123");
        user.setRole("USER");
        
        // when
        User actual = userRepository.save(user);
        
        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getId()).isNotNull();
        
    }
        
}