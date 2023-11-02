package com.example.authorizationregistration.repos;

import com.example.authorizationregistration.models.modelUser;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<modelUser,Long> {
    modelUser findByUsername(String username);
}
