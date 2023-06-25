package com.kadipe.demo.user.repository;

import com.kadipe.common.MasterEntity;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;

@Entity
@Table(name = "DEMO_USER")
public class UserEntity extends MasterEntity {

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "KADIPE_KEY")
    private String kadipeKey;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private Collection<LoginEntity> loginEntities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKadipeKey() {
        return kadipeKey;
    }

    public void setKadipeKey(String kadipeKey) {
        this.kadipeKey = kadipeKey;
    }

    public Collection<LoginEntity> getLoginEntities() {
        return loginEntities;
    }

    public void setLoginEntities(Collection<LoginEntity> loginEntities) {
        this.loginEntities = loginEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        return new EqualsBuilder().appendSuper(super.equals(o)).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).toHashCode();
    }
}
