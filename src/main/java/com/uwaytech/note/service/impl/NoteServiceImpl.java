package com.uwaytech.note.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.note.dao.ExtendNoteMapper;
import com.uwaytech.note.dao.NoteMapper;
import com.uwaytech.note.domain.Note;
import com.uwaytech.note.domain.NoteExample;
import com.uwaytech.note.domain.NoteInfo;
import com.uwaytech.note.service.INoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zeng on 2016/6/14.
 */
@Service("noteService")
public class NoteServiceImpl implements INoteService {

	@Resource
	private NoteMapper noteMapper;
	@Resource
	private ExtendNoteMapper extendNoteMapper;

	@Override
	public int addNote(Long courseId, Long materialId, String notes, Long userId, String title) {
		Note note = new Note();
		note.setUserId(userId);
		note.setCourseId(courseId);
		note.setMaterialId(materialId);
		note.setNote(notes);
		note.setCreateTime(new Date());
		note.setUpdateTime(new Date());
		note.setTitle(title);
		return noteMapper.insertSelective(note);
	}

	@Override
	public int updateNote(Long noteId, String notes, String title) {
		Note note = new Note();
		note.setId(noteId);
		note.setTitle(title);
		note.setNote(notes);
		note.setUpdateTime(new Date());
		return noteMapper.updateByPrimaryKeySelective(note);
	}

	@Override
	public int deleteNote(Long noteId, Long userId) {
		NoteExample example = new NoteExample();
		example.createCriteria().andIdEqualTo(noteId).andUserIdEqualTo(userId);
		return noteMapper.deleteByExample(example);
	}

	@Override
	public Page<NoteInfo> getNoteList(Long userId, Long courseId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<NoteInfo> page = extendNoteMapper.getNoteList(userId, courseId);
		return page;
	}
}
