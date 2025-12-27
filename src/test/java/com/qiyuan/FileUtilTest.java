package com.qiyuan;

import com.qiyuan.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FileUtilTest {
    @Test
    public void testReadFile() {
        String s = FileUtil.readMailTitleFile();
        log.warn("{}", s);
        String y = FileUtil.readMailContentFile("fuck");
        log.warn("{}", y);
    }
}
