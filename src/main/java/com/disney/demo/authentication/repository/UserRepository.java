package com.disney.demo.authentication.repository;

import com.disney.demo.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    UserEntity findByUsername(String username);
}
