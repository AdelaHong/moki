package com.im.moki.tomoko.response;

import com.im.moki.common.util.Result;
import com.im.moki.tomoko.constants.TomokoCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class TomokoResult<T> extends Result<T> {

    private static String SYS_CODE = "MOKI";

    public TomokoResult(boolean success) {
        super(success);
    }


    public static <T> Result<T> createSuccRes(T model, Date successTime) {
        Result result = Result.createSuccRes( model, successTime );
        result.setSysCode( SYS_CODE );
        return result;
    }

    public static <T> Result<T> createFailedRes(int errorCode, String errorMsg) {
        Result result = Result.createFailedRes( errorCode, errorMsg );
        result.setSysCode( SYS_CODE );
        return result;
    }

    public static <T> Result<T> createFailedRes(Result result) {
        Result result1 = Result.createFailedRes( result.getErrorCode(), result.getErrorMsg() );
        result1.setSysCode( result.getSysCode() );
        return result1;
    }

    public static <T> Result<T> createSuccRes( T t) {
        Result result = Result.createSuccRes( t );
        result.setSysCode( SYS_CODE );
        return result;
    }

    public static <T> Result<T> createFailedRes(TomokoCode code) {
        Result result = Result.createFailedRes(code.getCode(), code.getMsg());
        result.setSysCode( SYS_CODE );
        return  result;
    }

    public static Result createFailedRes(TomokoCode code, String msg) {
        Result result = Result.createFailedRes(code.getCode(), StringUtils.isEmpty(msg) ? code.getMsg() : msg);
        result.setSysCode( SYS_CODE );
        return  result;
    }

}
