package com.fmsia2.user.service.repositories;

import com.fmsia2.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String > {
    // by default JpaRepository provide basic CRUD operation for some custom application
    // write the code here


}
