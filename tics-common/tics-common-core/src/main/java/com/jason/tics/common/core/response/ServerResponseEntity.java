package com.jason.tics.common.core.response;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@Slf4j
public class ServerResponseEntity<T> {
    /**
     * 状态码
     */
    private String code;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    // 返回成功请求

    public static <T> ServerResponseEntity<T> success(T data){
        ServerResponseEntity<T> response = new ServerResponseEntity<>();
        response.setCode(CommonServerResponse.OK.getCode());
        response.setData(data);
        return response;
    }

    public static <T> ServerResponseEntity<T> success() {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(CommonServerResponse.OK.getCode());
        serverResponseEntity.setMsg(CommonServerResponse.OK.getMsg());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> success(String msg) {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(CommonServerResponse.OK.getCode());
        serverResponseEntity.setMsg(msg);
        return serverResponseEntity;
    }

    // 返回失败请求

    public static <T> ServerResponseEntity<T> showFailMsg(String msg) {
        log.error(msg);
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMsg(msg);
        serverResponseEntity.setCode(ExceptionResponseEnum.SHOW_FAIL.getCode());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> fail(ExceptionResponseEnum responseEnum) {
        log.error(responseEnum.toString());
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMsg(responseEnum.getMsg());
        serverResponseEntity.setCode(responseEnum.getCode());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> fail(ExceptionResponseEnum responseEnum, T data) {
        log.error(responseEnum.toString());
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setMsg(responseEnum.getMsg());
        serverResponseEntity.setCode(responseEnum.getCode());
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }

    //组合功能

    public static <T> ServerResponseEntity<T> successOrShowFail(boolean success, String failMsg) {
        return success ? success() : fail(ExceptionResponseEnum.SHOW_FAIL.setMsg(failMsg));
    }
}
