package com.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     * 根据日期分组，查询每个月博客的数量（对应首页显示按日期分组统计博客数功能）
     * @return
     */
    List<Blog> findBlogDateAndCount() throws Exception;

    /**
     * 分页查询博客列表（对应首页显示博客标题，摘要等信息）
     * @param page
     * @param blog
     * @return
     */
    IPage<Blog> findBlogListByPage(@Param("page") IPage<Blog> page,@Param("blog") Blog blog);

    /**
     * 根据id查询博客（对应首页点击博客标题，发布日期等超链接时，跳转到博客详情页面，查看博客具体内容）
     * @param id
     * @return
     */
    Blog findBlogContentById(Integer id);

    /**
     * 根据id查询上一篇博客（对应首页博客中间分页列表中的上一篇按钮）
     * @param id
     * @return
     */
    Blog findPreBlogById(Integer id);

    /**
     * 根据id查询下一篇博客（对应首页博客中间分页列表中的下一篇按钮）
     * @param id
     * @return
     */
    Blog findNextBlogById(Integer id);

}
