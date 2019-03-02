package ru.kinoguide.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Privilege;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UserRole;
import ru.kinoguide.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier(value = "passwordEncoder")
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
//        return new UserPrincipalImpl(name, user.getPassword());
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getUserAuthorities(user));
    }

    private Collection<GrantedAuthority> getUserAuthorities(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (UserRole userRole : user.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getName()));
            for (Privilege privilege : userRole.getPrivileges()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return grantedAuthorities;
    }
}
