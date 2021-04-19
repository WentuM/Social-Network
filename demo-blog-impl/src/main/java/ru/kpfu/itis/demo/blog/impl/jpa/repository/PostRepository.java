package ru.kpfu.itis.demo.blog.impl.jpa.repository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.demo.blog.api.dto.PostDTO;
import ru.kpfu.itis.demo.blog.api.dto.UserDTO;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.projection.OnlyTittlePost;
import ru.kpfu.itis.demo.blog.impl.entity.PostEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepository extends JpaSpecificationExecutor<PostEntity>, JpaRepository<PostEntity, Long> {
    Page<PostEntity> findAllByTittle(String name, Pageable pageable);


    List<PostEntity> findAllByIdIsNotNull();

    @Transactional
    @Modifying
    @Query(nativeQuery = true,  value = "INSERT INTO post_like (post_id, account_id) VALUES (:postId, :accountId)")
    void insertPostIdAndAccountId(@Param("postId") Long postId, @Param("accountId") Long accountId);

    //    @Override
//    @EntityGraph(value = "Post.Comments")
//    Page<PostEntity> findAll(Pageable pageable);
//    @Query("select new ru.kpfu.itis.demo.blog.api.dto.PostDTO(" +
//            "   m, " +
//            "   count(ml), " +
//            "   (sum(case when ml = :user then 1 else 0 end) > 0)" +
//            ") " +
//            "from PostEntity m left join m.likePosts ml " +
//            "group by m")
//    List<PostDTO> findAll(@Param("user_id") Long id);


//    @EntityGraph(value = "Post.Comments")
//    List<PostEntity> findAllWithout();
}
