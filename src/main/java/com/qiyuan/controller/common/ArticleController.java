package com.qiyuan.controller.common;



import com.qiyuan.dto.ArticlePageQueryDTO;
import com.qiyuan.pojo.Article;

import com.qiyuan.vo.PageResult;
import com.qiyuan.vo.Result;
import com.qiyuan.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("commonArticleController")
@RequestMapping("/common/article")
@Slf4j
@Tag(name = "文章接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;



    /**
     * 根据id查询文章
     */
    @GetMapping
    @Operation(summary = "根据id查询文章")

    public Result getArticleById(@RequestParam Integer id) {
        log.info("查询文章:{}", id);
        Article article = articleService.getArticleById(id);
        return Result.success(article);
    }

    /**
     * 分页查询文章
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询文章")

    public Result getArticleByPage(ArticlePageQueryDTO articlePageQueryDTO) {
        log.info("分页查询文章:{}", articlePageQueryDTO);
        PageResult<Article> articles = articleService.pageQuery(articlePageQueryDTO);
        return Result.success(articles);
    }

}
