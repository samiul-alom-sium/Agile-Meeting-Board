package service;

import java.util.Arrays;
import model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.UserRepository;


@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(username == null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
    }
    
    @Transactional(rollbackFor = Exception.class)
    public User create(User user){
        return userRepository.save(user);
    }


}