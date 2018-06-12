package cn.zk.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

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
        log.info("-------ApplicationListener");
    }
}
