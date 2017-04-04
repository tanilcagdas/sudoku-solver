
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.lang.StringUtils;

public class CustomCORSFilter implements Filter {

	private static final String ORIGIN = "Origin";

	public CustomCORSFilter() {
	}

	public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;

		if (req.getHeader(ORIGIN) != null) {

			response.setHeader("Access-Control-Allow-Origin", req.getHeaders("origin").nextElement().toString());

			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");

			String reqHead = req.getHeader("Access-Control-Request-Headers");

			// if (!StringUtils.isEmpty(reqHead)) {
			if (reqHead != null && reqHead.trim().length() > 0) {
				response.addHeader("Access-Control-Allow-Headers", reqHead);
			}
		}

		if (req.getMethod().equals("OPTIONS")) {
			try {
				response.getWriter().print("OK");
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			chain.doFilter(req, response);
		}
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpServletRequest req = (HttpServletRequest) arg0;

		if (req.getHeader(ORIGIN) != null) {

			response.setHeader("Access-Control-Allow-Origin", req.getHeaders("origin").nextElement().toString());

			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");

			String reqHead = req.getHeader("Access-Control-Request-Headers");

			// if (!StringUtils.isEmpty(reqHead)) {
			if (reqHead != null && reqHead.trim().length() > 0) {
				response.addHeader("Access-Control-Allow-Headers", reqHead);
			}
		}

		if (req.getMethod().equals("OPTIONS")) {
			try {
				response.getWriter().print("OK");
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			chain.doFilter(req, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
