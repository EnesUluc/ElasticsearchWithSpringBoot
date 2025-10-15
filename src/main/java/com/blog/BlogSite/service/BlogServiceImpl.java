package com.blog.BlogSite.service;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.blog.BlogSite.dto.BlogDto;
import com.blog.BlogSite.entity.Blog;
import com.blog.BlogSite.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    private final BlogRepo blogRepo;
    private final UsersService usersService;
    private final ElasticsearchOperations elasticsearchOperations;
    private co.elastic.clients.elasticsearch._types.query_dsl.Query queryDsl;

    @Autowired
    public BlogServiceImpl(BlogRepo blogRepo, UsersService usersService, ElasticsearchOperations elasticsearchOperations) {
        this.blogRepo = blogRepo;
        this.usersService = usersService;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    @Override
    public Blog saveBlogDto(BlogDto blogDto, Integer blogId, Integer userId) {
        // Dto operation
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setText(blogDto.getText());
        blog.setBlogId(blogId);
        blog.setUserId(userId);

        return blogRepo.save(blog);
    }

    @Override
    public List<Blog> findAllBlogs() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Blog> blogPage = blogRepo.findAll(pageable);
        return blogPage.getContent();
    }

    @Override
    public List<BlogDto> findAllBlogsDto() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Blog> blogPage = blogRepo.findAll(pageable);

        List<BlogDto> blogDtos = new ArrayList<>();
        for(Blog blog: blogPage){
            BlogDto blogDto = new BlogDto();
            blogDto.setTitle(blog.getTitle());
            blogDto.setText(blog.getText());

            // Get the username
            usersService.findById(blog.getUserId()).ifPresent(users -> blogDto.setUsername(users.getEmail()));
            blogDtos.add(blogDto);
        }
        return blogDtos;
    }

    @Override
    public Optional<Blog> findByBlogId(String blogId) {
        return blogRepo.findById(blogId);
    }

    @Override
    public Blog findByBlogId(int id) {
        return blogRepo.findByBlogId(id);
    }

    @Override
    public void deleteBlog(String blogId) {
        blogRepo.deleteById(blogId);
    }

    @Override
    public int findAllByUserId(int userId) {
        List<Blog> blogList = blogRepo.findAllByUserId(userId);
        return blogList.size();
    }

    @Override
    public void deleteAllByUserId(int userId) {
        blogRepo.deleteAllByUserId(userId);
    }

    @Override
    public void deleteByBlogId(int blogId) {
        blogRepo.deleteByBlogId(blogId);
    }

    @Override
    public List<Blog> findAllBlogsByUserId(int userId) {
        return blogRepo.findAllByUserId(userId);
    }

    @Override
    public List<BlogDto> searchByText(String word) {
        List<Blog> blogList = getBlogListCreatingQuery(word);

        List<BlogDto> blogDtoList = new ArrayList<>();
        for(Blog blog: blogList){
            BlogDto blogDto = new BlogDto();
            blogDto.setTitle(blog.getTitle());
            blogDto.setText(blog.getText());
            usersService.findById(blog.getUserId()).ifPresent(users -> blogDto.setUsername(users.getEmail()));

            blogDtoList.add(blogDto);
        }

        return blogDtoList;
    }
    public List<Blog> getBlogListCreatingQuery(String word){
        queryDsl = QueryBuilders.multiMatch()
                .query(word)
                .fields("title^2", "text")
                .build()
                ._toQuery();
        Query query = NativeQuery.builder()
                .withQuery(queryDsl)
                .build();

        SearchHits<Blog> hits = elasticsearchOperations.search(query, Blog.class);

        return  hits.stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
