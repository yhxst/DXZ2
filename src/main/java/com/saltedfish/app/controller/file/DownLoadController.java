package com.saltedfish.app.controller.file;

import com.saltedfish.app.config.FileConfig;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RequestMapping("/file")
@RestController
public class DownLoadController {
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "upload/{folder}")
    @ResponseBody
    public ResponseInfo upload(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable("folder")String folder, @RequestParam("test") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseInfo().setCode(Info.EMPTY_FILE);
        }
        return new ResponseInfo().setCode(FileUtil.fileStoreInDisk(request,response,file,folder));
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/download/{folder}/{fileName}.{suffixName}")
    public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response
    ,@PathVariable("folder")String folder,@PathVariable("fileName")String fileName,@PathVariable("suffixName")String suffixName) {
        if (fileName == null) {
            return "文件为空!";
        }
        String path = folder+"/"+fileName+"."+suffixName;
        return String.valueOf(FileUtil.fileDownLoad(request,response,path));
    }

    //多文件上传
    @RequestMapping(value = "/batch/upload/{folder}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo handleFileUpload(HttpServletRequest request,@PathVariable("folder")String folder) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        int code = Info.SERVER_ERR;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String fileName = FileUtil.getFileName(file);
                    String filePath = FileConfig.FILE_USER + folder +"/";
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath+fileName)));
                    stream.write(bytes);
                    stream.close();

                    code = Info.SUCCESS_UPLOAD;
                } catch (Exception e) {
                    stream = null;
                    code  = Info.EMPTY_SOMEONE_FILE;
                    return new ResponseInfo().setCode(code);
                }
            } else {
                code  = Info.EMPTY_FILE;
                return new ResponseInfo().setCode(code);
            }
        }
        return new ResponseInfo().setCode(code);
    }

}