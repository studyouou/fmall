package org.og.fmall.commonapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:ougen
 * @date:2019/9/2615:00
 */
@Data
public class ResponseContext implements Serializable{
    private int code=0;

    private String msg = "成功";

    public boolean cheakSuccess(){
        if (code != 0){
            return false;
        }
        return true;
    }
}
