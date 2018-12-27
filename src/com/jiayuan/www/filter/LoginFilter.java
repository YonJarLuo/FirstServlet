package com.jiayuan.www.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by LuoYJ on 2018/12/26.
 * 导包注意要导入：javax.servlet.filter
 * 需求：目前项目中的页面有index.jsp,login.html,welcome.html ，我们需要对这些页面的请求进行过滤，判断用户是否登录
 * 没有登录--转发到login.html页面，已登录--直接放行通过
 * 在没有添加过滤器之前，项目启动后，可以直接输入 页面后缀即可进行访问。http:localhost:8080/FirstServlet/welcome.html
 * 与web.xml配置文件进行配合使用
 * 执行顺序：Filter.init()--doFilter()--Servlet--客户端页面
 * 日志包org.slf4j.LoggerFactory，maven中搜索：slf4j
 *
 * 启动项目会执行两次doFilter(),原因排查中
 */
public class LoginFilter implements Filter{
    private  static  final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("执行了LoginFilter.init()方法");
        logger.info("执行了LoginFilter.init()方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("执行了LoginFilter.doFilter()放行前");
        System.out.println("执行了LoginFilter.doFilter()放行前");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取绝对路径
        String currentURL = request.getRequestURI();
        //截取 去掉项目名
        String targetURL = currentURL.substring(currentURL.indexOf("/",1),currentURL.length());
        System.out.println("targetURL:"+targetURL);
        HttpSession session = request.getSession();

        if(!"/login.html".equals(targetURL)){  //如果请求的路径不是login.html，则需要判断是否登录
            System.out.println(session.getAttribute("user"));
            if(session==null||session.getAttribute("user")==null){
                //如果session为空表示用户没有登陆就重定向到login.html页面
                System.out.println("request.getContextPath()=" + request.getContextPath());
                response.sendRedirect(request.getContextPath()+"/login.html");
                return ;
            }else{
                filterChain.doFilter(request,response);
            }
        }else{
            //如果是login.html页面，直接放行
            filterChain.doFilter(request,response);
        }

        System.out.println("执行了LoginFilter.doFilter()放行后");
        System.out.println("--------------------------------------");
        logger.info("执行了LoginFilter.doFilter()放行后");
    }

    @Override
    public void destroy() {
        logger.info("执行了LoginFilter.destroy()方法");
    }
}
