package com.blog.BlogSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogSiteApplication {

	// findAllBlogsDto içinde sadece blogdaki comment sayısını bulomak için commentService açtım, Comment service içinde de size getirmek için metot tanınladım getBlogCommentsSize-> Pek verimli gelmedi yaptığım.
	public static void main(String[] args) {
		SpringApplication.run(BlogSiteApplication.class, args);
	}

}
