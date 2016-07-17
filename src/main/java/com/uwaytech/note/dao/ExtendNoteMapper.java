package com.uwaytech.note.dao;

import com.github.pagehelper.Page;
import com.uwaytech.note.domain.NoteInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zeng on 2016/6/14.
 */
public interface ExtendNoteMapper {
	Page<NoteInfo> getNoteList(@Param("userId")Long userId, @Param("courseId")Long courseId);
}
