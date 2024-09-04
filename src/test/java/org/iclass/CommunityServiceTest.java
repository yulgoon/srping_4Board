package org.iclass;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.iclass.service.CommunityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Slf4j
class CommunityServiceTest {

	@Autowired
	CommunityService service;
	
	@DisplayName("서비스 자동 주입 확인하기")
	@Test
	void test() {
		assertNotNull(service);
	}
	
	@DisplayName("페이지 목록 가져오기")
	@Test
	void list() {
		Map<String, Object> map = service.pageSearchList(3);
		log.info(":::페이지 정보와 목록: {}:::", map);
	}

}
