package com.uwaytech.commentScore.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.commentScore.ctrl.dto.CommentCategoryDto;
import com.uwaytech.commentScore.ctrl.dto.CommentScoreDto;
import com.uwaytech.commentScore.ctrl.dto.FastCommentDto;
import com.uwaytech.commentScore.domain.CommentCategory;
import com.uwaytech.commentScore.domain.CommentScore;
import com.uwaytech.commentScore.domain.FastComment;
import com.uwaytech.commentScore.domain.FastCommentInfo;
import com.uwaytech.commentScore.service.CommentScoreService;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.SuccessResult;
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
@RequestMapping("/fastComment")
public class FastCommentController {

    @Resource
    private CommentScoreService commentScoreService;

    /**
     * 添加快捷评论
     * @param commentCategory
     * @return
     */
    @RequestMapping(value = "/v0.1", method = RequestMethod.POST)
    public Object addCommentCategory(@RequestBody CommentCategory commentCategory) {
        if (null == commentCategory.getCategoryId()) {
            new ParamMissException("分类参数categoryId不能为空");
        }
        if (StringUtils.isBlank(commentCategory.getCommentName())) {
            new ParamMissException("快捷评分名称commentName不能为空");
        }
        commentScoreService.addCommentCategory(commentCategory);
        return new SuccessResult();
    }

    /**
     * 删除快捷评论
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/v0.1/{commentId}", method = RequestMethod.DELETE)
    public Object deleteCommentCategory(@PathVariable("commentId") Long commentId) {
        commentScoreService.deleteCommentCategory(commentId);
        return new SuccessResult();
    }

    /**
     * 查询快捷评论
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/v0.1", method = RequestMethod.GET)
    public Object queryCommentCategory(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<CommentCategory> page = commentScoreService.queryCommentCategory(pageNum, pageSize);
        return CommentCategoryDto.toDto(page);
    }

    /**
     * 查询快捷评分
     * @param materialId 素材Id
     * @return
     */
    @RequestMapping(value = "/v0.1/{materialId}", method = RequestMethod.GET)
    public Object queryFastComment(@RequestParam("materialId") Long materialId,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<FastCommentInfo> page = commentScoreService.queryFastComment(materialId,pageNum,pageSize);
        return FastCommentDto.convertDto(page);
    }
}
