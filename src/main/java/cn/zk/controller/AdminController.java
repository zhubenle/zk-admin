package cn.zk.controller;

import cn.zk.app.intercepter.UserSessionIntercepter;
import cn.zk.common.AdminException;
import cn.zk.common.Resp;
import cn.zk.common.RespCode;
import cn.zk.entity.User;
import cn.zk.service.AdminService;
import cn.zk.service.ZkInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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

    private final static String LOGIN_FAIL_MESSAGE = "loginFailMessage";
    private final static String USER_FAIL_MESSAGE = "userFailMessage";

    private final AdminService adminService;
    private final ZkInfoService zkInfoService;

    @PostMapping(value = "/sign_in")
    public String signIn(HttpSession httpSession, User user, RedirectAttributesModelMap modelMap) {
        Optional<User> optionalUser = adminService.signIn(user);
        if (optionalUser.isPresent()) {
            User loginUser = optionalUser.get();
            httpSession.setAttribute(UserSessionIntercepter.SESSION_USER, loginUser);
            log.info("用户{} 登录成功", loginUser.getUsername());
            return "redirect:index";
        }
        modelMap.addFlashAttribute(LOGIN_FAIL_MESSAGE, "登录失败, 用户名或密码错误");
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
    public String toLogin(ModelMap modelMap, @ModelAttribute(LOGIN_FAIL_MESSAGE) String loginFailMessage) {
        log.error("loginFailMessage: {}", loginFailMessage);
        modelMap.addAttribute(LOGIN_FAIL_MESSAGE, loginFailMessage);
        return "login";
    }

    @GetMapping(value = "/index")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("zkinfos", zkInfoService.listAll());
        return "views/index";
    }

    @GetMapping(value = "/users")
    public String users(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                        @ModelAttribute(USER_FAIL_MESSAGE) String userFailMessage,
                        ModelMap modelMap) {
        modelMap.addAttribute("users", adminService.listUserByPage(page, pageSize));
        modelMap.addAttribute("zkinfos", zkInfoService.listAll());
        modelMap.addAttribute(USER_FAIL_MESSAGE, userFailMessage);
        return "views/users";
    }

    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public Resp<User> getUser(@PathVariable(value = "id") Integer id) {
        Resp<User> userResp = new Resp<>();
        try {
            userResp.success(adminService.getUserById(id));
        } catch (AdminException e) {
            log.error("获取用户失败: {}", e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("获取用户异常", e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }

    @DeleteMapping(value = "/user/{id}")
    @ResponseBody
    public Resp<String> deleteUser(@PathVariable(value = "id") Integer id) {
        Resp<String> userResp = new Resp<>();
        try {
            adminService.deleteUserById(id);
            userResp.success(null);
        } catch (AdminException e) {
            log.error("删除用户失败: {}", e.getCodeMsg());
            userResp.fail(e);
        } catch (Exception e) {
            log.error("删除用户异常", e);
            userResp.fail(RespCode.ERROR_99999);
        }
        return userResp;
    }

    @PostMapping(value = "/user")
    public String saveOrUpdateUser(User user, RedirectAttributesModelMap modelMap) {
        try {
            adminService.saveOrUpdateUser(user);
        } catch (AdminException e) {
            log.error("添加或更新用户失败: {}", e.getCodeMsg());
            modelMap.addFlashAttribute(USER_FAIL_MESSAGE, e.getCodeMsg());
        } catch (Exception e) {
            log.error("添加或更新添加用户异常", e);
            modelMap.addFlashAttribute(USER_FAIL_MESSAGE, e.getMessage());
        }
        return "redirect:users";
    }
}
