package cn.zk.manager.observer;

import cn.zk.service.ZkInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.state.ConnectionState;

import java.util.Observable;
import java.util.Observer;

/**
 * <br/>
 * Created on 2018/6/27 11:01.
 *
 * @author zhubenle
 */
@Slf4j
@RequiredArgsConstructor
public class ConnStateObserver implements Observer {

    private final ZkInfoService zkInfoService;

    @Override
    public void update(Observable o, Object arg) {
        ConnStateObserverDTO dto = (ConnStateObserverDTO) arg;
        log.warn("连接状态改变, 最新连接状态: {}", dto.getConnState());
        if (ConnectionState.LOST.equals(dto.getConnState())) {
            //连接丢失, 同时更新ZkInfo状态

        }
    }
}
