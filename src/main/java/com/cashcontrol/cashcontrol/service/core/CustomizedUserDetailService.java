package com.cashcontrol.cashcontrol.service.core;

import com.cashcontrol.cashcontrol.entity.user.User;
import com.cashcontrol.cashcontrol.service.repoHandler.UserRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomizedUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepoHandler userRepoHandler;


    public CustomizedUserDetailService(UserRepoHandler userRepoHandler) {
        this.userRepoHandler = userRepoHandler;
    }

    //login: checking user by username in user table
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepoHandler.findUserByUserId(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password..!");
        }
        return new CustomUserDetails(user);
    }


}
