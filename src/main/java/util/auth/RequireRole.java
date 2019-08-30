package util.auth;

import java.lang.annotation.*;

/**
 * Create by czq
 * time on 2019/8/30  22:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequireRole {
    String value();
}
