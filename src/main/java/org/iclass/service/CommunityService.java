package org.iclass.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.dao.CommunityCommentsMapper;
import org.iclass.dao.CommunityMapper;
import org.iclass.dto.CommunityDto;
import org.iclass.dto.PageReqDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Component
@Service
@Slf4j
@RequiredArgsConstructor
public class CommunityService {
	//게시판 기능 서비스 - 메인글과 댓글은 하나의 기능. 서비스도 하나만 만들기로 했습니다.
	//자동 주입이 필요합니다. - final로 하고 생성자 주입을 해야합니다.
	private final CommunityMapper mainDao;
	private final CommunityCommentsMapper cmtDao;
	
//	@RequiredArgsConstructor를 안 쓰고 직접 커스텀 생성자를 만들어 주입하는 경우
/*	public CommunityService(CommunityMapper mainDao, CommunityCommentsMapper cmtDao) {
		this.mainDao = mainDao;
		this.cmtDao = cmtDao;
	}
*/
	
	// 페이지네이션 + 검색 기능으로 글목록 만들기
	public Map<String, Object> pageSearchList(int pageNo) {
		// 현재 페이지를 전달 받아 해당 글목록을 만듭니다.
		// 한 페이지에 10개의 글 보여주기
		PageReqDto pageDto = PageReqDto.of(pageNo, 10);
		List<CommunityDto> list = mainDao.getList(pageDto);
		Map<String, Object> map = new HashMap<>();
		map.put("pageDto", pageDto);
		map.put("list", list);
		return map;
	}
	
//	글 상세보기는 조회수 증가도 해야 합니다. 조회수가 되지 않으면 또는 글조회가 되지 않았으면 transaction 처리를 해야 합니다.
	@Transactional	// transaction 단위에 해당하는 sql. 특히, insert, update, delete를 알아서 commit 또는 rollback 합니다.
	public CommunityDto read(int idx) {
		mainDao.setReadCount(idx);
//		CommunityDto dto = mainDao.selectByIdx(idx);
//		return dto;
		return mainDao.selectByIdx(idx);
	}
	
	public void write(CommunityDto dto) {
		mainDao.insert(dto);
	}
	
	public void modify(CommunityDto dto) {
		mainDao.update(dto);
	}
}
