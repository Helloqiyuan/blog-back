package com.qiyuan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiyuan.dto.pageQuery;
import com.qiyuan.mapper.ArticleMapper;
import com.qiyuan.pojo.Article;
import com.qiyuan.vo.PageResult;
import com.qiyuan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void insertArticle(Article article) {
        article.setAuthorId(1);
        article.setStatus(Article.DRAFT);
        article.setViewCount(0);
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateTime(LocalDateTime.now());
        articleMapper.insertArticle(article);
    }

    @Override
    public void deleteArticleById(Integer id) {
        articleMapper.deleteArticleById(id);
    }

    @Override
    public void updateArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateArticle(article);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.getArticleById(id);
    }

    @Override
    public PageResult<Article> pageQuery(pageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        Page<Article> p = articleMapper.pageQuery();
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
