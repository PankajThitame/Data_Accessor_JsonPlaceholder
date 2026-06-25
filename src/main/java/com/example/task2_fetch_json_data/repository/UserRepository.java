package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findByExternalId(Long externalId);
}
