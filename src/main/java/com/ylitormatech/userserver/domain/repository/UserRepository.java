package com.ylitormatech.userserver.domain.repository;

import com.ylitormatech.userserver.domain.entity.UserEntity;

/**
 * Created by Perttu Vanharanta on 7.7.2016.
 */
public interface UserRepository {

    public UserEntity getUser(String username);

    public void store(UserEntity userEntity);
}
