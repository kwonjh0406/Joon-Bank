package com.kwonjh0406.joon_bank.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ParameterValidationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String[] parameterNames = request.getParameterMap().keySet().toArray(new String[0]);

        for (String paramName : parameterNames) {
            String paramValue = request.getParameter(paramName);
            if (paramValue == null || paramValue.trim().isEmpty()) {
                // 빈 값이 있는 경우 오류 반환
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "모든 필드를 입력해주세요.");
                return false; // 요청을 중단
            }
        }
        return true; // 모든 파라미터가 유효한 경우 요청을 계속 진행
    }
}

