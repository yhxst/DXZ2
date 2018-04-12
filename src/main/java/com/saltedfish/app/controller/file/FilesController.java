package com.saltedfish.app.controller.file;

import com.saltedfish.app.bean.file.FileUrl;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.mapper.FileUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/fileUrl")
@RestController
public class FilesController {
    @Autowired
    private FileUrlMapper fileUrlMapper;

    @RequestMapping("/getOne/{code}")
    public FileUrl getFileUrl(@PathVariable("code")String code) {
        FileUrl fileUrl= fileUrlMapper.getOne(code);
        return fileUrl;
    }

    @RequestMapping("/getCurrPage/{userId}/{start}/{end}")
    public List<FileUrl> getCurrPageByDesc(HttpServletResponse response,
                                           @PathVariable("userId")String userId, @PathVariable("start")int start, @PathVariable("end")int end) {
        if (start < 0 || end < 0 || end <= start){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return null;
        }

        List<FileUrl> fileUrls = fileUrlMapper.getCurrPageByDesc(userId,start,end);
        return fileUrls;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void insert(FileUrl fileUrl) {
        fileUrl.autoCode(fileUrlMapper);
        fileUrl.autoCreateAt();
        fileUrl.autoUpdateAt();
        fileUrlMapper.insert(fileUrl);
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public void update(FileUrl fileUrl) {
        fileUrl.autoUpdateAt();
        fileUrlMapper.update(fileUrl);
    }

    @RequestMapping(value="/delete/{code}")
    public void delete(@PathVariable("code") String code) {
        fileUrlMapper.delete(code);
    }

}
