package ru.kpfu.itis.demo.blog.web.jpa.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.demo.blog.web.entity.PostEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepository extends JpaSpecificationExecutor<PostEntity>, JpaRepository<PostEntity, Long> {
//    Page<PostEntity> findAllByTittle(String name, Pageable pageable);


    List<PostEntity> findAllByIdIsNotNull();
    List<PostEntity> findAllByAccount_Id(Long accountId);
//    List<PostEntity> findPostEntitiesByAccountFollowUserId(ArrayList<Long> account_followUser);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,  value = "INSERT INTO post_like (post_id, account_id) VALUES (:postId, :accountId)")
    void insertPostIdAndAccountId(@Param("postId") Long postId, @Param("accountId") Long accountId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM post_like WHERE post_id = :postId and account_id = :accountId")
    void deletePostIdAndAccountId(@Param("postId") Long postId, @Param("accountId") Long accountId);
}
