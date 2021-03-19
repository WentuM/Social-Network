package ru.itis.springlectionsdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springlectionsdemo.models.User;


public interface UsersRepository extends JpaRepository<User, Long> {

}
