package com.nuomi.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author NuoMi
 */
public class StaticGenerator {
    public static void main(String[] args) throws Exception {
        // 获取当前项目的目录
        String projectPath = System.getProperty("user.dir");
        // File.separator 为本系统的分割符
        String inputPath =projectPath + File.separator + "nuomi-generator-demo-project" + File.separator + "acm-template";
        String outputPath = projectPath;
        //copyFilesByHutool(inputPath, outputPath);
        copyFileByRecursive(new File(inputPath), new File(outputPath));
    }

    /**
     * 拷贝文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 拷贝文件
     * @param inputFile 输入路径
     * @param outputFile 输出路径
     */
    public static void copyFileByRecursive(File inputFile, File outputFile) throws Exception {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录，首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取目录下的所有文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件，直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            // 是文件，直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
