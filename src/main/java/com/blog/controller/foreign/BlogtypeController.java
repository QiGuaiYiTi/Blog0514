package com.blog.controller.foreign;


import com.blog.service.BlogtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
@Controller
@RequestMapping("/blogtype")
public class BlogtypeController {

    @Autowired
    private BlogtypeService blogtypeService;

    /**
     * 查询所有的博客分类名称以及每个分类下博客的数量（对应首页按照博客分类显示分类名称及分类后博客数量）
     * @return
     */
    @ResponseBody
    @RequestMapping("/typeList")
    public String blogTypeList(){

        try {
            return blogtypeService.findBlogTypeNameAndBlogCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

