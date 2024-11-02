package com.bytebuddy.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytebuddy.scm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
