package org.example.exam_management_application.repository;

import org.example.exam_management_application.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
