package ru.kpfu.itis.demo.blog.impl.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.demo.blog.impl.entity.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
}
