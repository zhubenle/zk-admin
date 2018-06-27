package cn.zk.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "别名不能为空")
    @Column(name = "alias", nullable = false, length = 100)
    private String alias;

    @NotNull(message = "地址不能为空")
    @Column(name = "hosts", nullable = false, length = 1024)
    private String hosts;

    @Column(name = "conn_state", nullable = true)
    private String connState;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
