package gatech.cs.buzzcar.common.interceptor;

import gatech.cs.buzzcar.common.Globals;
import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.common.UserHolder;
import gatech.cs.buzzcar.entity.pojo.UserSecurity;
import gatech.cs.buzzcar.utils.JsonUtils;
import gatech.cs.buzzcar.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Objects;


@Component
@Order(1)
@Slf4j
public class AuthTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) throws IOException {
        boolean pass = false;
        if (Objects.nonNull(request)) {
            String requestURI = request.getRequestURI();

            String authToken = request.getHeader(Globals.AUTH_TOKEN);
            if(StringUtils.containsIgnoreCase(requestURI, "/public")){
                if (StringUtils.isNotBlank(authToken)) {
                    boolean validateToken = JwtTokenUtil.validateToken(authToken);
                    if (validateToken) {
                        String username = JwtTokenUtil.getUsernameFromToken(authToken);
                        String role = JwtTokenUtil.getUserRoleFromToken(authToken);
                        UserHolder.setCurrentUser(new UserSecurity(username, role));
                    }
                    pass = true;
                }else{
                    pass = true;
                }
            }
            if (StringUtils.isNotBlank(authToken)) {
                boolean validateToken = JwtTokenUtil.validateToken(authToken);
                if (validateToken) {
                    String username = JwtTokenUtil.getUsernameFromToken(authToken);
                    String role = JwtTokenUtil.getUserRoleFromToken(authToken);
                    UserHolder.setCurrentUser(new UserSecurity(username, role));
                    pass = true;
                }
            }
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(requestAttributes, true);

        if(!pass){
            if(Objects.nonNull(response)){
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JsonUtils.toJson(Result.fail(4001, "Permission Denied")));
            }
        }
        return pass;
    }

    @Override
    public void afterCompletion(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler, Exception ex) {

        if (Objects.nonNull(request)) {
            String authToken = request.getHeader(Globals.AUTH_TOKEN);
            if (StringUtils.isNotBlank(authToken)) {
                UserHolder.clear();
            }
        }
    }
}