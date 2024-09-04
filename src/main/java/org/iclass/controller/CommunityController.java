package org.iclass.controller;

import org.iclass.dto.CommunityDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/community")
public class CommunityController {

		@GetMapping("/list")
		public String list(@RequestParam(defaultValue = "1") int page, String columns, String keyword) {
			return "community/list";
		}
		
		@GetMapping("/write")	// 글 쓰기 화면
		public String write() {
			return "community/write";
		}
		
		@PostMapping("/write")	// 글 저장 후 글 목록으로 이동
		public String write(CommunityDto dto, Model model) {
			model.addAttribute("pageNo", 10);
			return "redirect:list";
		}
		
		@GetMapping("/modify")	// 글 수정 화면
		public String modify(int pageNo) {
			return "community/modify";
		}
		
		@PostMapping("/modify")	// 글 수정 후 글 목록으로 이동
		public String modify(int pageNo, CommunityDto dto, Model model) {
			model.addAttribute("idx", 10);
			return "redirect:list";
		}
		
		@GetMapping("/read")
		public String read(int idx, int pageNo, Model model) {
			model.addAttribute("idx", 10);
			return "community/read";
		}
		
		@PostMapping("/remove")
		public String remove(int idx, int pageNo) {
			return "redirect:list";
		}
		
}
