package com.egg.web.library.service;

import com.egg.web.library.entity.Users;
import com.egg.web.library.exception.MyExceptionService;
import com.egg.web.library.repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createUser(String username, String password) throws MyExceptionService {

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        userRep.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRep.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(MENSAJE, username)));

        return new User(users.getUsername(), users.getPassword(), Collections.emptyList());

    }

}
