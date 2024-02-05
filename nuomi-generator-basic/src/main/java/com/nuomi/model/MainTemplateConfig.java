package com.nuomi.model;

import lombok.Data;

/**
 * @author NuoMi
 */
@Data
public class MainTemplateConfig {
    /**
     * 作者
     */
    private String author = "nuomi";
    /**
     * 输出文本信息
     */
    private String outputText = "结果:";
    /**
     * 是否循环
     */
    private Boolean loop = false;
}
