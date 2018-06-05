package cn.zk.controller;

import cn.zk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br/>
 * Created on 2018/6/3 21:48.
 *
 * @author zhubenle
 */
@Controller
public class TestController {

    private final UserRepository userRepository;

    @Autowired
    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        userRepository.findAll().forEach(System.out::println);
        return "success";
    }
}
