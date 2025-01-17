package com.kaynaak.rest.security.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.kaynaak.rest.transform.BaseResponse;
import com.kaynaak.rest.util.JsonMapperUtil;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        BaseResponse coreResponse = new BaseResponse(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED) + " - " + authException.getMessage());
        out.write(JsonMapperUtil.writeValueAsString(coreResponse));
        out.flush();

    }
}

