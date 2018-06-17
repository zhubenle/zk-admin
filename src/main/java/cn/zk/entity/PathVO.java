package cn.zk.entity;

import java.util.List;

/**
 * <br/>
 * Created on 2018/6/17 19:26.
 *
 * @author zhubenle
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PathVO withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PathVO withName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public PathVO withIsParent(Boolean isParent) {
        this.isParent = isParent;
        return this;
    }

    public List<PathVO> getChildren() {
        return children;
    }

    public void setChildren(List<PathVO> children) {
        this.children = children;
    }

    public PathVO withChildren(List<PathVO> children) {
        this.children = children;
        return this;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public PathVO withOpen(Boolean open) {
        this.open = open;
        return this;
    }

    @Override
    public String toString() {
        return "PathVO{" +
                "name='" + name + '\'' +
                ", isParent=" + isParent +
                ", open=" + open +
                ", children=" + children +
                '}';
    }
}
