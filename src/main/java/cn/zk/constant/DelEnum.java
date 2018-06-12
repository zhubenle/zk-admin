package cn.zk.constant;

/**
 * <br/>
 * Created on 2018/6/12 15:26.
 *
 * @author zhubenle
 */
public enum DelEnum {

    /**
     *
     */
    DEL("删除", (byte) 1),
    UN_DEL("未删除", (byte) 0)
    ;

    DelEnum(String name, Byte value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private Byte value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }
}
