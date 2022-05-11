package com.revature.test;

import com.revature.dao.IReimbursementDao;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class ReimbursementServiceTest {

	@Test
	public void getAllReimbursements() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

		assertNotNull(ird.getAllReimbursements());

	}

	@Test
	public void getAllPendingReimbursement() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);
		assertNotNull(ird.getAllPendingReimbursements());

	}

	@Test
	public void getAllResolvedReimbursements() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

	}

	@Test
	public void getAllReimbursementsById() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

	}

	@Test
	public void approvePending() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

	}

	@Test
	public void denyPending() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

	}

	@Test
	public void registerReimbursement() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

	}

	@Test
	public void getAllPendingRequests() {
		IReimbursementDao ird = Mockito.mock(IReimbursementDao.class);

	}
}