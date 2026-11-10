package com.tang.missu.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/* BO层，是Service向Controller传输的数据 */
@Builder
@Getter
@Setter
public class PageCounter {
    private Integer page;
    private Integer size;
}
