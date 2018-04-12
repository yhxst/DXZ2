package com.saltedfish.app.controller.follow;

import com.saltedfish.app.bean.follow.Follow;
import com.saltedfish.app.bean.follow.FollowSum;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.intercept.Auth;
import com.saltedfish.app.mapper.FollowMapper;
import com.saltedfish.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试成功
 */

@Auth
@RequestMapping("/follow")
@RestController
public class FollowController {
    @Autowired
    private FollowMapper mapper;

    /**
     * 取得用户粉丝和关注的数量
     * @param response
     * @param userId
     * @return
     */
    @RequestMapping("/getFansSum/{userId}")
    public FollowSum getIdolAndFans(HttpServletResponse response, @PathVariable("userId")String userId) {
        if (userId != null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
        }
        //更新状态
        mapper.deleteFollowIsNull();
        //获取
        int fansSum = mapper.getFansSum(userId);
        int idolSum = mapper.getIdolSum(userId);
        FollowSum followSum = new FollowSum();
        followSum.setFansSum(fansSum);
        followSum.setIdolSum(idolSum);
        return followSum;
    }

    @RequestMapping("/add")
    public void insert(Follow follow) {
        follow.init(mapper);
        mapper.insert(follow);
    }

    //关注其他人
    @RequestMapping(value="/followOther/{idolId}")
    public void followOther(HttpServletRequest request,
                       HttpServletResponse response,
                       @PathVariable("idolId")String idolId) {
        String fansId = JwtUtil.parseToken(request);

        //检查是否已经关注
        if(mapper.getIsFollow(fansId,idolId) != null){
            ResponseInfo.setCode(response,Info.FOLLOW_ALREADY);
            return;
        }
        //没有关注,则进行关注
        Follow follow = new Follow();
        follow.init(mapper);
        follow.setFansUserId(fansId);
        follow.setIdolUserId(idolId);
        mapper.insert(follow);
    }

    //取消关注
    @RequestMapping(value="/remove/{idolId}")
    public void delete(HttpServletRequest request,
                       HttpServletResponse response,
                       @PathVariable("idolId") String idolId) {
        String fansId = JwtUtil.parseToken(request);
        if(mapper.getIsFollow(fansId,idolId) == null){
            //没有关注
            ResponseInfo.setCode(response,Info.FOLLOW_NOTFOLLOW);
            return;
        }
        //删除关注
        mapper.deleteFollow(idolId,fansId);
    }

}
