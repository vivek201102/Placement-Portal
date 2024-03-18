package org.vivek.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vivek.placementportal.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String id);
    User findByEmail(String email);
}
