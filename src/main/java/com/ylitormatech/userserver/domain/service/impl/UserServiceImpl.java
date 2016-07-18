package com.ylitormatech.userserver.domain.service.impl;

import com.ylitormatech.userserver.domain.entity.UserEntity;
import com.ylitormatech.userserver.domain.repository.UserRepository;
import com.ylitormatech.userserver.web.WwwUser;
import com.ylitormatech.userserver.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Perttu Vanharanta on 7.7.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public boolean getIsUserExist(String username){
        return userRepository.getIsUserExist(username);
    }

    @Transactional(readOnly = true)
    public WwwUser getUser(String username) {
        UserEntity u = userRepository.getUser(username);
        return new WwwUser(u.getId(),u.getUsername(), u.getPassword(),u.getEmail(),u.getRole());
    }

    @Transactional(readOnly = false)
    public void singup(WwwUser u) {
        UserEntity dbu = new UserEntity();
        dbu.setEmail(u.getEmail());
        dbu.setPassword(passwordEncoder.encode(u.getPassword()));
        dbu.setRole(u.getRole());
        dbu.setUsername(u.getUsername());
        userRepository.store(dbu);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        return getUser(username);
    }
}
