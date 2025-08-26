package com.skillnest.firstspring.repository;

import com.skillnest.firstspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//findAll() -> SELECT * FROM users;
//findById(id) -> SELECT * FROM users WHERE id = 1;
//save(user) -> INSERT y UPDATE
//deleteById(id) -> DELETE FROM users WHERE id = 1;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); //SELECT * FROM users WHERE email = <email>;

}
