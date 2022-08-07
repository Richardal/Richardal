package cn.idcast.dao;

import cn.idcast.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public List<User> findAll();

    User findByUsernameAndPassword(String username,String password);

    void add(User user);

    void delete(int id);

    User ById(int id);

    void update(User user);

/*
* 查询总记录数
* */
    public int findTotalCount(Map<String, String[]> condition);
    /*
    * 分页查询每页的详情
    * */

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

}
