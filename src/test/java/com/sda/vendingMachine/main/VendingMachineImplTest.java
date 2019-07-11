package com.sda.vendingMachine.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sda.vendingMachine.models.Coin;

public class VendingMachineImplTest {
	private IVendingMachine atm;
	

	@Before
	public void setUp() {
		atm = VendingMachineImpl.getInstance();
		
	}

	@Test
	public void testGetInstance() {

		IVendingMachine atm2 = VendingMachineImpl.getInstance();
		assertNotEquals(null, atm2);
		assertEquals(atm, atm2);
	}

	@Test
	public void testAcceptCoin() {
		atm.acceptCoin(Coin.QUARTER);
		assertEquals(Coin.QUARTER.getVal(),((VendingMachineImpl) atm).getUserBalance());
	}
//
//	@Test
//	public void testSelectItem() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRefundRemainingBalance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAdminRefillMachine() {
//		fail("Not yet implemented");
//	}
//
}
