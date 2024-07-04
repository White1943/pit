package com.example.platform.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 公共的分页对象
 */
@Getter
@Setter
public class SearchInfo {
    @JsonProperty("age")
    private int age;//当前页
    @JsonProperty("sex")
    private String sex;//页面大小
    @JsonProperty("color")
    private String color;//页面大小
    @JsonProperty("type")
    private String type;//页面大小
}
