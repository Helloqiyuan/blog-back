package com.qiyuan.controller;

import com.qiyuan.dto.ArticlePageQueryDTO;
import com.qiyuan.dto.pageQueryDTO;
import com.qiyuan.pojo.Article;
import com.qiyuan.vo.PageResult;
import com.qiyuan.pojo.Result;
import com.qiyuan.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 新增文章
     */
    @PostMapping()
    public Result insertArticle(@RequestBody Article article) {
        log.info("新增文章:{}", article);
        articleService.insertArticle(article);
        return Result.success();
    }

    /**
     * 根据id删除文章
     */
    @DeleteMapping
    public Result deleteArticleById(@RequestParam Integer id) {
        log.info("删除文章:{}", id);
        articleService.deleteArticleById(id);
        return Result.success();
    }

    /**
     * 修改文章
     */
    @PutMapping
    public Result updateArticle(@RequestBody Article article) {
        log.info("修改文章:{}", article);
        articleService.updateArticle(article);
        return Result.success();
    }

    /**
     * 根据id查询文章
     */
    @GetMapping
    public Result getArticleById(@RequestParam Integer id) {
        log.info("查询文章:{}", id);
        Article article = articleService.getArticleById(id);
        return Result.success(article);
    }

    /**
     * 分页查询文章
     */
    @GetMapping("/page")
    public Result getArticleByPage(ArticlePageQueryDTO articlePageQueryDTO) {
        log.info("分页查询文章:{}", articlePageQueryDTO);
        PageResult<Article> articles = articleService.pageQuery(articlePageQueryDTO);
        return Result.success(articles);
    }
}
