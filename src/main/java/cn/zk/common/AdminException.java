package cn.zk.common;

/**
 * <br/>
 * Created on 2018/6/16 15:36.
 *
 * @author zhubenle
 */
public class AdminException extends RuntimeException{
    private static final long serialVersionUID = -5034237239389579799L;

    private Integer code;
    private String msg;

    public AdminException(RespCode respCode) {
        super(respCode.toString());
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public AdminException(RespCode respCode, Throwable cause) {
        super(respCode.toString(), cause);
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCodeMsg() {
        return code + ":" + msg;
    }
}
