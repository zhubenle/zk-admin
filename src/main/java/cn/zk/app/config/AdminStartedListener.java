package cn.zk.app.config;

import cn.zk.entity.ZkInfo;
import cn.zk.manager.AbstractCuratorManager;
import cn.zk.manager.DefaultCuratorManager;
import cn.zk.manager.factory.CuratorManagerFactory;
import cn.zk.service.ZkInfoService;
import cn.zk.service.impl.ZkInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * <br/>
 * Created on 2018/6/12 14:39.
 *
 * @author zhubenle
 */
@Slf4j
public class AdminStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ZkInfoService zkInfoService = event.getApplicationContext().getBean(ZkInfoServiceImpl.class);
        CuratorClientProperties curatorClientProperties = event.getApplicationContext().getBean(CuratorClientProperties.class);
        Map<String, AbstractCuratorManager> managerMap = zkInfoService.listAll()
                .stream()
                .collect(Collectors.toMap(ZkInfo::getAlias, o -> new DefaultCuratorManager(o.getHosts(), curatorClientProperties)));
        if (!managerMap.isEmpty()) {
            CuratorManagerFactory curatorManagerFactory = event.getApplicationContext().getBean(CuratorManagerFactory.class);
            curatorManagerFactory.setManagerMap(managerMap);
        }
    }
}
