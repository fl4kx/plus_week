package com.example.demo.repository;

import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id IN :userIds AND u.status != :status")
    void blockUsersById(@Param("status") Status status, @Param("userIds") List<Long> userIds);

}
