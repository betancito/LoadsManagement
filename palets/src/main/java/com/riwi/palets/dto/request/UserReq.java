package com.riwi.palets.dto.request;

import com.riwi.palets.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {
    private String username;

    private String email;

    private String password;

    private UserRole role;
}
