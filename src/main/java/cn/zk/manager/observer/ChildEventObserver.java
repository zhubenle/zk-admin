package cn.zk.manager.observer;

import cn.zk.websocket.ZkChildEventMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ChildEventObserver implements Observer {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChildEventObserver.class);

    private final ZkChildEventMessageHandler zkChildEventMessageHandler;

    @Override
    public void update(Observable o, Object arg) {
        ObserverDTO dto = (ObserverDTO) arg;
        if (!dto.getType().equals(ObserverDTO.Type.CHILD_EVENT)) {
            return;
        }
        String path = dto.getPath();
        LOGGER.info("Zookeeper节点{}发生变化", path);
        zkChildEventMessageHandler.sendMessage(path);
    }
}
