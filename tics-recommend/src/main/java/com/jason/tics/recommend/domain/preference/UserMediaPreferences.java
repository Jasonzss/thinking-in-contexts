package com.jason.tics.recommend.domain.preference;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Jason
 */
@Data
@Entity
@Table
public class UserMediaPreferences {
    @EmbeddedId
    private UserMediaPreferencesId preferencesId;
    @Min(1)
    private Double preference;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    public UserMediaPreferences() {
    }

    public UserMediaPreferences(UserMediaPreferencesId preferencesId, Double preference) {
        this.preferencesId = preferencesId;
        this.preference = preference;
    }

    @Embeddable
    @Data
    public static class UserMediaPreferencesId implements Serializable {
        private Long uid;
        private String mediaType;
    }

    public static UserMediaPreferencesId id(Long uid, String mediaType){
        UserMediaPreferencesId id = new UserMediaPreferencesId();
        id.setUid(uid);
        id.setMediaType(mediaType);
        return id;
    }

    public UserMediaPreferences updatePreference(double value){
        preference+=value;
        return this;
    }
}
