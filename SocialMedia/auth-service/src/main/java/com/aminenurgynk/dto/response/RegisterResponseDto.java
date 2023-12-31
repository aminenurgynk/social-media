package com.aminenurgynk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {

    private Long id;
    private String activationCode;
    private String username;
}
