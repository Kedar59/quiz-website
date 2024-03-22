package com.fmsia2.user.service.repositories;

import com.fmsia2.user.service.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth , String > {
    @Query("Select u from UserAuth u WHERE u.email = :email")
    Optional<UserAuth> findByEmail(@Param("email") String email);
}
