package com.jiayuan.www;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LuoYJ on 2018/12/21.
 * 要和web.xml配合使用
 * 登录案例 html--Servlet--service--dao
 * 后续 完善连接数据库JDBC、连接池
 */
public class LoginServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求get成功");
        request.getRequestDispatcher("/login.html").forward(request, response);  //转发
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求post成功");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        if ("luoyj".equals(user) && "123456".equals(password)){
            //重定向，跳到欢迎界面
            System.out.println("密码正确");
            response.sendRedirect("/FirstServlet/welcome.html");    //把项目的war放到Tomcat的webapps目录下时，此处需使用绝对路径
//            response.sendRedirect("/welcome.html");
        }else{
            //另一种重定向
            System.out.println("密码不正确");
            response.setStatus(302);
            response.setHeader("Location","/FirstServlet/login.html");   //把项目的war放到Tomcat的webapps目录下时，此处需使用绝对路径
//            response.setHeader("Location","/login.html");
        }
    }
}
