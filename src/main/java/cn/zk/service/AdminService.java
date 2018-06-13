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
     * @param page
     *         页码
     * @param pageSize
     *         每页数量
     *
     * @return 分页对象
     */
    Page<User> listUserByPage(Integer page, Integer pageSize);

    /**
     * 添加获取按照主键更新用户
     *
     * @param user
     *         用户对象
     *
     * @return 添加或者更新后的对象
     */
    User saveOrUpdateUser(User user);
}
