package com.saltedfish.app.util;

import com.saltedfish.app.config.FileConfig;
import com.saltedfish.app.config.Info;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 没做:路径储存到数据库
 */
public class FileUtil {
    private FileUtil() {
    }

    public static int fileStoreInDisk(HttpServletRequest request, HttpServletResponse response,
                                      MultipartFile file, String folder) {

        int code = Info.SERVER_ERR;

        // 解决中文问题，liunx下中文路径，图片显示问题
        String fileName = getFileName(file);
        File dest = new File(FileConfig.FILE_USER + "/" + folder + "/" + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            code = Info.SUCCESS_UPLOAD;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String fileStoreInDisk2(HttpServletRequest request, HttpServletResponse response,
                                          MultipartFile file, String folder) {

        String photoUrl = "";

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (!suffix.equals(".jpg") && !suffix.equals(".png"))
            return photoUrl;
        String photoUrl1 =  folder+ "headPhoto" + suffix;
        File dest = new File(FileConfig.FILE_USER + folder +  "headPhoto" + suffix);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            photoUrl = photoUrl1;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photoUrl;
    }

    public static int fileDownLoad(org.apache.catalina.servlet4preview.http.HttpServletRequest request,
                                   HttpServletResponse response, String filePath) {
        int code = Info.SERVER_ERR;
        //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
//            String realPath = request.getServletContext().getRealPath(
//                    "//WEB-INF//");//相对路径

        File file = new File(FileConfig.FILE_USER, filePath);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + filePath);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                code = Info.SUCCESS_DOWN;
                Log.d("fileDownLoad", "成功");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return code;
    }


    public static void fileDownLoad2(HttpServletResponse response, String filePath) {


        File file = new File(FileConfig.FILE_USER, filePath);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + filePath);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 判断是否为中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    public static String getDateStr(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
        return dateFormat.format( now );
    }

    public static String getFileName(String fileName,String suffixName){
        if (isChinese(fileName)){
            fileName = getDateStr() +UUID.randomUUID() + suffixName;
        }else {
            fileName = getDateStr() +fileName + suffixName;
        }
        return fileName;
    }
    public static String getFileName(MultipartFile fileNameAndSuffixName){
        String fileName = fileNameAndSuffixName.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (isChinese(fileName)){
            fileName = getDateStr() +UUID.randomUUID() + suffixName;
        }else {
            fileName = getDateStr() +fileName + suffixName;
        }
        return fileName;
    }
}
