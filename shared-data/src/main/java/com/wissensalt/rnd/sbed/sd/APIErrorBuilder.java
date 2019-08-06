package com.wissensalt.rnd.sbed.sd;

import com.wissensalt.rnd.sbed.sd.dto.response.ResponseAPIErrorDTO;
import lombok.ToString;

import java.util.Date;


@ToString
public class APIErrorBuilder {

    private static final Integer NOT_FOUND_STATUS = 404;
    private static final Integer INTERNAL_SERVER_ERROR = 500;
    private static final Integer BAD_REQUEST = 400;
    private static final Integer CONFLICT = 409;
    private static final Integer GONE = 401;

    public static ResponseAPIErrorDTO notFound(Class p_Exception, String p_Message, String p_Path) {
        return new ResponseAPIErrorDTO(
                new Date(),
                NOT_FOUND_STATUS,
                p_Exception.getCanonicalName(),
                p_Message,
                p_Path,
                "Not Found"
        );
    }


    public static ResponseAPIErrorDTO conflict(Class p_Exception, String p_Message, String p_Path) {
        return new ResponseAPIErrorDTO(
                new Date(),
                CONFLICT,
                p_Exception.getCanonicalName(),
                p_Message,
                p_Path,
                "Conflict"
        );
    }

    public static ResponseAPIErrorDTO internalServerError(Class p_Exception, String p_Message, String p_Path) {
        return new ResponseAPIErrorDTO(
                new Date(),
                INTERNAL_SERVER_ERROR,
                p_Exception.getCanonicalName(),
                p_Message,
                p_Path,
                "Internal Server Error"
        );
    }

    public static ResponseAPIErrorDTO badRequest(Class p_Exception, String p_Message, String p_Path) {
        return new ResponseAPIErrorDTO(
                new Date(),
                BAD_REQUEST,
                p_Exception.getCanonicalName(),
                p_Message,
                p_Path,
                "Bad Request"
        );
    }

    public static ResponseAPIErrorDTO gone(Class p_Exception, String p_Message, String p_Path) {
        return new ResponseAPIErrorDTO(
                new Date(),
                GONE,
                p_Exception.getCanonicalName(),
                p_Message,
                p_Path,
                "Gone"
        );
    }
}
