package org.common.auth.repository;

import org.common.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select * from user where email_id = ?1 and password = ?2",nativeQuery = true)
    List<User> findAllByLogin(@Param("emailId") String emailId, @Param("password") String password);
}
