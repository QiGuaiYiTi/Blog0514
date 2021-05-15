package com.blog.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description : 后台首页控制器
 *
 * @author : Sam
 * @created : 2021/5/16
 */
@RequestMapping("/admin")
public class IndexAdminController {

    @RequestMapping("/index")
    public String index(){

        return "admin/home";
    }

}
