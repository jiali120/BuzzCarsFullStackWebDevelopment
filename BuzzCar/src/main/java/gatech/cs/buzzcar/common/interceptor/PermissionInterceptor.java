package gatech.cs.buzzcar.common.interceptor;

import gatech.cs.buzzcar.common.annotation.HasPermission;
import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.utils.JsonUtils;
import gatech.cs.buzzcar.utils.UserUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@Order(2)
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@Nullable HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) throws Exception {
        if (this.hasPermission(handler)) {
            return true;
        }
        assert response != null;
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtils.toJson(Result.fail(6001, "privilege not granted")));
        return false;
    }


    private boolean hasPermission(Object handler) {

        if (handler instanceof HandlerMethod handlerMethod) {
            HasPermission hasPermission = handlerMethod.getMethod().getAnnotation(HasPermission.class);
            if (hasPermission == null) {
                hasPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(HasPermission.class);
            }
            if (hasPermission == null) {
                return true;
            }
            String value = hasPermission.value();
            if (StringUtils.isNotBlank(value)) {
                String userRole = UserUtils.getUserRole();
                return StringUtils.containsIgnoreCase(value, userRole);
            }else{
                return true;
            }
        }
        return false;
    }


}
