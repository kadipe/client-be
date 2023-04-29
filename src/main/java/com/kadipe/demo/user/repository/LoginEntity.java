package com.kadipe.demo.user.repository;

import com.kadipe.common.MasterEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "DEMO_LOGIN")
public class LoginEntity extends MasterEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEMO_USER_ID")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
