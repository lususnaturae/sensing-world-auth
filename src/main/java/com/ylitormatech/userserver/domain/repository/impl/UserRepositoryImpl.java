package com.ylitormatech.userserver.domain.repository.impl;

import com.ylitormatech.userserver.domain.entity.UserEntity;
import com.ylitormatech.userserver.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Perttu Vanharanta on 7.7.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public UserEntity getUser(String username) {
        return em.createQuery("FROM UserEntity u WHERE u.username=:username", UserEntity.class).setParameter("username", username).getSingleResult();
    }


    public boolean getIsUserExist(String username){
        List<UserEntity> list = em.createQuery("FROM UserEntity u WHERE u.username=:username", UserEntity.class).setParameter("username",username).getResultList();
        if(!list.isEmpty())
        {
            return false;
        }
        return true;
    }


    @Override
    public void store(UserEntity userEntity) {
        em.persist(userEntity);
    }
}
