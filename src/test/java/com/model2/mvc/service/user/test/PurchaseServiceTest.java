package com.model2.mvc.service.user.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPruchase() throws Exception {
		
		User user = new User();
		Purchase pur = new Purchase();
		Product prod = new Product();
		
		user.setUserId("user12");
		prod.setProdNo(10026);
		
		pur.setBuyer(user);
		pur.setPurchaseProd(prod);
		pur.setPaymentOption("1");
		pur.setReceiverName("scott");
		pur.setReceiverPhone("010111111111");
		pur.setDivyAddr("서울시");
		pur.setDivyRequest("경비실");
		pur.setTranCode("1");
		pur.setDivyDate("20220805");
		
		purchaseService.addPurchase(pur);
		
		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
	}
	
//	@Test
	public void testGetPurchase() throws Exception {
		
		Purchase pur = new Purchase();
		//==> 필요하다면...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("경기도");
//		user.setEmail("test@test.com");
		
		pur = purchaseService.getPurchase(10010);

		//==> console 확인
		//System.out.println(user);
		
		//==> API 확인
		Assert.assertEquals(10026, pur.getPurchaseProd().getProdNo());
		Assert.assertEquals("user12", pur.getBuyer().getUserId());
		Assert.assertEquals("scott", pur.getReceiverName());
		Assert.assertEquals("010111111111", pur.getReceiverPhone());
		Assert.assertEquals("1  ", pur.getPaymentOption());
		Assert.assertEquals("서울시", pur.getDivyAddr());
		Assert.assertEquals("경비실", pur.getDivyRequest());
		Assert.assertEquals("2022-08-05 00:00:00", pur.getDivyDate());
		Assert.assertEquals("1  ", pur.getTranCode());
//		Assert.assertEquals("1", pur.getTranCode());

	}
	
//	@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase pur = purchaseService.getPurchase(10010);
		Assert.assertNotNull(pur);
		
		Assert.assertEquals(10026, pur.getPurchaseProd().getProdNo());
		Assert.assertEquals("user12", pur.getBuyer().getUserId());
		Assert.assertEquals("scott", pur.getReceiverName());
		Assert.assertEquals("010111111111", pur.getReceiverPhone());
		Assert.assertEquals("1  ", pur.getPaymentOption());
		Assert.assertEquals("서울시", pur.getDivyAddr());
		Assert.assertEquals("경비실", pur.getDivyRequest());
		Assert.assertEquals("2022-08-05 00:00:00", pur.getDivyDate());
		Assert.assertEquals("1  ", pur.getTranCode());
		
		pur.setPaymentOption("2");
		pur.setReceiverName("SCOTT");
		pur.setReceiverPhone("01022221111");
		pur.setDivyAddr("서울시 관악구");
		pur.setDivyRequest("문 앞");
		pur.setDivyDate("20220807");
		
		purchaseService.updatePurchase(pur);
		
		pur = purchaseService.getPurchase(10010);
		Assert.assertNotNull(pur);
		
		//==> console 확인
		//System.out.println(user);
			
		//==> API 확인
		Assert.assertEquals("2  ", pur.getPaymentOption());
		Assert.assertEquals("SCOTT", pur.getReceiverName());
		Assert.assertEquals("01022221111", pur.getReceiverPhone());
		Assert.assertEquals("서울시 관악구", pur.getDivyAddr());
		Assert.assertEquals("문 앞", pur.getDivyRequest());
		Assert.assertEquals("2022-08-07 00:00:00", pur.getDivyDate());
	 }

//	 @Test
	 public void testUpdateTranCode() throws Exception{
		 
		 Purchase pur = purchaseService.getPurchase(10010);
		 Assert.assertNotNull(pur);
		 
		 Assert.assertEquals("1  ", pur.getTranCode());
		 
		 pur.setTranCode("2");
		 
		 purchaseService.updateTranCode(pur);
		 
		 pur = purchaseService.getPurchase(10010);
		 Assert.assertNotNull(pur);
		 
		 //==> console 확인
		 //System.out.println(user);
		 
		 //==> API 확인
		 Assert.assertEquals("2  ", pur.getTranCode());
	 }
	 
	 //==>  주석을 풀고 실행하면....
//	 @Test
	 public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = purchaseService.getPurchaseList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println(list);
	 }
	 
}