package cn.zk.app;

import cn.zk.app.config.AdminStartedListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <br/>
 * Created on 2018/6/3 13:53.
 *
 * @author zhubenle
 */
@SpringBootApplication
@ComponentScan(value = {"cn.zk"})
@EntityScan(value = {"cn.zk.entity"})
@EnableJpaRepositories(value = {"cn.zk.repository"})
public class App {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.addListeners(new AdminStartedListener());
        application.run(args);
    }
}
