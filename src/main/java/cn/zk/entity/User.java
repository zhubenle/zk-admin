package cn.zk.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @NotNull(message = "用户名不能为空")
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @NotNull(message = "邮箱不能为空")
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotNull(message = "密码不能为空")
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
