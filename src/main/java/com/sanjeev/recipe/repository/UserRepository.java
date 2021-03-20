package com.sanjeev.recipe.repository;

import com.sanjeev.recipe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findUserByUserId(@Param("userId")String userId);
}
