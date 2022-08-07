package cn.idcast.service.impl;

import cn.idcast.dao.UserDao;
import cn.idcast.dao.impl.UserDaoImpl;
import cn.idcast.domain.PageBean;
import cn.idcast.domain.User;
import cn.idcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findById(String id) {
//就一个功能换成数值，再调用
        return dao.ById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0){
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
      /*  for (String id : ids) {
            dao.delete(Integer.parseInt(id));*/
    }

    @Override
    public PageBean<User> findUserByPage(String rows, String currentPage, Map<String, String[]> condition) {
        int _rows = Integer.parseInt(rows);
        int _currentPage = Integer.parseInt(currentPage);

        if (_currentPage  <= 0){
            _currentPage = 1;
        }

//        创建空的pagebean对象
        PageBean<User> pb = new PageBean<User>();
        pb.setCurrentPage(_currentPage);
        pb.setRows(_rows);
//        调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

//        计算开始记录索引
        int i = (_currentPage - 1) * _rows;
        List<User> list = dao.findByPage(i,_rows,condition);
        pb.setList(list);
        //计算总页码
        int totalPage = (totalCount % _rows) == 0 ? totalCount / _rows : (totalCount / _rows) + 1;
        pb.setTotalPage(totalPage);

        System.out.println(pb);

        return pb;


    }
}
