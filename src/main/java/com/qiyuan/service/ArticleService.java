package com.qiyuan.service;

import com.qiyuan.dto.ArticlePageQueryDTO;
import com.qiyuan.dto.pageQueryDTO;
import com.qiyuan.pojo.Article;
import com.qiyuan.vo.PageResult;

public interface ArticleService {
    void insertArticle(Article article);
    void deleteArticleById(Integer id);
    void updateArticle(Article article);
    Article getArticleById(Integer id);
    PageResult<Article> pageQuery(ArticlePageQueryDTO articlePageQueryDTO);
}
