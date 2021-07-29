package com.alkemyjava.Huergo.services;

import com.alkemyjava.Huergo.entities.User;
import com.alkemyjava.Huergo.enumerations.Role;
import com.alkemyjava.Huergo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("NO EXISTE EL USUARIO");
        }
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(role));
    }

    @Transactional
    public User create(User user) {
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(encoder.encode(user.getPassword()));
        u.setRole(Role.USER);
        userRepository.save(u);
        return u;
    }
}
