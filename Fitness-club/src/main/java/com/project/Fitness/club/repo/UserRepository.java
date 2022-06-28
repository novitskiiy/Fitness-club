package com.project.Fitness.club.repo;

import com.project.Fitness.club.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
