package cn.zk.controller;

import cn.zk.entity.User;
import cn.zk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <br/>
 * Created on 2018/6/3 21:48.
 *
 * @author zhubenle
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class AdminController {

    private final UserRepository userRepository;

    @PostMapping(value = "/sign_in")
    public String signIn(User user, ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        return "login";
    }

    @GetMapping(value = "/login")
    public String toLogin(ModelMap modelMap) {
        return "login";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }
}
