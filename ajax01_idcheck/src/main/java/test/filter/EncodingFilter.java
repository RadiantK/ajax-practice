package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//서블릿 실행 전
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response);
		
		// 서블릿 실행 후 
	}

}
