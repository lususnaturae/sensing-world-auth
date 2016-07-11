package com.ylitormatech.userserver.domain.service;


import com.ylitormatech.userserver.web.WwwUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Perttu Vanharanta on 7.7.2016.
 */
public interface UserService extends UserDetailsService {

    public WwwUser getUser(String username);
    public boolean getIsUserExist(String username);

    public void singup(WwwUser u);

    public UserDetails loadUserByUsername(String username);

}
