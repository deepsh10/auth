package org.common.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;

    private String emailId;

    private String uuid;

    private Instant createdInstance;

}
