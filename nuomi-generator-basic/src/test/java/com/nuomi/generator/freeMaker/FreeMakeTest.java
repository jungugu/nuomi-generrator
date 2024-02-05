package com.nuomi.generator.freeMaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NuoMi
 */
public class FreeMakeTest {

    @Test
    public void test() throws IOException, TemplateException {
        // 指定版本
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文本的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        configuration.setNumberFormat("0.######");
        // 指定字符集
        configuration.setDefaultEncoding("utf-8");
        // 指定模板文件
        Template template = configuration.getTemplate("myweb.html.ftl");
        // 创建数据模型
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("currentYear", 2023);
        
        List<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> item1 = new HashMap<String, Object>();
        item1.put("url","https://codefather.cn");
        item1.put("label", "编程导航");

        HashMap<String, Object> item2 = new HashMap<String, Object>();
        item2.put("url","https://laoyujianli.com");
        item2.put("label", "老鱼简历");

        items.add(item1);
        items.add(item2);

        dataModel.put("menuItems", items);

        Writer out = new FileWriter("myweb.html");
        template.process(dataModel, out);

        // 关闭流
        out.close();
    }
}
