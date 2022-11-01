package util;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

@Component
public class AuditAwareImplementation implements AuditorAware<String>{
    
    @Override
    public Optional<String> getCurrentAuditor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication == null || !authentication.isAuthenticated()){
            return Optional.empty();
        }
        
        return Optional.of(((User) authentication.getPrincipal()).getUsername());
    }
    
    

}
