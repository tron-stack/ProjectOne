package com.revature.test;

import com.revature.Driver;
import com.revature.dao.IReimbursementDao;
import com.revature.dao.IUserDao;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class ReimbursementServiceTest {



	@Mock
	static IReimbursementDao ird;

	//We also have to use inject mocks, because UserService depends on UserDao
	@InjectMocks
	static ReimbursementService rs;
	@BeforeClass
	public void setUpDao() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

	}
	@Test
	public void getAllReimbursements() {
		assertNotNull(ird.getAllReimbursements());


	}

	@Test
	public void getAllPendingReimbursement() {
		assertNotNull(ird.getAllPendingReimbursements());

	}

	@Test
	public void getAllResolvedReimbursements() {
		assertNotNull(ird.getAllResolvedReimbursements());

	}

	@Test
	public void getAllReimbursementsById() {
		int id =4;
		assertEquals(ird.getAllReimbursementsById(id), )
	}

	@Test
	public void approvePending() {

	}

	@Test
	public void denyPending() {

	}

	@Test
	public void registerReimbursement() {

	}

	@Test
	public void getAllPendingRequests() {

	}

}