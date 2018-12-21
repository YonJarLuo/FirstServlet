package com.jiayuan.www;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by LuoYJ on 2018/12/21.
 */
public class HelloServlet extends HttpServlet {
    private String message ;

    public void init(){
        message = "Hello First Servlet for IDEA";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置:响应内容类型
        response.setContentType("text/html");

        // 输出文本
        PrintWriter out = response.getWriter();
        out.write("<h1> " + message + " </h1>");


        //使用重定向
        /*response.setStatus(302);
        response.setHeader("Location","http://www.baidu.com");*/

    }
}
