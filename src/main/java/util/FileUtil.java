package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {
    //    从文件中加载
    public static List<String> loadTestFile(String fileName) throws IOException {
        BufferedReader bufferedReader;
        bufferedReader=new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName),Charset.forName("GBK")));
        String word = "";
        List<String> result = new LinkedList<>();
        while((word = bufferedReader.readLine())!=null){
            result.add(word);
        }
        bufferedReader.close();
        return result;
    }
}
