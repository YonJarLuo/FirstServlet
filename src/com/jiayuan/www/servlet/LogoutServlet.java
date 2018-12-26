package com.jiayuan.www.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by LuoYJ on 2018/12/26.
 * 退出登录
 * request.getSession(true)：若存在会话则返回该会话，否则新建一个会话。== request.getSession();
 * request.getSession(false)：若存在会话则返回该会话，否则返回NULL
 */
public class LogoutServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String  user = (String) session.getAttribute("user");
        if (user != null){
            session.removeAttribute("user");
            System.out.println("用户退出成功");
        }else{
            System.out.println("用户已退出");
        }
        //重定向到登录页面
        response.sendRedirect("/FirstServlet/login.html");    //(重定向时,此处需使用绝对路径

    }
}
