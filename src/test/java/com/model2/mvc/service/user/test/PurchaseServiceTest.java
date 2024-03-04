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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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
		pur.setDivyAddr("�����");
		pur.setDivyRequest("����");
		pur.setTranCode("1");
		pur.setDivyDate("20220805");
		
		purchaseService.addPurchase(pur);
		
		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
	}
	
//	@Test
	public void testGetPurchase() throws Exception {
		
		Purchase pur = new Purchase();
		//==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");
		
		pur = purchaseService.getPurchase(10010);

		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
		Assert.assertEquals(10026, pur.getPurchaseProd().getProdNo());
		Assert.assertEquals("user12", pur.getBuyer().getUserId());
		Assert.assertEquals("scott", pur.getReceiverName());
		Assert.assertEquals("010111111111", pur.getReceiverPhone());
		Assert.assertEquals("1  ", pur.getPaymentOption());
		Assert.assertEquals("�����", pur.getDivyAddr());
		Assert.assertEquals("����", pur.getDivyRequest());
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
		Assert.assertEquals("�����", pur.getDivyAddr());
		Assert.assertEquals("����", pur.getDivyRequest());
		Assert.assertEquals("2022-08-05 00:00:00", pur.getDivyDate());
		Assert.assertEquals("1  ", pur.getTranCode());
		
		pur.setPaymentOption("2");
		pur.setReceiverName("SCOTT");
		pur.setReceiverPhone("01022221111");
		pur.setDivyAddr("����� ���Ǳ�");
		pur.setDivyRequest("�� ��");
		pur.setDivyDate("20220807");
		
		purchaseService.updatePurchase(pur);
		
		pur = purchaseService.getPurchase(10010);
		Assert.assertNotNull(pur);
		
		//==> console Ȯ��
		//System.out.println(user);
			
		//==> API Ȯ��
		Assert.assertEquals("2  ", pur.getPaymentOption());
		Assert.assertEquals("SCOTT", pur.getReceiverName());
		Assert.assertEquals("01022221111", pur.getReceiverPhone());
		Assert.assertEquals("����� ���Ǳ�", pur.getDivyAddr());
		Assert.assertEquals("�� ��", pur.getDivyRequest());
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
		 
		 //==> console Ȯ��
		 //System.out.println(user);
		 
		 //==> API Ȯ��
		 Assert.assertEquals("2  ", pur.getTranCode());
	 }
	 
	 //==>  �ּ��� Ǯ�� �����ϸ�....
//	 @Test
	 public void testGetPurchaseListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
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
	 	
	 	//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println(list);
	 }
	 
}