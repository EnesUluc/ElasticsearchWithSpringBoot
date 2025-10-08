package com.blog.BlogSite.repo;

import com.blog.BlogSite.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface BlogRepo extends ElasticsearchRepository<Blog, String> {
}
