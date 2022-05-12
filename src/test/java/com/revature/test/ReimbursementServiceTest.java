package com.revature.test;

import com.revature.Driver;
import com.revature.dao.IReimbursementDao;
import com.revature.dao.IUserDao;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.revature.test.UserServiceTest.iud;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

public class ReimbursementServiceTest {



	@Mock
	static IReimbursementDao ird;

	//We also have to use inject mocks, because UserService depends on UserDao
	@InjectMocks
	static ReimbursementService rs;
	@Before
	public void initReimbursementTests() {
		MockitoAnnotations.openMocks(this);

	}
	@Test
	public void getAllReimbursements() {

		List<Reimbursement> list = new ArrayList<>();
		doReturn(list).when(ird).getAllReimbursements();

		rs.getAllReimbursements();

		verify(ird).getAllReimbursements();

	}

	@Test
	public void getAllPendingReimbursement() {

		List<Reimbursement> list = new ArrayList<>();
		doReturn(list).when(ird).getAllPendingReimbursements();

		rs.getAllPendingReimbursement();

		verify(ird).getAllPendingReimbursements();
	}

	@Test
	public void getAllResolvedReimbursements() {
		List<Reimbursement> list = new ArrayList<>();
		doReturn(list).when(ird).getAllResolvedReimbursements();

		rs.getAllResolvedReimbursements();

		verify(ird).getAllResolvedReimbursements();

	}

	@Test
	public void getAllReimbursementsById() {

		Reimbursement r = new Reimbursement(100,23.44,new Date(), new Date(), "description", 1, 2, 1, 1);
		doReturn(r).when(ird).getAllReimbursementsById(r.getReimbursementId());

		rs.getAllReimbursementsById(r.getReimbursementId());

		verify(ird).getAllReimbursementsById(r.getReimbursementId());
	}

	@Test
	public void approvePending() {
		doNothing().when(ird).approvePendingReimbursement(any());
		rs.approvePending(any());

		verify(ird).approvePendingReimbursement(any());

	}

	@Test
	public void denyPending() {
		doNothing().when(ird).denyPendingReimbursement(any());
		rs.denyPending(any());

		verify(ird).approvePendingReimbursement(any());

	}

	@Test
	public void registerReimbursement() {
		doNothing().when(ird).createReimbursement(any());
		rs.registerReimbursement(100.90, new Date(), new Date(), "description", 1, 2, 1);

		verify(ird).createReimbursement(any());


	}

	@Test
	public void getAllPendingRequests() {

		List<Reimbursement> list = new ArrayList<>();
		doReturn(list).when(ird).getAllPendingReimbursements();
		rs.getAllPendingRequests(23);

		verify(ird).getAllPendingReimbursements();

	}

}