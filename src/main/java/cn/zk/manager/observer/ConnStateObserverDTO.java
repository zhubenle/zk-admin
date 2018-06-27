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
public class ConnStateObserverDTO {
    private final ConnectionState connState;
    private final String connStr;
}
