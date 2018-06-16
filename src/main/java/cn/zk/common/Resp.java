package cn.zk.common;

import java.time.LocalDateTime;

/**
 * <br/>
 * Created on 2018/6/16 15:32.
 *
 * @author zhubenle
 */
public class Resp<T> {

    private Integer code;
    private String msg;
    private LocalDateTime time;
    private T data;

    public void success(T data) {
        this.code = RespCode.SUCCESS.getCode();
        this.msg = RespCode.SUCCESS.getMsg();
        time = LocalDateTime.now();
        this.data = data;
    }

    public void fail(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
        this.time = LocalDateTime.now();
    }

    public void fail(AdminException e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
        this.time = LocalDateTime.now();
    }

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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
