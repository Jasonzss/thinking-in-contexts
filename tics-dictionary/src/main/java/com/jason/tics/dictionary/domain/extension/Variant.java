package com.jason.tics.dictionary.domain.extension;

import com.jason.tics.dictionary.constraints.IsWord;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 变体词
 * do - doing - done - does - did
 *
 * @author Jason
 */
@Getter
@Setter
@ToString
@Table
@Entity
public class Variant {
    @Id
    @IsWord
    private String target;
    private String variantsGroupName;
    private String desc;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateTime;

    public Variant() {
    }

    public Variant(String target, String variantsGroupName, String desc) {
        this.target = target;
        this.variantsGroupName = variantsGroupName;
        this.desc = desc;
    }
}
