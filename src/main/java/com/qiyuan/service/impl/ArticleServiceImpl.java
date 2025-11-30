package com.qiyuan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiyuan.constant.ArticleConstant;
import com.qiyuan.dto.pageQueryDTO;
import com.qiyuan.exception.ArticleException;
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
        Integer i = articleMapper.deleteArticleById(id);
        if (i == 0) {
            throw new ArticleException(ArticleConstant.CAN_NOT_DELETE_NOT_EXIST_ARTICLE);
        }
    }

    @Override
    public void updateArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        Integer i = articleMapper.updateArticle(article);
        if (i == 0) {
            throw new ArticleException(ArticleConstant.CAN_NOT_UPDATE_NOT_EXIST_ARTICLE);
        }
    }

    @Override
    public Article getArticleById(Integer id) {
        Article article =  articleMapper.getArticleById(id);
        if (article == null) {
            throw new ArticleException(ArticleConstant.ARTICLE_NOT_EXIST);
        }
        return article;
    }

    @Override
    public PageResult<Article> pageQuery(pageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Article> p = articleMapper.pageQuery();
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
