package util.auth;

import entity.Video;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Create by czq
 * time on 2019/8/30  22:51
 */
public class AuthUtils {
    private static final String ROLE_KEY = "ROLE_KEY";

    public static String getRole() {
        return session().map(s -> String.valueOf(s.getAttribute(ROLE_KEY))).orElse("");
    }


    public static void setRole(final String role) {
        session().orElseThrow().setAttribute(ROLE_KEY, role);
    }


    private static Optional<HttpSession> session() {
        RequestAttributes attr = RequestContextHolder.getRequestAttributes();
        if (attr instanceof ServletRequestAttributes) {
            ServletRequestAttributes rAttr = (ServletRequestAttributes) attr;
            return Optional.ofNullable(rAttr.getRequest().getSession());
        }
        return Optional.empty();
    }


}
