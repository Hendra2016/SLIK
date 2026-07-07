package common.spring;

import com.bank.dto.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        RestResponse body = new RestResponse();
        body.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        body.setMessage("Unauthorized");
        body.setContents(null);
        body.setTotalRecords(0);

        mapper.writeValue(response.getOutputStream(), body);
    }
}