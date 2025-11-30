package com.qiyuan.service;

import com.qiyuan.dto.pageQuery;
import com.qiyuan.pojo.Article;
import com.qiyuan.vo.PageResult;

public interface ArticleService {
    void insertArticle(Article article);
    void deleteArticleById(Integer id);
    void updateArticle(Article article);
    Article getArticleById(Integer id);
    PageResult<Article> pageQuery(pageQuery pageQuery);
}
