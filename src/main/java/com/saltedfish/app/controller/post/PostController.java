package com.saltedfish.app.controller.post;

import com.saltedfish.app.bean.SimpleSearch;
import com.saltedfish.app.bean.file.FileUrl;
import com.saltedfish.app.bean.post.Post;
import com.saltedfish.app.bean.post.SimplePost;
import com.saltedfish.app.bean.post.SimplePost_Input;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.intercept.Auth;
import com.saltedfish.app.mapper.*;
import com.saltedfish.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.saltedfish.app.constant.Constants.*;

/**
 * 1.获取指定的博 √
 * 2.获取任意一人的一页博 √
 * 3.删除博 √
 * 4.查找博
 *      4.1 按类别查找博 √
 *      4.2 按热度查找博
 *      4.3 按关键字查找博
 * 5.发博 √
 *
 */
@Auth
@RequestMapping("/post")
@RestController
public class PostController {
    @Autowired
    PostMapper postMapper;

    @Autowired
    Star_StoreMapper starMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    User_InfoMapper user_infoMapper;

    @Autowired
    FileUrlMapper fileUrlMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    Post_CategoryMapper post_categoryMapper;

    /**
     * 取得指定的博
     * 测试成功
     */
    @RequestMapping("/getSimplePost/{postId}")
    public SimplePost getSimplePost(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("postId") String postId){
        if (postId == null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return null;
        }
        //博不存在
        if (!isPost(postId)){
            ResponseInfo.setCode(response,Info.POST_NOT_EXIST);
        }
        //判断是否是本人的博
        String myUserId = JwtUtil.parseToken(request);
        String postUserId = postMapper.getUserId(postId);
        if (!myUserId.equals(postUserId)){
            //不是本人博
            int permission = postMapper.getPermission(postId);
            if (permission == 0){
                //公开
                return getSimplePost(postId);
            }else {
                ResponseInfo.setCode(response,Info.POST_NOT_EXIST);
                return null;
            }
        }else {
            //是本人博
            int permission = postMapper.getPermission(postId);
            if (permission == 1 || permission == 0){
                //私密 或者 公开
                return getSimplePost(postId);
            }else {
                //被系统删除
                ResponseInfo.setCode(response,Info.POST_NOT_EXIST);
                return null;
            }
        }
    }

    /**
     * 取得某用户一页博
     * 测试成功
     */
    @RequestMapping("/getOnePageSimplePost/{postUserId}/{page}")
    public List<SimplePost> getOnePageSimplePost(HttpServletRequest request,
                                        HttpServletResponse response, @PathVariable("page") int page,
                                        @PathVariable("postUserId") String postUserId) {
        if (postUserId == null) {
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return null;
        }

        //使用查找的用户
        String myUserId = JwtUtil.parseToken(request);
        return getOnePageSimplePost(myUserId,postUserId,page);
    }

    /**
     * 发博
     * 测试成功
     */
    @RequestMapping(value = "/sendPost",method = RequestMethod.POST)
    public void sendPost(HttpServletRequest request,
                         HttpServletResponse response,
                         SimplePost_Input simplePost){

        if (simplePost == null){
            ResponseInfo.setCode(response,Info.PARAM_ERR);
            return;
        }
        if (simplePost.getUserId() == null || simplePost.getPhotoUrls() == null){
            ResponseInfo.setCode(response,Info.PARAM_ERR);
            return;
        }


        Post post = new Post(postMapper);
        List<String> fileUrls = simplePost.getPhotoUrls();
        post.setUserId(simplePost.getUserId());
        if (simplePost.getContent() != null)
            post.setContent(simplePost.getContent());
        if (simplePost.getAddress() != null)
            post.setAddress(simplePost.getAddress());

        String userId = simplePost.getUserId();
        String postId = post.getCode();
        //添加一条博
        postMapper.insert(post);
        for (int i = 0;i< fileUrls.size();i++){
            FileUrl fileUrl = new FileUrl(fileUrlMapper);
            fileUrl.setUserId(userId);
            fileUrl.setUrl(fileUrls.get(i));
            fileUrl.setPostId(postId);
            fileUrlMapper.insert(fileUrl);
        }

    }


    /**
     * 删除博
     * 测试成功
     */
    @RequestMapping("/deletePost/{postId}")
    private void deletePost(HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable("postId") String postId){
        if (postId == null){
            ResponseInfo.setCode(response,Info.PARAM_ERR);
            return;
        }
        String myUserId = JwtUtil.parseToken(request);
        String postUserId = postMapper.getUserId(postId);
        if (!myUserId.equals(postUserId)){
            //需要删除的博用户不是发博的用户
            ResponseInfo.setCode(response,Info.PARAM_ERR);
            return;
        }
        //删除
        postMapper.delete(postId);

    }


    /**
     * Select
     */
    /**
     * 按类别查找博
     * category 那个成功
     */
    @RequestMapping(value = "/searchPost",method = RequestMethod.POST)
    public List<SimplePost> searchPost(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    SimpleSearch simpleSearch){
        if (simpleSearch == null){
            ResponseInfo.setCode(response,Info.PARAM_ERR);
            return null;
        }
        return getOnePageSimplePostByMethod(simpleSearch);
    }




    private List<SimplePost> getOnePageSimplePostByMethod(SimpleSearch simpleSearch){
        String content = simpleSearch.getContent();
        //String categoryContent = simpleSearch.getCategoryContent();
        String method = simpleSearch.getMethod();
        int page = simpleSearch.getPage();
        int star = (page - 1) * PAGE_NUMBER;
        int end = page * PAGE_NUMBER;

        switch (method){
            case METHOD_DEF :
                return getSimplePostByDef(content,star,end);
            case METHOD_DESC :
                return getSimplePostByDesc(content,star,end);
            case METHOD_ASC :
                return getSimplePostByAsc(content,star,end);
            case METHOD_HEAT :
                return getSimplePostByHeat(content,star,end);
            case METHOD_CATEGORY :
                return getSimplePostByCategory(content,star,end);
        }
        return null;
    }

    private List<SimplePost> getSimplePostByDef(String content,int star,int end){
        List<SimplePost> simplePostList = new ArrayList<>();
        List<String> postIdList = postMapper.getContentLike(content,star,end);

        for (int i=0;i<postIdList.size();i++){
            SimplePost simplePost = getSimplePost(postIdList.get(i));
            if (simplePost != null)
                simplePostList.add(simplePost);
        }
        return simplePostList;
    }
    private List<SimplePost> getSimplePostByDesc(String content,int star,int end){
        List<SimplePost> simplePostList = new ArrayList<>();
        List<String> postIdList = postMapper.getContentLikeDesc(content,star,end);

        for (int i=0;i<postIdList.size();i++){
            SimplePost simplePost = getSimplePost(postIdList.get(i));
            if (simplePost != null)
                simplePostList.add(simplePost);
        }
        return simplePostList;
    }
    private List<SimplePost> getSimplePostByAsc(String content,int star,int end){
        List<SimplePost> simplePostList = new ArrayList<>();
        List<String> postIdList = postMapper.getContentLikeAsc(content,star,end);

        for (int i=0;i<postIdList.size();i++){
            SimplePost simplePost = getSimplePost(postIdList.get(i));
            if (simplePost != null)
                simplePostList.add(simplePost);
        }
        return simplePostList;
    }
    private List<SimplePost> getSimplePostByHeat(String content,int star,int end){
        List<SimplePost> simplePostList = new ArrayList<>();
        List<String> postIdList = postMapper.getContentLikeHeat(content,star,end);

        for (int i=0;i<postIdList.size();i++){
            SimplePost simplePost = getSimplePost(postIdList.get(i));
            if (simplePost != null)
                simplePostList.add(simplePost);
        }
        return simplePostList;
    }
    private List<SimplePost> getSimplePostByCategory(String content,int star,int end){
        List<SimplePost> simplePostList = new ArrayList<>();
        //取得内容包含 content 的类别
        String categoryId = categoryMapper.getCategoryIdByContent(content);
        //取得当前页前十的博
        List<String> postIdList = post_categoryMapper.getPostIdsByCategoryId(categoryId,star,end);
        for (int i=0;i<postIdList.size();i++){
            SimplePost simplePost = getSimplePost(postIdList.get(i));
            if (simplePost != null)
                simplePostList.add(simplePost);
        }
        return simplePostList;
    }


    private SimplePost getSimplePost(String postId){
        SimplePost simplePost = new SimplePost();

        //获取博
        Post post = postMapper.getOne(postId);
        int starSum = starMapper.getStarSumByPostId(postId);
        String name = userMapper.getUserName(post.getUserId());
        String headPhotoUrl = user_infoMapper.getUserHeadPhotoUrl(post.getUserId());
        List<String> photoUrls = fileUrlMapper.getFileUrlByPostId(postId);
        simplePost.setCode(post.getCode());
        simplePost.setUserId(post.getUserId());
        simplePost.setContent(post.getContent());
        simplePost.setAddress(post.getAddress());
        simplePost.setPhotoUrls(photoUrls);
        simplePost.setStarSum(starSum);
        simplePost.setUserHeadPhotoUrl(headPhotoUrl);
        simplePost.setUserName(name);

        //更新点击数和热度  热度暂时不做
        postMapper.updateClickSum(postId);

        return simplePost;
    }

    private List<SimplePost> getOnePageSimplePost(String myUserId,String postUserId,int page){
        boolean flag = false;
        if (postUserId.equals(myUserId)){
            flag = true;
        }

        List<SimplePost> simplePostList = new ArrayList<>();
        int star = (page - 1) * PAGE_NUMBER;
        int end = page * PAGE_NUMBER;
        List<String> postIdList = new ArrayList<>();
        if (flag) {
            //是本人
            //查找permission<2 的博
            postIdList = postMapper.getOnePageUserId(postUserId,2, star, end);
        } else {
            //不是本人
            //查找permission<1 的博
            postIdList = postMapper.getOnePageUserId(postUserId,1, star, end);
        }
        for (int i = 0;i<postIdList.size();i++){
            String postId = postIdList.get(i);

            SimplePost simplePost = getSimplePost(postId);
            if (simplePost != null)
                simplePostList.add(simplePost);
        }
        return simplePostList;
    }

    private boolean isPost(String postId){
        boolean flag = false;
        Post post = postMapper.getOne(postId);
        if (post != null){
            flag = true;
        }
        return flag;
    }
}
