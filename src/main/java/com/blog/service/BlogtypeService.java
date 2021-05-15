package com.blog.service;

import com.blog.entity.Blogtype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
public interface BlogtypeService extends IService<Blogtype> {

    /**
     * 查询所有的博客分类名称以及每个分类下博客的数量
     * @return
     * @throws Exception
     */
    String findBlogTypeNameAndBlogCount() throws Exception;


}
