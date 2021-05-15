package com.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.entity.Blog;
import com.blog.dao.BlogMapper;
import com.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
@Service
@Transactional
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 根据日期分组，查询每个月博客的数量,对应首页显示按日期分组统计博客数功能
     * @return
     */
    @Override
    public String findBlogDateAndCount() throws Exception {
        //先从Redis缓存中读取数据
        String blogInfo = redisTemplate.opsForValue().get("blog_date_count");
        //若从缓存读到的数据为空
        if(blogInfo==null ||blogInfo.equals("") ||blogInfo.length()==0){
            //则调用查询数据库的方法查询数据库
            List<Blog> blogList = blogMapper.findBlogDateAndCount();
            //将数据转换为JSON格式数据
            blogInfo = JSON.toJSONString(blogList);
            //将数据保存到Redis缓存
            redisTemplate.opsForValue().set("blog_date_count",blogInfo);
        }
        //返回数据
        return blogInfo;

    }

    @Override
    public IPage<Blog> findBlogListByPage(IPage<Blog> page, Blog blog) {
        return blogMapper.findBlogListByPage(page,blog);
    }

    /**
     * 根据id查询博客（对应首页点击博客标题，发布日期等超链接时，跳转到博客详情页面，查看博客具体内容）
     * @param id
     * @return
     */
    @Override
    public Blog findBlogContentById(Integer id) {
        //实现每次点击博客，阅读量+1的功能
        //先查询出博客对象
        Blog blog = blogMapper.findBlogContentById(id);
        //设置博客阅读量+1
        blog.setClickHit(blog.getClickHit()+1);
        //修改数据
        blogMapper.updateById(blog);
        //返回博客对象
        return blog;
    }

    /**
     * 根据id查询上一篇博客（对应首页博客中间分页列表中的上一篇按钮）
     * @param id
     * @return
     */
    @Override
    public Blog findPreBlogById(Integer id) {
        return blogMapper.findPreBlogById(id);
    }

    /**
     * 根据id查询下一篇博客（对应首页博客中间分页列表中的下一篇按钮）
     * @param id
     * @return
     */
    @Override
    public Blog findNextBlogById(Integer id) {
        return blogMapper.findNextBlogById(id);
    }
}
