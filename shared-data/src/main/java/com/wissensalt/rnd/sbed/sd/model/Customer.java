package com.wissensalt.rnd.sbed.sd.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-02
 **/
@Getter
@Setter
@Table(name = "mst_customer")
@Entity
public class Customer extends BaseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -4924510656747843108L;

    @Column(name = "user_name", unique = true)
    private String userName;
}
