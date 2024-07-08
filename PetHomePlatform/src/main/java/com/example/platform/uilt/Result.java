package com.example.platform.uilt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class Result<T> implements Serializable {
    
    private String code;
    private String msg;
    private T data;
    

    /**
     * @param errorType
     */
    public Result(SystemErrorType errorType) {
        this.code = errorType.getCode();
        this.msg = errorType.getMsg();
    }

    /**
     * @param errorType
     * @param data
     */
    public Result(SystemErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    /**
     * 内部使用，用于构造成功的结果
     *
     * @param code
     * @param msg
     * @param data
     */
    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 快速创建成功结果并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return new Result<>(SystemErrorType.SUCCESS.getCode(), SystemErrorType.SUCCESS.getMsg(), data);
    }

    public static Result success(SystemErrorType systemErrorType) {
        return new Result(systemErrorType);
    }

    /**
     * 快速创建成功结果
     *
     * @return Result
     */
    public static Result success() {
        return success(SystemErrorType.SUCCESS);
    }

    /**
     * 系统异常类没有返回数据
     *
     * @return Result
     */
    public static Result fail() {
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }


    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result fail(SystemErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }

    public static Result fail(String code,String msg) {
        return new Result(code,msg,null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @return Result
     */
    public static Result fail(SystemErrorType errorType) {
        return Result.fail(errorType, null);
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return new Result<>(SystemErrorType.SYSTEM_ERROR, data);
    }
}
