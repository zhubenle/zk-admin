package cn.zk.app.config;

import cn.zk.manager.factory.CuratorManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created on 2018/6/12 14:39.
 *
 * @author zhubenle
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AdminStoppedListener implements ApplicationListener<ContextClosedEvent> {

    private final CuratorManagerFactory curatorManagerFactory;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        curatorManagerFactory.getManagerMap().forEach((key, value) -> value.close());
        log.info("关闭zookeeper管理端");
    }
}
