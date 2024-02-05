package com.nuomi.generator;

import com.nuomi.model.MainTemplateConfig;

import java.io.File;

/**
 * @author NuoMi
 */
public class MainGenerator {
    public static void main(String[] args) throws Exception {
        // 1.静态文件生成
        // 获取当前项目的目录
        String projectPath = System.getProperty("user.dir");
        // File.separator 为本系统的分割符
        String inputPath =projectPath + File.separator + "nuomi-generator-demo-project" + File.separator + "acm-template";
        String outputPath = projectPath;
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        // 2.动态文件生成
        // 获取当前项目的目录
        String dynamicProjectPath = System.getProperty("user.dir") + File.separator + "nuomi-generator-basic";
        // File.separator 为本系统的分割符
        String dynamicInputPath =dynamicProjectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        MainTemplateConfig dataModel = new MainTemplateConfig();
        DynamicGenerator.doGenerator(dynamicInputPath, dynamicOutputPath, dataModel);
    }
}
