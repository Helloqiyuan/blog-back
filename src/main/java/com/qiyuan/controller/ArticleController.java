package com.qiyuan.controller;

import com.qiyuan.constant.UploadConstant;
import com.qiyuan.dto.ArticlePageQueryDTO;
import com.qiyuan.pojo.Article;
import com.qiyuan.pojo.ArticlePicture;
import com.qiyuan.pojo.EditorResult;
import com.qiyuan.utils.AliyunOSSUtil;
import com.qiyuan.vo.PageResult;
import com.qiyuan.pojo.Result;
import com.qiyuan.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/article")
@Slf4j
@Tag(name = "文章接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    /**
     * 新增文章
     */
    @PostMapping()
    @Operation(summary = "新增文章")
    public Result insertArticle(@RequestBody Article article) {
        log.info("新增文章:{}", article);
        articleService.insertArticle(article);
        return Result.success();
    }

    /**
     * 根据id删除文章
     */
    @DeleteMapping
    @Operation(summary = "根据id删除文章")
    public Result deleteArticleById(@RequestParam Integer id) {
        log.info("删除文章:{}", id);
        articleService.deleteArticleById(id);
        return Result.success();
    }

    /**
     * 修改文章
     */
    @PutMapping
    @Operation(summary = "修改文章")
    public Result updateArticle(@RequestBody Article article) {
        log.info("修改文章:{}", article);
        articleService.updateArticle(article);
        return Result.success();
    }

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
    /**
     * 文章图片的上传接口
     */
    @PostMapping("/upload")
    @Operation(summary = "文章图片的上传接口")
    public EditorResult uploadArticlePicture(MultipartFile file) {
        log.info("上传文章图片开始:{}", file.getOriginalFilename());
        ArticlePicture res = new ArticlePicture();
        try {
            res.setUrl(aliyunOSSUtil.upload(file));
        } catch (Exception e) {
            return EditorResult.error(UploadConstant.UPLOAD_FAIL);
        }
        log.info("上传文章图片成功:{}", res);

        return EditorResult.success(res);
    }
}
