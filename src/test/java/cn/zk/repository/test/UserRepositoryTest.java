package cn.zk.repository.test;

import cn.zk.app.App;
import cn.zk.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <br/>
 * Created on 2018/6/3 21:08.
 *
 * @author zhubenle
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGet() {
        System.out.println(userRepository.findAll());;
    }
}
