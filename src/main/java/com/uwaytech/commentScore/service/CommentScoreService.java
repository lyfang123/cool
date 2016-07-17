package com.uwaytech.commentScore.service;

import com.github.pagehelper.Page;
import com.uwaytech.commentScore.domain.CommentCategory;
import com.uwaytech.commentScore.domain.CommentScore;
import com.uwaytech.commentScore.domain.FastComment;
import com.uwaytech.commentScore.domain.FastCommentInfo;

import java.util.List;

/**
 * Created by rtyui on 2016/6/13.
 */
public interface CommentScoreService {
    /**
     * 添加评论
     * @param commentScore 评论对象
     */
    Integer addCommentScore(CommentScore commentScore);

    /**
     * 查询评论
     * @return
     * @param materialId 素材ID
     * @param pageNum 当前页
     * @param pageSize 每页条数
     */
    Page<CommentScore> queryCommentScores(Long materialId, Integer pageNum, Integer pageSize);

    /**
     * 查询素材评分的平均分
     * @param materialId 素材ID
     * @return
     */
    Double queryAvgScore(Long materialId);

    /**
     * 查询快捷评论
     * @param materialId 素材Id
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<FastCommentInfo> queryFastComment(Long materialId, Integer pageNum, Integer pageSize);

    /**
     * 添加快捷评论
     * @param commentCategory
     */
    void addCommentCategory(CommentCategory commentCategory);

    /**
     * 删除快捷评论
     * @param commentId
     */
    void deleteCommentCategory(Long commentId);

    /**
     * 查询快捷评论
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CommentCategory> queryCommentCategory(Integer pageNum, Integer pageSize);

    /**
     * 查询素材评论
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<CommentScore> queryAllCommentScores(Integer pageNum, Integer pageSize);

    /**
     * 删除素材评论
     * @param id
     */
    void deleteCommentScore(Long id);
}
