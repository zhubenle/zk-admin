package cn.zk.service;

import cn.zk.entity.User;

import java.util.Optional;

/**
 * <br/>
 * Created on 2018/6/10 19:36.
 *
 * @author zhubenle
 */
public interface AdminService {

    /**
     * 登录
     *
     * @param user
     *         登录对象
     *
     * @return 登录成功返回对象
     */
    Optional<User> signIn(User user);
}
