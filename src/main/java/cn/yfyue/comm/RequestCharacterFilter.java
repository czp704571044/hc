package cn.yfyue.comm;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

/**
 *  字符集过滤器
 *  encoding 字符编码类型，如GBK,UTF-8
 *  forceEncoding 是否总是对请求进行字符集编码
 *  forceEncoding=true，每次都对请求进行编码设置
 *  forceEncoding=false 只有才请求还没有设置编码类型时才进行编码设置
 *  @author yy
 *
 */
public class RequestCharacterFilter implements Filter {
    private FilterConfig filterConfig;
    private String encoding;
    private boolean forceEncoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        this.forceEncoding = Boolean.parseBoolean(this.filterConfig.getInitParameter("forceEncoding"));
    }

    public void setFilterConfig(final FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

//Clean up resources
    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException,
            ServletException {
        response.setCharacterEncoding(this.encoding);
        if (this.forceEncoding || request.getCharacterEncoding() == null) {
            if (((HttpServletRequest) request).getMethod().equalsIgnoreCase(
                    "post")) {
                request.setCharacterEncoding(this.encoding);
            } else {
                Map param = request.getParameterMap();
                Iterator keys = param.keySet().iterator();
                while (keys.hasNext()) {
                    String[] temp = (String[]) param.get(keys.next());
                    for (int i = 0; i < temp.length; i++) {
                        try {
                            temp[i] = new String(temp[i].getBytes("iso-8859-1"),
                                                 this.encoding);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
