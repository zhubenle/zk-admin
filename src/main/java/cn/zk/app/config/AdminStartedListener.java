package cn.zk.app.config;

import cn.zk.entity.ZkInfo;
import cn.zk.manager.AbstractCuratorManager;
import cn.zk.manager.DefaultCuratorManager;
import cn.zk.manager.factory.CuratorManagerFactory;
import cn.zk.manager.observer.ConnStateObserver;
import cn.zk.service.ZkInfoService;
import cn.zk.websocket.ZkStateMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * <br/>
 * Created on 2018/6/12 14:39.
 *
 * @author zhubenle
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AdminStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private final ZkInfoService zkInfoService;
    private final CuratorManagerProperties curatorClientProperties;
    private final CuratorManagerFactory curatorManagerFactory;
    private final ZkStateMessageHandler zkStateMessageHandler;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        Map<String, AbstractCuratorManager> managerMap = zkInfoService.listAll()
                .stream()
                .collect(Collectors.toMap(ZkInfo::getAlias, o -> {
                    DefaultCuratorManager curatorManager = new DefaultCuratorManager(o.getHosts(), curatorClientProperties);
                    curatorManager.addObserver(new ConnStateObserver(zkInfoService, zkStateMessageHandler));
                    return curatorManager;
                }));
        if (!managerMap.isEmpty()) {
            log.info("启动加载{} zookeeper管理端", managerMap.keySet().toString());
            curatorManagerFactory.setManagerMap(managerMap);
        }
    }
}
