package org.iclass.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommunityCommentsDto {
	private int idx;
	private int mref;
	private String writer;
	private String content;
	private Date createdAt;
	private String ip;
	private int heart;
}
