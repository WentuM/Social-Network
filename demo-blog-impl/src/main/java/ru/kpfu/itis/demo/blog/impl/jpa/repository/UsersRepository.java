package ru.kpfu.itis.demo.blog.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,  value = "INSERT INTO account_followers (account_id, follower_id) VALUES (:accountId, :followerId)")
    void insertAccountIdAndFollowerId(@Param("accountId") Long accountId, @Param("followerId") Long followerId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,  value = "DELETE FROM account_followers WHERE account_id = :accountId and follower_id = :followerId")
    void deleteAccountIdAndFollowerId(@Param("accountId") Long accountId, @Param("followerId") Long followerId);
//    Optional<UserEntity> findByAccountId(String accountId);
}
