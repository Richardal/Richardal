package cn.idcast.web;

import cn.idcast.domain.PageBean;
import cn.idcast.domain.User;
import cn.idcast.service.UserService;
import cn.idcast.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class findUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      1.接受请求参数
        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");


        if(currentPage ==null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null ||"".equals(rows)) {
            rows = "5";
        }

        Map<String, String[]> condition = req.getParameterMap();
//        2.调用service
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(rows, currentPage,condition);
//        3.将page存入request域对象
        req.setAttribute("pb",pb);
//        4.妆发到list.jsp
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
