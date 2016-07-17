package com.uwaytech.note;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.note.domain.NoteInfo;
import com.uwaytech.note.service.INoteService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by zeng on 2016/6/14.
 */
public class NoteServiceTest extends JunitTestConfig {
	@Resource
	private INoteService noteService;

	@Test
	@Rollback(false)
	public void add() {
		Long userId = 1L;
		Long courseId = 2313385160161720L;
		Long materialId = 2313385076846592L;
		String title = "测试笔记";
		String note = "测试笔记，测试内容。";
		int result = noteService.addNote(courseId, materialId, note, userId, title);
		Assert.assertEquals(result == 1, true);
	}

	@Test
	public void update() {
		Long id = 1L;
		String title = "测试笔记修改";
		String note = "测试笔记，测试内容修改。";
		int result = noteService.updateNote(id, note, title);
		Assert.assertEquals(result == 1, true);
	}

	@Test
	public void delete() {
		Long id = 1L;
		Long userId = 1L;
		int result = noteService.deleteNote(id, userId);
		Assert.assertEquals(result == 1, true);
	}

	@Test
	public void query() {
		Long id = 1L;
		Integer pageNum =1;
		Integer pageSize = 10;
		Long courseId = 2222L;
		Page<NoteInfo> page = noteService.getNoteList(id, courseId, pageNum, pageSize);
		Assert.assertEquals(page.getResult().size()>0,true);
		for (NoteInfo noteInfo : page.getResult()) {
			System.out.println(noteInfo.getCourseName());
		}
	}
}
