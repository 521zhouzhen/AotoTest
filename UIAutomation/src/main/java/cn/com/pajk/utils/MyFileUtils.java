package cn.com.pajk.utils;

public class MyFileUtils {
    public static String fileSeparatorAdapt(String path){
        final String FILE_SEPARATOR=System.getProperty("file.separator");
        return path.replace("/",FILE_SEPARATOR).replace("\\",FILE_SEPARATOR);
    }
}
