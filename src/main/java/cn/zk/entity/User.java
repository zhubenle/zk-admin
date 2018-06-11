package cn.zk.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <br/>
 * Created on 2018/6/3 21:05.
 *
 * @author zhubenle
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "del", nullable = false, length = 2)
    private Byte del;

}
