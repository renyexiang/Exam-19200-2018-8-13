package filter;

import Entity.Customer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class loginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();


        String userName=(String )session.getAttribute("userName");
        if(userName!=null){
            chain.doFilter(request,response);
        } else{
            out.println("您还未登陆，三秒钟后跳转至登录页面");
            response.setHeader("refresh","3;/login.jsp");

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
