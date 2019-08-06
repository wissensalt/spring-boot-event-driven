package com.wissensalt.rnd.sbed.sd.model;

import com.wissensalt.rnd.sbed.sd.audit.ApplicationAuditorAware;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 4584611646839832573L;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    private ApplicationAuditorAware auditorAware;

    public BaseData() {
        auditorAware = new ApplicationAuditorAware();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedBy
    @Length(max = 150)
    @Column(name = "created_by", length = 150)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_on")
    private Date createdOn;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_on")
    private Date modifiedOn;
}
