package com.zhm.gen.common.web.domain.response;

import java.io.Serializable;
import java.util.List;

/**
 * CxSelect树结构实体类
 * 
 * @author ruoyi
 */
public class ResultSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 数据值字段名称
     */
    private String v;

    /**
     * 数据标题字段名称
     */
    private String n;

    /**
     * 子集数据字段名称
     */
    private List<ResultSelect> s;

    public ResultSelect()
    {
    }

    public ResultSelect(String v, String n)
    {
        this.v = v;
        this.n = n;
    }

    public List<ResultSelect> getS()
    {
        return s;
    }

    public void setN(String n)
    {
        this.n = n;
    }

    public String getN()
    {
        return n;
    }

    public void setS(List<ResultSelect> s)
    {
        this.s = s;
    }

    public String getV()
    {
        return v;
    }

    public void setV(String v)
    {
        this.v = v;
    }
}
