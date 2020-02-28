package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.service.ArticleService;


@Controller
@RequestMapping("/insert-article")
public class InsertArticleController {

	@Autowired
	private ShowBbsController showBbsController;
	
	
	@Autowired
	private ArticleService articleService;
	
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	@RequestMapping("")
	public String postarticle(@Validated ArticleForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showBbsController.form(model);
		}
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleService.save(article);
		return "redirect:/bbs";
	}
}
