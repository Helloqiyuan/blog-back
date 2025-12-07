package com.qiyuan.mapper;

import com.github.pagehelper.Page;
import com.qiyuan.dto.ArticlePageQueryDTO;
import com.qiyuan.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    void insertArticle(Article article);
    Integer deleteArticleById(Integer id);
    Integer updateArticle(Article article);
    Article getArticleById(Integer id);
    Page<Article> pageQuery(ArticlePageQueryDTO articlePageQueryDTO);

}
