package com.blog.dao;

import com.blog.entity.Blogtype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
public interface BlogtypeMapper extends BaseMapper<Blogtype> {

    /**
     * 查询所有的博客分类名称以及每个分类下博客的数量（对应首页按照博客分类显示分类名称及分类后博客数量）
     * @return
     * @throws Exception
     */
    List<Blogtype> findBlogTypeNameAndBlogCount() throws Exception;


}
