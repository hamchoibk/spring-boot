package com.kaynaak.rest.security.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaynaak.rest.common.ResponseDescription;
import com.kaynaak.rest.transform.CoreResponse;
import com.kaynaak.rest.util.JsonMapper;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

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
        CoreResponse coreResponse = new CoreResponse(ResponseDescription.ERROR_STATUS, String.valueOf(HttpServletResponse.SC_UNAUTHORIZED) + " - " + authException.getMessage());
        out.write(JsonMapper.writeValueAsString(coreResponse));
        out.flush();

    }
}

