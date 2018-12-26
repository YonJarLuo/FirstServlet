package com.jiayuan.www.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by LuoYJ on 2018/12/21.
 * 要和web.xml配合使用
 * 登录案例 html--Servlet--service--dao
 * 后续 完善连接数据库JDBC、连接池，去数据库中查用户信息，进行校验
 */
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("LoginServlet.init()");
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求get成功");
        request.getRequestDispatcher("/login.html").forward(request, response);  //转发
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求LoginServlet.doPost()成功");

        String user = request.getParameter("user");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if ("luoyj".equals(user) && "123456".equals(password)){

            System.out.println("密码正确");
            session.setAttribute("user",user);
            session.setAttribute("password",password);
            //重定向写法1
//            response.sendRedirect("/FirstServlet/welcome.html");    //(重定向时)把项目的war放到Tomcat的webapps目录下时，此处需使用绝对路径

            request.getRequestDispatcher("/welcome.html").forward(request, response);  //转发  转发不需要加项目名
        }else{
            //另一种重定向
            /*System.out.println("密码不正确");
            response.setStatus(302);
            response.setHeader("Location","/FirstServlet/login.html");*/

            System.out.println("密码不正确");

            //使用转发
            request.getRequestDispatcher("/login.html").forward(request,response);
        }
    }
}
