package util.auth;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Create by czq
 * time on 2019/8/30  23:02
 */
@Aspect
@Component
public class AuthAspect {


    @Before("@annotation(RequireRole)")
    public void doIntercept(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        RequireRole annotation = signature.getMethod().getAnnotation(RequireRole.class);
        String role = annotation.value();
        String userRole = AuthUtils.getRole();
        if (!Objects.equals(role, userRole)) {
            throw new AuthException("没有权限");
        }
    }


}
