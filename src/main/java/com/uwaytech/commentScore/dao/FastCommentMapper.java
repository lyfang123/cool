package com.uwaytech.commentScore.dao;

import com.uwaytech.commentScore.domain.FastComment;
import com.uwaytech.commentScore.domain.FastCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FastCommentMapper {
    int countByExample(FastCommentExample example);

    int deleteByExample(FastCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FastComment record);

    int insertSelective(FastComment record);

    List<FastComment> selectByExample(FastCommentExample example);

    FastComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FastComment record, @Param("example") FastCommentExample example);

    int updateByExample(@Param("record") FastComment record, @Param("example") FastCommentExample example);

    int updateByPrimaryKeySelective(FastComment record);

    int updateByPrimaryKey(FastComment record);
}