package com.jre.assetregister.security.services;

import com.jre.assetregister.database.entities.LoginUser;
import com.jre.assetregister.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser currentUser = userRepository.findByUsername(username);

        UserDetails user = new User(
                username,
                currentUser.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList(currentUser.getUserRole())
        );

        return user;
    }
}
