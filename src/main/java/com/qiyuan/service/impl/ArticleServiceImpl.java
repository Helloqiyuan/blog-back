package com.qiyuan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiyuan.constant.ArticleConstant;
import com.qiyuan.dto.ArticlePageQueryDTO;
import com.qiyuan.exception.ArticleException;
import com.qiyuan.mapper.ArticleMapper;
import com.qiyuan.pojo.Article;
import com.qiyuan.vo.PageResult;
import com.qiyuan.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @CacheEvict(value = "article_page_query", allEntries = true)
    public void insertArticle(Article article) {
        article.setAuthorId(1);
        article.setStatus(Article.DRAFT);
        article.setViewCount(0);
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateTime(LocalDateTime.now());
        articleMapper.insertArticle(article);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "article", key = "#id"),
                    @CacheEvict(value = "article_page_query", allEntries = true)
            }
    )
    public void deleteArticleById(Integer id) {
        Integer i = articleMapper.deleteArticleById(id);
        if (i == 0) {
            throw new ArticleException(ArticleConstant.CAN_NOT_DELETE_NOT_EXIST_ARTICLE);
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "article_page_query",allEntries = true),
                    @CacheEvict(value = "article",key = "#article.id")
            }
    )
    public void updateArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        Integer i = articleMapper.updateArticle(article);
        if (i == 0) {
            throw new ArticleException(ArticleConstant.CAN_NOT_UPDATE_NOT_EXIST_ARTICLE);
        }
    }

    @Override
    @Cacheable(value = "article", key = "#id")
    public Article getArticleById(Integer id) {
        Article article = articleMapper.getArticleById(id);
        if (article == null) {
            throw new ArticleException(ArticleConstant.ARTICLE_NOT_EXIST);
        }
        article.setViewCount(article.getViewCount() + 1);
        articleMapper.updateArticle(article);
        return article;
    }

    @Override
    @Cacheable(value = "article_page_query", key = "#articlePageQueryDTO.toString()")
    public PageResult<Article> pageQuery(ArticlePageQueryDTO articlePageQueryDTO) {
        PageHelper.startPage(articlePageQueryDTO.getPage(), articlePageQueryDTO.getPageSize());
        Page<Article> p = articleMapper.pageQuery(articlePageQueryDTO);
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
