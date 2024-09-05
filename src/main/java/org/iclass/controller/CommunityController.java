package org.iclass.controller;

import java.util.Map;

import org.iclass.dto.CommunityDto;
import org.iclass.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

		private final CommunityService service;
	
		@GetMapping("/list")
		public String list(@RequestParam(defaultValue = "1") int pageNo, String columns, String keyword, Model model) {
			Map<String, Object> map = service.pageSearchList(pageNo);
			model.addAttribute("list", map.get("list"));
			model.addAttribute("pageDto", map.get("pageDto"));
			return "community/list";
		}
		
		@GetMapping("/write")	// 글 쓰기 화면
		public String write(@RequestParam(defaultValue = "1") int pageNo, Model model) {
			model.addAttribute("pageNo", pageNo);
			return "community/write";
		}
		
		@PostMapping("/write")	// 글 저장 후 글 목록으로 이동
		public String write(CommunityDto dto, RedirectAttributes reAttr) {
			log.info(":::글쓰기 입력 dto: {}:::", dto);
			service.write(dto);
			reAttr.addFlashAttribute("message", "글이 등록되었습니다.");
			return "redirect:list";
		}
		
		@GetMapping("/modify")	// 글 수정 화면
		public String modify(int idx, @RequestParam(defaultValue = "1") int pageNo, Model model) {
			CommunityDto dto = service.selectByIdx(idx);
			model.addAttribute("dto", dto);
			model.addAttribute("pageNo", pageNo);
			return "community/modify";
		}
		
		@PostMapping("/modify")	// 글 수정 후 글 목록으로 이동
		public String modify(int pageNo, CommunityDto dto, RedirectAttributes reAttr) {
			service.modify(dto);
			reAttr.addAttribute("idx", dto.getIdx());
			reAttr.addAttribute("pageNo", pageNo);
			reAttr.addFlashAttribute("message", "글이 수정되었습니다.");
			return "redirect:read";
		}
		
		@GetMapping("/read")
		public String read(int idx, @RequestParam(defaultValue = "1") int pageNo, Model model) {
			model.addAttribute("dto", service.read(idx));
			model.addAttribute("pageNo", pageNo);
			return "community/read";
		}
		
		@PostMapping("/remove")
		public String remove(int idx, int pageNo, RedirectAttributes reAttr) {
			service.remove(idx);
			reAttr.addAttribute("pageNo", pageNo);
			reAttr.addFlashAttribute("message", "글이 삭제되었습니다.");
			return "redirect:list";
		}
		
}
