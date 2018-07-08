package com.baizhi.cmfz.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
public class RichTextResult implements Serializable{

    private Integer errno;
    private List<String> data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RichTextResult{" +
                "errno=" + errno +
                ", data=" + data +
                '}';
    }
}
