package com.wissensalt.rnd.sbed.util.aspect;

import java.lang.annotation.*;

/**
 *
 * This annotation is used to log {@link javax.servlet.http.HttpServletRequest}
 * and {@link javax.servlet.http.HttpServletResponse} from/ to client.
 * Every http request and http response will be recorded within database as JSON value.
 *
 *     In order to enable this annotation, your configuration class must have :
 *     <ol>
 *         <li>A Bean of {@link org.springframework.web.servlet.DispatcherServlet}</li>
 *         <li>Put this annotation inside {@link org.springframework.web.bind.annotation.RestController} class on top of rest mapping method.</li>
 *     </ol>
 *     Parameter {@code name} is not mandatory
 *
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLogger {

    /**
     * @return the name of end point/ function
     */
    String name() default "";
}
