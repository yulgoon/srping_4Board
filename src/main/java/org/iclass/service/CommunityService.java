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
		// 페이지 리스트 계산을 위한 method
		pageDto = makePagination(pageDto);
		List<CommunityDto> list = mainDao.getList(pageDto);
		Map<String, Object> map = new HashMap<>();
		map.put("pageDto", pageDto);
		map.put("list", list);
		return map;
	}
	
	// 이 service에서만 사용할 method이므로 private으로 그냥 두겠습니다.
	private PageReqDto makePagination(PageReqDto pageDto) {
		int totalCount = mainDao.getCount();
		int pageSize = pageDto.getPageSize();
		double temp = (double)totalCount/pageSize;
		int totalPages = (int)Math.ceil(temp);
		int currentPage = pageDto.getPageNo();
//		pageNo(currentPage)는 1보다 작을 수 없다.
		currentPage = Math.max(1, currentPage);
//		pageNo(currentPage)는 totalPages보다 클 수 없다.
		currentPage = Math.min(totalPages, currentPage);
		// 현재 페이지를 기준으로 페이지 리스트 시작 번호 구하기
		int startPage = (currentPage - 1) / pageSize * 10 + 1; // e.g.) 현재 페이지: 14 => (14-1)/10*10+1 = 11
		// 페이지 리스트 크기가 10이라고 했을 때,
		// (현재 페이지 - 1)을 10으로 나눈 몫 * 10 + 1
		int endPage = Math.min(totalPages, startPage + pageSize - 1);
		// 9 대신에 pageSize - 1로 할 수 있습니다.
		
		int startNo = (currentPage - 1) * pageSize + 1; // (페이지번호-1)*(페이지의글개수)+1
		int endNo = startNo + (pageSize - 1);	 // ((페이지번호-1)*(페이지의글개수)+1) + (페이지의글개수-1)
		
		pageDto.setPageNo(currentPage);
		pageDto.setStartNo(startNo);
		pageDto.setEndNo(endNo);
		pageDto.setTotalCount(totalCount);
		pageDto.setTotalPages(totalPages);
		pageDto.setStartPage(startPage);
		pageDto.setEndPage(endPage);
		return pageDto;
	}

	//	글 상세보기는 조회수 증가도 해야 합니다. 조회수가 되지 않으면 또는 글조회가 되지 않았으면 transaction 처리를 해야 합니다.
	@Transactional	// transaction 단위에 해당하는 sql. 특히, insert, update, delete를 알아서 commit 또는 rollback 합니다.
	public CommunityDto read(int idx) {
		mainDao.setReadCount(idx);	// 조회 수 1 증가
//		CommunityDto dto = mainDao.selectByIdx(idx);
//		return dto;
		return mainDao.selectByIdx(idx);
	}
	
//	글 수정 후 글 상세보기 페이지로 돌아가기 때문에 read()를 그대로 사용하면 조회수가 또 증가합니다.
//	이 문제 해결을 위해 간단하게 수정을 위한 method(modify())를 하나 더 만들었습니다.
	public CommunityDto selectByIdx(int idx) {
		return mainDao.selectByIdx(idx);
	}
	
	public void write(CommunityDto dto) {
		mainDao.insert(dto);
	}
	
	public void modify(CommunityDto dto) {
		mainDao.update(dto);
	}

	public void remove(int idx) {
		mainDao.delete(idx);
	}
	
}
