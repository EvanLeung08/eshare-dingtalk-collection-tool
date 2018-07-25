package com.eshare.Utils;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileUtil
 *
 * @author liangyh
 * @email liangyuhua@ppmoney.com
 * @date 2018/7/25
 */
public class FileUtil {

    /**
     * 从资源目录读取文件内容
     * @param resourcePath
     * @return
     */
    public static String readFileFromResource(String resourcePath) {
        try {
            File file = ResourceUtils.getFile(resourcePath);
            if (file.exists()) {
                return text2String(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 转换文件内容为字符串
     * @param file
     * @return
     */
    public static String text2String(File file) {
        StringBuffer result = new StringBuffer();
        BufferedReader reader = null;
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            reader = new BufferedReader(fr);
            String s;
            while ((s = reader.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }
}
