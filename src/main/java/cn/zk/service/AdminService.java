package cn.zk.service;

import cn.zk.entity.User;
import org.springframework.data.domain.Page;

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

    /**
     * 分页获取用户列表
     *
     * @param pageNo
     *         页码
     * @param pageSize
     *         每页数量
     *
     * @return 分页对象
     */
    Page<User> listUserByPage(Integer pageNo, Integer pageSize);

    /**
     * 添加用户
     *
     * @param user
     *         用户对象
     */
    void saveUser(User user);
}
