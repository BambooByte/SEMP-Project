package com.mini.project.last.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mini.project.last.Model.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Integer>{

	UserEntity findByUserMail(String userEmail);


}
