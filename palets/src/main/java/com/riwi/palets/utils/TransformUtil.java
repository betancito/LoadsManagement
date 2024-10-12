package com.riwi.palets.utils;

import com.riwi.palets.dto.request.UserReq;
import com.riwi.palets.dto.response.UserRes;
import com.riwi.palets.model.entity.User;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransformUtil {

    //Utils to transform Users
    //to userEntity from UserRequest
    public User transformToUser(UserReq userReq){
        return User.builder()
                .username(userReq.getUsername())
                .email(userReq.getEmail())
                .password(userReq.getPassword())
                .role(userReq.getRole())
                .build();
    }

    //to userResponse from UserEntity
    public UserRes transformUserRes(User userEntity){
        return UserRes.builder()
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .role(userEntity.getRole())
                .build();
    }
}
