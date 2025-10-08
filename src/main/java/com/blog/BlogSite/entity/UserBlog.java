package com.blog.BlogSite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_blog")
public class UserBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

}
