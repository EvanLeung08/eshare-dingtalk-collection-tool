package com.eshare.Utils;

import java.io.*;
import java.net.URL;

/**
 * FileUtil
 *
 * @author liangyh
 * @date 2018/7/25
 */
public class FileUtil {

    /**
     * 从资源目录读取文件内容
     *
     * @param resourcePath
     * @return
     */
    public static String readFileFromResource(String resourcePath) {
        try {
            URL url = FileUtil.class.getClassLoader().getResource(resourcePath);
            File file = new File(url.getFile());
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
     *
     * @param file
     * @return
     */
    public static String text2String(File file) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        InputStreamReader read = null;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(file);
            read = new InputStreamReader(
                    fi, "utf-8");
            reader = new BufferedReader(read);
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(System.lineSeparator() + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (read != null) {
                try {
                    read.close();
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
        return sb.toString();
    }
}
