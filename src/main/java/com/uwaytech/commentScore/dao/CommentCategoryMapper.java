package com.uwaytech.commentScore.dao;

import com.uwaytech.commentScore.domain.CommentCategory;
import com.uwaytech.commentScore.domain.CommentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentCategoryMapper {
    int countByExample(CommentCategoryExample example);

    int deleteByExample(CommentCategoryExample example);

    int deleteByPrimaryKey(Long commentId);

    int insert(CommentCategory record);

    int insertSelective(CommentCategory record);

    List<CommentCategory> selectByExample(CommentCategoryExample example);

    CommentCategory selectByPrimaryKey(Long commentId);

    int updateByExampleSelective(@Param("record") CommentCategory record, @Param("example") CommentCategoryExample example);

    int updateByExample(@Param("record") CommentCategory record, @Param("example") CommentCategoryExample example);

    int updateByPrimaryKeySelective(CommentCategory record);

    int updateByPrimaryKey(CommentCategory record);
}