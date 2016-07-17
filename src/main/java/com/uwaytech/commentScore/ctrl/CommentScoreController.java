package com.uwaytech.commentScore.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.commentScore.ctrl.dto.CommentScoreDto;
import com.uwaytech.commentScore.domain.CommentScore;
import com.uwaytech.commentScore.domain.FastComment;
import com.uwaytech.commentScore.domain.FastCommentInfo;
import com.uwaytech.commentScore.service.CommentScoreService;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.id.service.IdGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * CommentScoreController
 * 评论ctrl
 *
 * @author lyfang
 * @date 2016/6/13
 */
@RestController
@RequestMapping("/comment")
public class CommentScoreController {

    @Resource
    private CommentScoreService commentScoreService;

    @Resource
    private IdGeneratorService idGeneratorService;

    /**
     * 添加评论
     *
     * @param commentScore 评论对象
     * @return
     */
    @RequestMapping(value = "/v0.1", method = RequestMethod.POST)
    public Object addCommentScore(@RequestBody CommentScore commentScore) {
        if (null == commentScore) {
            new ParamMissException("参数不能为空!");
        }
        if (null == commentScore.getComments()) {
            new ParamMissException("评论参数comments不能为空");
        }
        if (null == commentScore.getScore()) {
            new ParamMissException("评分参数score不能为空");
        }
        if (null == commentScore.getMaterialId()) {
            new ParamMissException("素材参数materialId不能为空");
        }
        Long userId = SessionUtils.getUserId();
        commentScore.setUserId(userId);
        long id = idGeneratorService.generatorId(SessionUtils.getSchoolId());
        commentScore.setId(id);
        if (StringUtils.isNotEmpty(SessionUtils.getUserInfoWrapper().getUserInfo().getName())) {
            commentScore.setUserName(SessionUtils.getUserInfoWrapper().getUserInfo().getName());
        } else {
            commentScore.setUserName(SessionUtils.getUserInfoWrapper().getUserInfo().getPhone());
        }
        commentScore.setPhoto(SessionUtils.getUserInfoWrapper().getUserInfo().getPhoto());
        commentScoreService.addCommentScore(commentScore);
        return new SuccessResult();
    }

    /**
     * 查询素材对应的评论
     *
     * @param materialId 素材ID
     * @return 返回评论列表
     */
    @RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
    public Object queryCommentScores(@RequestParam(value = "materialId", required = false) Long materialId,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        if(null != materialId){
            Page<CommentScore> page = commentScoreService.queryCommentScores(materialId, pageNum, pageSize);

            Double avgScore = commentScoreService.queryAvgScore(materialId);

            return CommentScoreDto.commentScoreDto(page, avgScore);
        }else{
            Page<CommentScore> page = commentScoreService.queryAllCommentScores( pageNum, pageSize);

            return CommentScoreDto.commentDto(page);
        }
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/v0.1/{id}", method = RequestMethod.DELETE)
    public Object deleteCommentScore(@PathVariable("id") Long id) {
        commentScoreService.deleteCommentScore(id);
        return new SuccessResult();
    }
}
