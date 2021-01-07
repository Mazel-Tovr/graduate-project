package com.mazeltov.authorization.service.dao.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<com.mazeltov.authorization.service.dao.model.User, Long> {
    Optional<com.mazeltov.authorization.service.dao.model.User> findByUsername(String username);
}
