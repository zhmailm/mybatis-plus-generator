package com.zhm.gen.common.config.proprety;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Contact;

/**
 * Describe: 接 口 文 档 配 置 类
 * Author: zhm
 * CreateTime: 2019/10/23
 * */
@Component
@ConfigurationProperties("swagger")
public class SwaggerProperty {

    /** 是 否 开 启 */
    private Boolean enable = true;

    /** 分 组 名 称 */
    private String groupName;

    /** 系 统 标 题 */
    private String title;

    /** 描 述 信 息 */
    private String describe;

    /** 版 本 信 息 */
    private String version;

    /** 扫 描 路 径 */
    private String scanPackage;

    /** 扩 展 信 息 */
    private Contact contact;

    /** 协 议 */
    private String licence;

    /** 协 议 链 接 */
    private String licenceUrl;

    /** 组 织 链 接 */
    private String termsOfServiceUrl;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLicenceUrl() {
        return licenceUrl;
    }

    public void setLicenceUrl(String licenceUrl) {
        this.licenceUrl = licenceUrl;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }
}