package com.blog.BlogSite.repo;

import com.blog.BlogSite.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface BlogRepo extends ElasticsearchRepository<Blog, String> {
    List<Blog> findAllByUserId(int userId);
    void deleteAllByUserId(int userId);
    Blog findByBlogId(int blogId);
    void deleteByBlogId(int blogId);
}
