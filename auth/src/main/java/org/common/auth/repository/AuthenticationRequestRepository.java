package org.common.auth.repository;

import org.common.auth.model.AuthenticationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthenticationRequestRepository extends JpaRepository<AuthenticationRequest, Long> {

    @Query(value = "select * from authentication_request where email_id = ?1 and uuid= ?2 order by created_instance desc",nativeQuery = true)
    List<AuthenticationRequest> findAllByURN(@Param("email") String email, @Param("urn") String urn);
}
