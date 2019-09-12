package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import common.filter.wrapper.EncryptWrapper;

@WebFilter(servletNames= {
		"MemberRegister","MemberLoginServlet","MemberLogin"
})
public class EncryptFilter implements Filter {

    public EncryptFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		EncryptWrapper enc = new EncryptWrapper((HttpServletRequest)request);
		chain.doFilter(enc, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
