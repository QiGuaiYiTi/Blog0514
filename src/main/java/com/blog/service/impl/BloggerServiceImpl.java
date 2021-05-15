package com.blog.service.impl;

import com.blog.entity.Blogger;
import com.blog.dao.BloggerMapper;
import com.blog.service.BloggerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sam
 * @since 2021-05-14
 */
@Service
public class BloggerServiceImpl extends ServiceImpl<BloggerMapper, Blogger> implements BloggerService {

}
