package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cotroller.ShowBbsController;
import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
public class InsertCommentController {
	
	@Autowired
	private ShowBbsController showBbsController;

	@Autowired
	private CommentService commentService;
	
	/**
	 * コメントのフォームを初期化します.
	 * 
	 * @return コメントフォーム
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	/**
	 * コメントを投稿します.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト
	 * @param model
	 *            モデル
	 * @return 掲示板画面
	 */
	@RequestMapping(value = "/postcomment")
	public String postcomment(@Validated CommentForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showBbsController.form(model);
		}
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		commentService.save(comment);
		return "redirect:/joinedbbs";
	}

}
