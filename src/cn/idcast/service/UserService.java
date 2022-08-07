package cn.idcast.service;

import cn.idcast.domain.PageBean;
import cn.idcast.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //查询用户信息
    public List<User> findAll();

    User login(User user);

    void addUser(User user);

    void deleteUser(String id);

    User findById(String id );
    void updateUser(User user);

    void delSelectedUser(String[] ids);


    PageBean<User> findUserByPage(String rows, String currentPage, Map<String, String[]> condition);
}
