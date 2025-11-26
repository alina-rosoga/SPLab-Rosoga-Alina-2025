package sp.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

@Component
public class RequestLoggingFilter implements Filter {

    private final String logFile = "requests.log";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no-op
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String before = String.format("%s - START %s %s\n", Instant.now(), req.getMethod(), req.getRequestURI());
        appendLog(before);
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;
        String after = String.format("%s - END %s %s (%d ms)\n", Instant.now(), req.getMethod(), req.getRequestURI(), duration);
        appendLog(after);
    }

    private synchronized void appendLog(String line) {
        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write(line);
        } catch (IOException e) {
            // ignore logging failures
        }
    }

    @Override
    public void destroy() {
        // no-op
    }
}
