package cn.idcast.web;

import cn.idcast.domain.User;
import cn.idcast.service.UserService;
import cn.idcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.设置编码
        req.setCharacterEncoding("utf-8");
//        2.获取数据
        String s = req.getParameter("verifycode");

//        3，验证码校验

        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码的一次性
        if (!checkcode_server.equalsIgnoreCase(s) && checkcode_server !=null && s !=null){
            req.setAttribute("login_msg","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }


//        4，封装user对象
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try{
            BeanUtils.populate(user,map);
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(InvocationTargetException e ){
            e.printStackTrace();
        }
//        5，调用service查询
        UserService userService = new UserServiceImpl();
        User login = userService.login(user);
        //获取用户名和密码

//        6，判断登录成功
        if (login !=null){
            session.setAttribute("user",login);
            resp.sendRedirect(req.getContextPath() +"/index.jsp");
        }else{
            req.setAttribute("login_msg","用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
