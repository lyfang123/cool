package com.uwaytech.note.service;

import com.github.pagehelper.Page;
import com.uwaytech.note.domain.NoteInfo;

/**
 * Created by zeng on 2016/6/14.
 */
public interface INoteService {
    /**
     * 添加笔记
     * @param courseId 课程id
     * @param materialId 素材id
     * @param note 笔记内容
     * @param userId 用户id
     * @param title 笔记标题
     * @return
     */
    int addNote(Long courseId, Long materialId, String note, Long userId, String title);

    /**
     * 修改笔记
     * @param noteId 笔记id
     * @param note 笔记内容
     * @param title 笔记标题
     * @return
     */
    int updateNote(Long noteId, String note, String title);

    /**
     * 删除笔记
     * @param noteId 笔记id
     * @param userId
     * @return
     */
    int deleteNote(Long noteId, Long userId);

    /**
     * 笔记列表
     * @param userId 用户id
     * @param courseId
     *@param pageNum
     * @param pageSize   @return
     */
    Page<NoteInfo> getNoteList(Long userId, Long courseId, Integer pageNum, Integer pageSize);
}
