package cn.zk.repository;

import cn.zk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <br/>
 * Created on 2018/6/3 21:06.
 *
 * @author zhubenle
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查找用户
     *
     * @param username
     *         用户名
     *
     * @return 用户对象
     */
    User findByEmailEquals(String username);
}
