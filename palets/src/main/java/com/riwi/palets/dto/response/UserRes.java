package com.riwi.palets.dto.response;

import com.riwi.palets.model.entity.User;
import com.riwi.palets.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRes {
    private String username;

    private String email;

    private UserRole role;
}
