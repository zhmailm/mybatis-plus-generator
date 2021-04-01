package com.zhm.gen.common.web.base;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Describe: 基 础 实 体 类
 * Author: zhm
 * CreateTime: 2019/10/23
 * */

public class BaseDomain implements Serializable {

    /**
     * 创建时间
     * */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 创建人
     * */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 创建人名称
     * */
    @ApiModelProperty(value = "创建人名称")
    private String createName;

    /**
     * 修改时间
     * */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 修改时间
     * */
    @ApiModelProperty(value = "修改时间")
    private String updateBy;

    /**
     * 修改名称
     * */
    @ApiModelProperty(value = "修改名称")
    private String updateName;

    /**
     * 备注
     * */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     *  请求参数
     *  */
    @ApiModelProperty(value = "请求参数")
    private Map<String, Object> params;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
