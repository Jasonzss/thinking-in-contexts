package com.jason.tics.user.domain;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;

/**
 * @author Jason
 */
public enum Sex {
    BOY(1, "男"),
    GIRL(0, "女"),
    SECRET(-1, "保密");

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    Sex(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Sex getSex(int i){
        Sex sex;
        switch (i){
            case 1 : sex = Sex.BOY;break;
            case 0 : sex = Sex.GIRL;break;
            case -1 : sex = Sex.SECRET;break;
            default:
                throw new TicsException(ExceptionResponseEnum.INVALID_REQUEST_PARAM);
        }

        return sex;
    }
}
