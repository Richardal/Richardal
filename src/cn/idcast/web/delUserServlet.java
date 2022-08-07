package cn.idcast.web;

import cn.idcast.service.UserService;
import cn.idcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserServlet")
public class delUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取id
        String id = req.getParameter("id");
//        2.调用service删除
        UserService service = new UserServiceImpl();
        service.deleteUser(id);
//        3.跳转到查询页面
        resp.sendRedirect(req.getContextPath()+"/UserListServlet");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
