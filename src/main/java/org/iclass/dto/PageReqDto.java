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

	private PageReqDto() {}
	
	public static PageReqDto of(int pageNo, int pageSize) {
		int startNo = (pageNo - 1) * pageSize + 1; // (페이지번호-1)*(페이지의글개수)+1
		int endNo = startNo + (pageSize - 1);	 // ((페이지번호-1)*(페이지의글개수)+1) + (페이지의글개수-1)
		
		PageReqDto dto = new PageReqDto();
		dto.setPageNo(pageNo);
		dto.setPageSize(pageSize);
		dto.setStartNo(startNo);
		dto.setEndNo(endNo);
		
		return dto;
	}
}
