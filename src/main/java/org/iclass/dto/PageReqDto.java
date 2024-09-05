package org.iclass.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageReqDto {
	
	private int pageNo = 1;		// 요청(현재)페이지
	private int pageSize = 10;	// 한 페이지의 글 개수
	private int startNo;	// 페이지 글목록의 시작 rownum
	private int endNo;		// 페이지 글목록의 마지막 rownum
	
//	페이지리스트(글목록x)를 위한 속성
	private int startPage;
	private int endPage;
	private int totalPages;
	private int totalCount;
	
//	검색을 위한 속성
	private String column;
	private String keyword;

	private PageReqDto() {}
	
	public static PageReqDto of(int pageNo, int pageSize) {
		
		PageReqDto dto = new PageReqDto();
		dto.setPageNo(pageNo);
		dto.setPageSize(pageSize);
		
		return dto;
	}
}
