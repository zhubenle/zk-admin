package cn.zk.controller;

import cn.zk.app.intercepter.UserSessionIntercepter;
import cn.zk.entity.User;
import cn.zk.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

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

    private final AdminService adminService;

    @PostMapping(value = "/sign_in")
    public String signIn(HttpSession httpSession, User user, RedirectAttributesModelMap modelMap) {
        Optional<User> optionalUser = adminService.signIn(user);
        if (optionalUser.isPresent()) {
            User loginUser = optionalUser.get();
            httpSession.setAttribute(UserSessionIntercepter.SESSION_USER, loginUser);
            log.info("用户{} 登录成功", loginUser.getUsername());
            return "redirect:index";
        }
        modelMap.addFlashAttribute("loginFailMessage", "登录失败");
        return "redirect:login";
    }

    @PostMapping(value = "/sign_out")
    public String signOut(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(UserSessionIntercepter.SESSION_USER);
        httpSession.removeAttribute(UserSessionIntercepter.SESSION_USER);
        log.info("用户{} 退出登录", user.getUsername());
        return "redirect:login";
    }

    @GetMapping(value = "/login")
    public String toLogin(HttpServletRequest request, @ModelAttribute("loginFailMessage") String loginFailMessage) {
        return "login";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "views/index";
    }

    @GetMapping(value = "/users")
    public String users(Integer page, Integer pageSize, ModelMap modelMap) {
//        modelMap.addAttribute("users", adminService.listUserByPage(page, pageSize));
        return "views/users";
    }
}
