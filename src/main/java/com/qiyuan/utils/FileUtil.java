package com.qiyuan.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class FileUtil {

    /**
     * 从 classpath（resources 目录）中读取指定文本文件内容，
     * 并根据提供的键值映射替换文件中的占位符。
     *
     * <p>
     * 文件中的占位符格式为：<b>{key}</b>，
     * 方法会使用 {@code replaceList} 中对应 {@code key → value} 的映射进行替换。
     * </p>
     *
     * <h3>示例</h3>
     * <pre>
     * 文件内容：
     *   ab{username}cd{age}gh
     *
     * replaceList：
     *   {
     *     "username" : "Tom",
     *     "age"      : "18"
     *   }
     *
     * 返回结果：
     *   abTomcd18genshin
     * </pre>
     *
     * <h3>使用说明</h3>
     * <ul>
     *   <li>文件必须位于 {@code src/main/resources} 目录或其子目录下</li>
     *   <li>默认使用 UTF-8 编码读取文件</li>
     *   <li>未匹配到的占位符将保持原样不变</li>
     *   <li>若文件不存在或读取失败，将抛出 {@link RuntimeException}</li>
     * </ul>
     *
     * @param fileName
     *        classpath 下的文件路径，例如 {@code "template/mail.txt"}
     * @param replaceList
     *        占位符替换映射，key 为占位符名称（不包含大括号），
     *        value 为替换后的文本内容
     * @return
     *        替换完成后的完整文件内容字符串
     * @throws RuntimeException
     *        当文件不存在、读取失败或发生 IO 异常时抛出
     */
    public static String readFile(String fileName, Map<String, String> replaceList) {
        String res;
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            res = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            for (Map.Entry<String, String> entry : replaceList.entrySet()) {
                res = res.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }



    public static String readMailContentFile(String friendLink) {
        return readFile("mail-content.txt", Map.of("friend-link",friendLink));
    }
    public static String readMailTitleFile(){
        return readFile("mail-title.txt", Map.of());
    }
}
