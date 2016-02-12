package logcore.service;

import logcore.dao.SecurityUserDAO;
import logcore.domain.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecUserDetailsService implements UserDetailsService {

    @Autowired
    SecurityUserDAO securityUserDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = securityUserDAO.getUserByUsername(username);
        if (securityUser != null) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities = getSimpleGrantedAuthorities(securityUser, authorities);
            return new User(securityUser.getUsername(), securityUser.getPassword(), authorities);
        }
        else {
            throw new UsernameNotFoundException("No such user");
        }
    }

    private List<SimpleGrantedAuthority> getSimpleGrantedAuthorities(SecurityUser securityUser, List<SimpleGrantedAuthority> authorities) {
        if ((securityUser.getRoles() != null) && (securityUser.getRoles().size() != 0)) {
            authorities = securityUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());
        }
        return authorities;
    }
}