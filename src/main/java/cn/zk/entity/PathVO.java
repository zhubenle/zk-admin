package cn.zk.entity;

import lombok.Data;

import java.util.List;

/**
 * <br/>
 * Created on 2018/6/17 19:26.
 *
 * @author zhubenle
 */
@Data
public class PathVO {

    private String id;
    private String name;
    private Boolean isParent;
    private Boolean open;
    private List<PathVO> children;

    public PathVO(String name, Boolean isParent) {
        this.name = name;
        this.isParent = isParent;
    }
}
