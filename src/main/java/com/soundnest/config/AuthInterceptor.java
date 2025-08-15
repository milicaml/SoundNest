package com.soundnest.config;


import com.soundnest.model.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final SessionUser sessionUser;

    public AuthInterceptor(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String path = request.getRequestURI();

        if (path.startsWith("/login") || path.startsWith("/register") ||
                path.startsWith("/css") || path.startsWith("/js")) {
            if (sessionUser.isLogged() && (path.equals("/login") || path.equals("/register"))) {
                response.sendRedirect(request.getContextPath() + "/");
                return false;
            }
            return true;
        }

        if (!sessionUser.isLogged()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}