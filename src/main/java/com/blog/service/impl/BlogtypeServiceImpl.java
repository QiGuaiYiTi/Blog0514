package com.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.blog.entity.Blogtype;
import com.blog.dao.BlogtypeMapper;
import com.blog.service.BlogtypeService;
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
public class BlogtypeServiceImpl extends ServiceImpl<BlogtypeMapper, Blogtype> implements BlogtypeService {

    @Autowired
    private BlogtypeMapper blogtypeMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 查询所有的博客分类名称以及每个分类下博客的数量（对应首页按照博客分类显示分类名称及分类后博客数量）
     * @return
     * @throws Exception
     */
    @Override
    public String findBlogTypeNameAndBlogCount() throws Exception {
        //先从Redis缓存中读取
        String blogTypeInfo = redisTemplate.opsForValue().get("blog_name_count");
        //若Redis缓存中找不到
        if(blogTypeInfo==null || blogTypeInfo.equals("") || blogTypeInfo.length()==0){
            //去数据库查询
            List<Blogtype> blogTypeList = blogtypeMapper.findBlogTypeNameAndBlogCount();
            //转换为字符串
            blogTypeInfo=JSON.toJSONString(blogTypeList);
        }
        //将查询到的结果添加到缓存中
        redisTemplate.opsForValue().set("blog_name_count",blogTypeInfo);
        //返回数据
        return blogTypeInfo;
    }
}
