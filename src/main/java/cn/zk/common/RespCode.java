package cn.zk.common;

/**
 * <br/>
 * Created on 2018/6/16 15:38.
 *
 * @author zhubenle
 */
public enum RespCode {
    /**
     *
     */
    SUCCESS(10000, "成功"),
    USER_NOT_FOUND(10001, "用户不存在"),
    CAN_NOT_DELETE(10002, "admin用户不能删除"),

    SYS_ERROR(99999, "系统异常")
    ;

    RespCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RespCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }
}
