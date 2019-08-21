package com.wissensalt.rnd.sbed.util.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sbed.sd.dto.request.ContentAPIRequestData;
import com.wissensalt.rnd.sbed.sd.dto.request.ContentAPIResponseData;
import com.wissensalt.rnd.sbed.sd.dto.request.RequestAPILogDTO;
import com.wissensalt.rnd.sbed.sd.model.HttpLog;
import com.wissensalt.rnd.sbed.util.dao.IHttpLogDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Component
public abstract class ARequestLoggerAspect implements IRequestLoggerAspect {

    private final ObjectMapper objectMapper;
    private final IHttpLogDAO httpLogDAO;

    @Override
    public Object logRequest(ProceedingJoinPoint joinPoint, RequestLogger requestLogger) throws Throwable {
        HttpLog httpLog = new HttpLog();
        RequestAPILogDTO requestAPILogDTO = new RequestAPILogDTO();
        StringBuilder apiLog = new StringBuilder();
        apiLog.append(requestLogger.name()).append("\n");

        requestAPILogDTO.setEndPointName(requestLogger.name());
        requestAPILogDTO.setTimeStamp(new Date());

        ContentCachingRequestWrapper request = getWrapper(joinPoint);

        ContentAPIRequestData apiRequestData = new ContentAPIRequestData();

        List<String> cookieRequest = new ArrayList<>();

        Object retVal;
        retVal = joinPoint.proceed();

        if (request != null) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies() ) {
                    String cookieContent = cookie.getName().concat(":").concat(cookie.getValue());
                    cookieRequest.add(cookieContent);
                    apiLog.append(cookieContent).append("\n");
                }
            }
            apiRequestData.setCookies(cookieRequest);

            List<String> headerRequest = new ArrayList<>();

            for (String header : Collections.list(request.getHeaderNames())) {
                String headerContent = header.concat(":").concat(request.getHeader(header));
                headerRequest.add(headerContent);
                apiLog.append(headerContent).append("\n");
            }
            apiRequestData.setHeaders(headerRequest);
            apiRequestData.setRemoteAddress(request.getRemoteAddr());
            apiRequestData.setRemotePort(request.getRemotePort());
            apiRequestData.setRemoteUser(request.getRemoteUser());
            apiRequestData.setMethod(request.getMethod());
            apiRequestData.setRequestUrl(request.getRequestURI());

            String requestBody = getRequestBody(request);

            apiRequestData.setPayload(requestBody);
            requestAPILogDTO.setRequestData(apiRequestData);

            apiLog.append("method ").append(request.getMethod()).append("\n");
            apiLog.append("auth type ").append(request.getAuthType()).append("\n");
            apiLog.append("remote Address ").append(request.getRemoteAddr()).append("\n");
            apiLog.append("Remote user ").append(request.getRemoteUser()).append("\n");
            apiLog.append("remote host ").append(request.getRemoteHost()).append("\n");
            apiLog.append("remote port ").append(request.getRemotePort()).append("\n");
            apiLog.append("Request API: ").append(request.getRequestURL().toString()).append("\n");
            apiLog.append("Payload :").append(getRequestBody(request)).append("\n");

            ContentAPIResponseData responseData = new ContentAPIResponseData();
            try {
                responseData.setBody(objectMapper.writeValueAsString(retVal));
            } catch (JsonProcessingException e) {
                log.debug("Error Parsing Response Data {}", e.toString());
            }
            responseData.setHost(request.getRemoteHost());
            requestAPILogDTO.setResponseData(responseData);
        }


        apiLog.append("Response:").append(objectMapper.writeValueAsString(retVal));
        log.debug(apiLog.toString());

        httpLog.setEndPointName(requestAPILogDTO.getEndPointName());
        httpLog.setRequestTime(requestAPILogDTO.getTimeStamp());
        httpLog.setRequest(objectMapper.writeValueAsString(requestAPILogDTO.getRequestData()));
        httpLog.setResponse(objectMapper.writeValueAsString(requestAPILogDTO.getResponseData()));
        httpLogDAO.save(httpLog);

        return retVal;
    }


    @Override
    public String getRequestBody(ContentCachingRequestWrapper wrapper) {
        String payload = null;
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    int maxLength = buf.length > 500 ? 500 : buf.length;

                    payload = new String(buf, 0, maxLength, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    log.error("UnsupportedEncoding : {}", e.toString());
                }
            }
        }
        return payload;
    }

    @Override
    public ContentCachingRequestWrapper getWrapper(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        ContentCachingRequestWrapper request = null;

        for (Object arg : args) {
            if (arg instanceof ContentCachingRequestWrapper) {
                request = (ContentCachingRequestWrapper) arg;
                break;
            }
        }
        return request;
    }

}
