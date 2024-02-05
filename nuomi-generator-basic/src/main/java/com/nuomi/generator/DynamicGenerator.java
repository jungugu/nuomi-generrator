package com.nuomi.generator;

import com.nuomi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author NuoMi
 */
public class DynamicGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        // 获取当前项目的目录
        String projectPath = System.getProperty("user.dir") + File.separator + "nuomi-generator-basic";
        // File.separator 为本系统的分割符
        String inputPath =projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig dataModel = new MainTemplateConfig();
        doGenerator(inputPath, outputPath, dataModel);
    }
    public static void doGenerator(String inputPath, String outputPath, Object dataModel) throws IOException, TemplateException {
        // 指定版本
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文本的路径
        File parentFile = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(parentFile);
        configuration.setNumberFormat("0.######");
        // 指定字符集
        configuration.setDefaultEncoding("utf-8");
        // 指定模板文件
        String fileName = new File(inputPath).getName();
        Template template = configuration.getTemplate(fileName);

        Writer out = new FileWriter(outputPath);
        template.process(dataModel, out);

        // 关闭流
        out.close();
    }
}
