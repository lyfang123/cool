package com.uwaytech.note.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.note.domain.NoteInfo;
import com.uwaytech.note.service.INoteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zeng on 2016/6/14.
 */
@RestController
@RequestMapping(value = "/note")
public class NoteController {

	@Resource
	private INoteService noteService;

	/**
	 * 新增课程笔记
	 *
	 * @param courseId
	 * @param materialId
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/v0.1", method = RequestMethod.POST)
	public Object addNote(@RequestParam(value = "courseId") Long courseId,
	                      @RequestParam(value = "materialId") Long materialId,
	                      @RequestParam(value = "note") String note,
	                      @RequestParam(value = "title") String title) {
		Long userId = SessionUtils.getUserId();
		noteService.addNote(courseId, materialId, note, userId, title);
		return new SuccessResult();
	}

	/**
	 * 修改课程笔记
	 *
	 * @param noteId
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{noteId}", method = RequestMethod.PUT)
	public Object updateNote(@PathVariable(value = "noteId") Long noteId,
	                         @RequestParam(value = "note") String note,
	                         @RequestParam(value = "title") String title) {
		noteService.updateNote(noteId, note, title);
		return new SuccessResult();
	}

	/**
	 * 课程笔记删除
	 *
	 * @param noteId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{noteId}", method = RequestMethod.DELETE)
	public Object deleteNote(@PathVariable(value = "noteId") Long noteId) {
		Long userId = SessionUtils.getUserId();
		noteService.deleteNote(noteId, userId);
		return new SuccessResult();
	}

	/**
	 * 课程笔记列表
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
	public Object getNoteList(@RequestParam(value = "courseId", required = false) Long courseId,
	                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Long userId = SessionUtils.getUserId();
		Page<NoteInfo> page = noteService.getNoteList(userId, courseId, pageNum, pageSize);
		return page;
	}
}
