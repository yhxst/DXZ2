package com.saltedfish.app.controller.comment;

import com.saltedfish.app.bean.category.Category;
import com.saltedfish.app.bean.comment.Comment;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.mapper.CategoryMapper;
import com.saltedfish.app.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.saltedfish.app.constant.Constants.PAGE_NUMBER;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentMapper mapper;

    @RequestMapping("/getCurrPage/{postId}/{descOrAsc}/{currPage}")
    public List<Comment>  getCurrPageByDesc(HttpServletResponse response,
                                     @PathVariable("postId")String postId,
                                     @PathVariable("descOrAsc")String descOrAsc,
                                     @PathVariable("currPage")int currPage) {
        if (postId != null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return null;
        }
        if (descOrAsc.equals("desc") || descOrAsc.equals("asc")){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return null;
        }
        if (descOrAsc.equals("desc")) {
            int start = (currPage - 1) * PAGE_NUMBER;
            int end = currPage * PAGE_NUMBER - 1;
            List<Comment> comments = mapper.getCurrPageByDesc(postId, start, end);
            return comments;
        }else if (descOrAsc.equals("asc")) {
            int start = (currPage - 1) * PAGE_NUMBER;
            int end = currPage * PAGE_NUMBER - 1;
            List<Comment> comments = mapper.getCurrPageByDesc(postId, start, end);
            return comments;
        }
        return null;
    }

    @RequestMapping("/add")
    public void insert(Comment comment) {
        comment.init(mapper);
        mapper.insert(comment);
    }

    @RequestMapping(value="/update")
    public void update(Comment comment) {
        comment.autoUpdateAt();
        mapper.update(comment);
    }

    @RequestMapping(value="/delete/{code}")
    public void delete(@PathVariable("code") String code) {
        mapper.delete(code);
    }
}
