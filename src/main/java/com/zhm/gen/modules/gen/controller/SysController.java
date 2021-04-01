package com.zhm.gen.modules.gen.controller;

import com.zhm.gen.common.util.ServletUtil;
import com.zhm.gen.common.util.SystemInfoUtils;
import com.zhm.gen.common.web.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;

/**
 * Describe: 入 口 控 制 器
 * Author: zhm
 * CreateTime: 2019/10/23
 */
@RestController
@RequestMapping
public class SysController extends BaseController {

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 登录视图
     */
    @GetMapping("/")
    public ModelAndView index() {
        return JumpPage("index");
    }

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 主页视图
     */
    @GetMapping("console")
    public ModelAndView home(Model model) {
        model.addAttribute("monitor", SystemInfoUtils.getInfo());
        return JumpPage("console/console");
    }

    /**
     * Describe:无权限页面
     * Return:返回403页面
     */
    @GetMapping("error/403")
    public ModelAndView noPermission() {
        return JumpPage("error/403");
    }

    /**
     * Describe:找不带页面
     * Return:返回404页面
     */
    @GetMapping("error/404")
    public ModelAndView notFound() {
        return JumpPage("error/404");
    }

    /**
     * Describe:异常处理页
     * Return:返回500界面
     */
    @GetMapping("error/500")
    public ModelAndView onException() {
        return JumpPage("error/500");
    }

    /**
     * @author: zhm
     * @version: 1.0
     * @Description: 根据地址下载文件
     * @create: 2021/3/1 14:08
     * @return:
     */
    @GetMapping("download")
    public void download(String path) {
        if (path != null) {
            try {
                java.io.File files = new java.io.File(path);
                if (files.exists()) {
                    FileCopyUtils.copy(new FileInputStream(path), ServletUtil.getResponse().getOutputStream());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
