package cn.zk.manager.observer;

import cn.zk.service.ZkInfoService;
import cn.zk.websocket.ZkStateMessageHandler;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    private final ZkStateMessageHandler zkStateMessageHandler;

    @Override
    public void update(Observable o, Object arg) {
        ObserverDTO dto = (ObserverDTO) arg;
        if (!dto.getType().equals(ObserverDTO.Type.CONN_STATE)) {
            return;
        }
        log.warn("连接状态改变, 最新连接状态: {}", dto.getConnState());
        zkInfoService.updateZkInfoConnStateByHosts(dto.getConnStr(), dto.getConnState().toString());
        zkStateMessageHandler.sendMessage(JSONObject.toJSONString(dto));
    }
}
