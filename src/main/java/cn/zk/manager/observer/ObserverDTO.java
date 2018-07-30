package cn.zk.manager.observer;

import lombok.Data;
import org.apache.curator.framework.state.ConnectionState;

/**
 * <br/>
 * Created on 2018/6/27 11:16.
 *
 * @author zhubenle
 */
@Data
public class ObserverDTO {

    private Type type;
    private ConnectionState connState;
    private String connStr;
    private String path;

    public ObserverDTO(Type type, ConnectionState connState, String connStr) {
        this.type = type;
        this.connState = connState;
        this.connStr = connStr;
    }

    public ObserverDTO(Type type, String path) {
        this.type = type;
        this.path = path;
    }

    public enum Type{
        /**
         *
         */
        CONN_STATE, CHILD_EVENT
    }
}
