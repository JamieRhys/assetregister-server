package com.jre.assetregister.database.repositories;

import com.jre.assetregister.database.entities.LoginUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<LoginUser, Long> {
    LoginUser findByUsername(@Param("username") String username);
}
