package com.example.firstproject.security_service;

import com.example.firstproject.model.Users;
import com.example.firstproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDeteilsServiceImpl")
public class UserDeteilsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users users = usersRepository.findByEmail(s).get();
        return MySecurityUsers.userDetailsFromMyUsers(users);
    }
}
