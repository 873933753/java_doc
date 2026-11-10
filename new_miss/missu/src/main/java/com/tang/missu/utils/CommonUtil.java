package com.tang.missu.utils;

import com.tang.missu.bo.PageCounter;

public class CommonUtil {
    // 封装方法 - 将前端传入的start和count处理为 page 和 size
    public static PageCounter convertToPageParameter(Integer start, Integer count){
        int pageNum = start / count;

        PageCounter pageCounter = PageCounter.builder()
                .page(pageNum)
                .size(count)
                .build();
        return pageCounter;
    }
}
