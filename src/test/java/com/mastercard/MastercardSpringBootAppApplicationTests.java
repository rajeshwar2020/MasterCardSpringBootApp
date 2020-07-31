package com.mastercard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mastercard.controller.CityController;
import com.mastercard.service.MasterCardService;


@SpringBootTest

class MastercardSpringBootAppApplicationTests {

	
	@Autowired
	private MasterCardService service;
	
	@InjectMocks
	CityController city;
	
	@Test
	public void testGetConnection() {
		
		
		String actualResult = service.getConnection("Newark", "Boston");
		String expectedResult = "yes";
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testGetConnection_02() {
		
		
		String actualResult = service.getConnection("Atlanta", "Boston");
		String expectedResult = "no";
		assertEquals(expectedResult, actualResult);
	}
	
//	@Test
//	public void testDfs_01() throws NoSuchMethodException, SecurityException {
//		
//		Map<String, HashSet<String>> map = new HashMap<>();
//		Map<String, Boolean> visited = new HashMap<>();
//		
//		Method method = MasterCardService.class.getDeclaredMethod("dfs", String.class, String.class,Map.class, Map.class);
//		method.setAccessible(true);
//		MasterCardService service = new MasterCardService();
//		method.invoke(service, )
//	}
	
	
	

}
