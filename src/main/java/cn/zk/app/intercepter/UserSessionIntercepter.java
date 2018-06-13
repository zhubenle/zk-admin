package cn.zk.app.intercepter;

import cn.zk.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 会话拦截器<br/>
 * Created on 2018/6/3 13:53.
 *
 * @author zhubenle
 */
@Component
public class UserSessionIntercepter extends HandlerInterceptorAdapter {

    public final static String SESSION_USER = "session_user";
    private final static String USER_PATH = "user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute(SESSION_USER);
        if (Objects.isNull(user)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        if (user.getId() != 1 && request.getServletPath().contains(USER_PATH)) {
            response.sendRedirect(request.getContextPath() + "/error");
            return false;
        }
        return true;
    }
}