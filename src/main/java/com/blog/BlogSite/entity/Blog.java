package com.blog.BlogSite.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id; // Important

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "blogs")
public class Blog {
    @Id
    private String id;

    private int userId;
    private int blogId;

    private String text;
}
