package com.uwaytech.commentScore.dao;

import com.uwaytech.commentScore.domain.CommentScore;
import com.uwaytech.commentScore.domain.CommentScoreExample;
import com.uwaytech.commentScore.domain.FastComment;
import com.uwaytech.commentScore.domain.FastCommentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtendCommentScoreMapper {

	Double queryAvgScore(@Param("materialId") Long materialId);

	List<FastCommentInfo> queryFastComment(@Param("materialId") Long materialId, @Param("categoryId") Long categoryId);

	Long querySchoolCategoryId(@Param("materialId") Long materialId);

	Long querySupplierCategoryId(@Param("materialId") Long materialId);
}