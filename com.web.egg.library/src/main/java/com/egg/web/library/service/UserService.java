package com.egg.web.library.service;
import com.egg.web.library.entity.Roll;
import com.egg.web.library.entity.Users;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRep;

    private final String MENSAJE = "El usuario ingresado no existe";
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void createUser(String email, String password, Roll roll) throws MyExceptionService {

        Users user = new Users();
        user.setEmail(email);
        user.setRoll(roll);
        user.setPassword(encoder.encode(password));
        userRep.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRep.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(MENSAJE, email)));
        
        
        GrantedAuthority authority = new SimpleGrantedAuthority ("ROLE_"+users.getRoll().getName());
        
        
        return new User(users.getEmail(), users.getPassword(), Collections.singletonList(authority));

    }

}
