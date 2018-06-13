package cn.zk.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * <br/>
 * Created on 2018/6/12 14:44.
 *
 * @author zhubenle
 */
@Data
@Entity
public class ZkInfo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "alias", nullable = false, length = 100)
    private String alias;

    @Column(name = "hosts", nullable = false, length = 1024)
    private String hosts;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
