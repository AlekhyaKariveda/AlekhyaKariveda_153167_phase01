package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Alekhya", "9988776655",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Akhi", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Greeshu", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9988776655", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9942221102", new BigDecimal(1500));
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("Eric", "999", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("Eric", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() 
	{
		service.createAccount("Alekhya", "9988776655", new BigDecimal(-100));
	}
	
	
	@Test
	public void testCreateAccount6() 
	{
		Customer actual=service.createAccount("K.Alekhya", "9849708743", new BigDecimal(5000));
		Customer expected=new Customer("K.Alekhya", "9849708743", new Wallet(new BigDecimal(5000)));
		
		assertEquals(expected, actual);
	}
	
	
	
	@Test
	public void testCreateAccount7() 
	{
		Customer actual=service.createAccount("nishi","9948559049", new BigDecimal(5000.75));
		Customer expected=new Customer("nishi","9948559049", new Wallet(new BigDecimal(5000.75)));
		
		assertEquals(expected, actual);
	}


	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance8() 
	{
		service.showBalance("");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance9() 
	{
		service.showBalance("12345");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance10() 
	{
		service.showBalance("9948559049");		
	}
	
	
	
	
	@Test
	public void testShowBalance11() 
	{
		Customer customer=service.showBalance("9988776655");
		BigDecimal expectedResult=new BigDecimal(9000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}

	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer12() 
	{
		service.fundTransfer("9948559049", "9948559049", new BigDecimal(5000));		
	}

	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer13() 
	{
		service.fundTransfer("9988776655", "", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer14() 
	{
		service.fundTransfer("", "9948559049", new BigDecimal(500));		
	}
	
	
	@Test
	public void testFundTransfer15() 
	{
		Customer customer=service.fundTransfer("9988776655", "9963242422", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer16() 
	{
		Customer customer=service.fundTransfer("9988776655", "9963242422", new BigDecimal(15000));	
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer17() 
	{
		service.fundTransfer("", "", new BigDecimal(500));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer18() 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount19() 
	{
		service.depositAmount(null, new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount20() 
	{
		service.depositAmount("", new BigDecimal(500));
	}
	
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount21() 
	{
		service.depositAmount("9942221102", new BigDecimal(0));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount22() 
	{
		service.depositAmount("9922950519", new BigDecimal(-1000));
	}
		
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdrawAmount23() 
	{
		service.withdrawAmount("9988776655", new BigDecimal(15000));	
	}
	
}
