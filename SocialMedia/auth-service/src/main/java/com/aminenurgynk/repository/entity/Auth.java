package com.aminenurgynk.repository.entity;

import com.aminenurgynk.repository.enums.ERole;
import com.aminenurgynk.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
public class Auth  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ERole role = ERole.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
