package com.uwaytech.commentScore.dao;

import com.uwaytech.commentScore.domain.CommentScore;
import com.uwaytech.commentScore.domain.CommentScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentScoreMapper {
    int countByExample(CommentScoreExample example);

    int deleteByExample(CommentScoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentScore record);

    int insertSelective(CommentScore record);

    List<CommentScore> selectByExample(CommentScoreExample example);

    CommentScore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommentScore record, @Param("example") CommentScoreExample example);

    int updateByExample(@Param("record") CommentScore record, @Param("example") CommentScoreExample example);

    int updateByPrimaryKeySelective(CommentScore record);

    int updateByPrimaryKey(CommentScore record);
}