package com.uwaytech.commentScore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.commentScore.dao.CommentCategoryMapper;
import com.uwaytech.commentScore.dao.CommentScoreMapper;
import com.uwaytech.commentScore.dao.ExtendCommentScoreMapper;
import com.uwaytech.commentScore.dao.FastCommentMapper;
import com.uwaytech.commentScore.domain.*;
import com.uwaytech.commentScore.service.CommentScoreService;
import com.uwaytech.cool.common.enums.RoleTypeEnum;
import com.uwaytech.cool.common.util.Calculator;
import com.uwaytech.schoolCourse.ctrl.dto.CourseRecommendInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * CommentScoreServiceImpl
 *  评论service实现类
 * @author lyfang
 * @date 2016/6/13
 */

@Service
public class CommentScoreServiceImpl implements CommentScoreService {

    @Resource
    private CommentScoreMapper commentScoreMapper;

    @Resource
    private ExtendCommentScoreMapper extendCommentScoreMapper;

    @Resource
    private FastCommentMapper fastCommentMapper;

    @Resource
    private CommentCategoryMapper commentCategoryMapper;

    /**
     * 添加评论
     *
     * @param commentScore 评论对象
     */
    @Override
    public Integer addCommentScore(CommentScore commentScore) {
        commentScore.setCreateTime(new Date());
        //保存快捷评论
        if(null != commentScore.getCommentIds() && commentScore.getCommentIds().length > 0){
            for (Long id:commentScore.getCommentIds()){
                //查询当前课程是否已经有快捷评论
                FastCommentExample example = new FastCommentExample();
                example.createCriteria().andCommentIdEqualTo(id).andMaterialIdEqualTo(commentScore.getMaterialId());
                List<FastComment> fastComments = fastCommentMapper.selectByExample(example);
                FastComment fastComment = null;
                if(!fastComments.isEmpty()){
                    //当前课程已经有快捷评论，直接加1
                    fastComment = fastComments.get(0);
                    fastComment.setCount(fastComment.getCount() + 1);
                    fastCommentMapper.updateByPrimaryKey(fastComment);
                }else{
                    //当前课程没有快捷评论，插入快捷评论
                    fastComment = new FastComment();
                    fastComment.setCommentId(id);
                    fastComment.setCount(1L);
                    fastComment.setMaterialId(commentScore.getMaterialId());
                    fastCommentMapper.insert(fastComment);
                }
            }
        }
        //插入评论内容
        return commentScoreMapper.insert(commentScore);
    }

    /**
     * 查询评论列表
     *
     * @return
     * @param materialId 素材ID
     * @param pageNum 当前页
     * @param pageSize 每页条数
     */
    @Override
    public Page<CommentScore> queryCommentScores(Long materialId, Integer pageNum, Integer pageSize) {
        CommentScoreExample example = new CommentScoreExample();
        example.createCriteria().andMaterialIdEqualTo(materialId);
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(pageNum, pageSize);
        return (Page<CommentScore>)commentScoreMapper.selectByExample(example);
    }

    /**
     * 查询素材评分的平均分
     *
     * @param materialId 素材ID
     * @return
     */
    @Override
    public Double queryAvgScore(Long materialId) {
        return extendCommentScoreMapper.queryAvgScore(materialId);
    }

    @Override
    public Page<FastCommentInfo> queryFastComment(Long materialId, Integer pageNum, Integer pageSize) {
        //查询课程对应的顶级分类Id
        Calculator cal = new Calculator(materialId);
        Long categoryId = null;
        if(cal.getType().equals(RoleTypeEnum.SCHOOL)){
            categoryId = extendCommentScoreMapper.querySchoolCategoryId(materialId);
        }else if(cal.getType().equals(RoleTypeEnum.SUPPLIER)){
            categoryId = extendCommentScoreMapper.querySupplierCategoryId(materialId);
        }
        PageHelper.startPage(pageNum,pageSize);
        return (Page<FastCommentInfo>)extendCommentScoreMapper.queryFastComment(materialId, categoryId);
    }

    @Override
    public void addCommentCategory(CommentCategory commentCategory) {
        commentCategoryMapper.insert(commentCategory);
    }

    @Override
    public void deleteCommentCategory(Long commentId) {
        commentCategoryMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public Page<CommentCategory> queryCommentCategory(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page<CommentCategory>)commentCategoryMapper.selectByExample(null);
    }

    @Override
    public Page<CommentScore> queryAllCommentScores(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page<CommentScore>)commentScoreMapper.selectByExample(null);
    }

    @Override
    public void deleteCommentScore(Long id) {
        commentScoreMapper.deleteByPrimaryKey(id);
    }
}
