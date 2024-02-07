package com.jason.tics.common.security.config;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.response.ServerResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

/**
 * 默认的全局异常处理器的配置
 *
 * @author Jason
 */
@RestController
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerConfig {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ServerResponseEntity<String>> EntityNotFoundExceptionHandler(EntityNotFoundException e){
        log.error("EntityNotFoundException", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ServerResponseEntity.fail(ExceptionResponseEnum.RESOURCE_NOT_FOUND)
        );
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ServerResponseEntity<String>> EntityExistsExceptionHandler(EntityExistsException e){
        log.error("EntityExistsException", e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ServerResponseEntity.fail(ExceptionResponseEnum.RESOURCE_ALREADY_EXISTS)
        );
    }
}
