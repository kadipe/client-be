package com.kadipe.common;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class MasterEntity {

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    @GenericGenerator(name = "sequenceUUID", strategy = "com.kadipe.fw.KeyGenerator")
    @GeneratedValue(generator = "sequenceUUID")
    private String id;

    @Column(name = "tz", nullable = false)
    private String timeZone;

    @Column(name = "CREATE_TS", nullable = false)
    private LocalDateTime createTS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public LocalDateTime getCreateTS() {
        return createTS;
    }

    public void setCreateTS(LocalDateTime createTS) {
        this.createTS = createTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MasterEntity that = (MasterEntity) o;

        return new EqualsBuilder().append(getId(), that.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }
}
