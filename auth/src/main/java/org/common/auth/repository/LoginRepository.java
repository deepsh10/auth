package org.common.auth.repository;

import org.common.auth.dto.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginRequest, Long> {
}
