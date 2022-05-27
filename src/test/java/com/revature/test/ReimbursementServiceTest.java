package com.revature.test;

import com.revature.dao.IReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

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

		List<Reimbursement> list = new ArrayList<>();
		doReturn(list).when(ird).getAllReimbursementsById(anyInt());

		rs.getAllReimbursementsById(anyInt());

		verify(ird).getAllReimbursementsById(anyInt());
	}



	@Test
	public void registerReimbursement() {

		doNothing().when(ird).createReimbursement(any());
		rs.registerReimbursement(100.90, new Date(), new Date(), "description", 2, 1);

		verify(ird).createReimbursement(any());


	}


	@Test
	public void testGetAllRequestsByStatus() {
		List<Reimbursement> list = new ArrayList<>();
		Reimbursement r = new Reimbursement(1,10.00,new Date(),new Date(),"This is for create request test", 1,2,3,2);
		list.add(r);

		doReturn(list).when(ird).readAllRequestsByStatus(r.getReimbursementAuthor(),r.getReimbursementStatus());

		rs.getAllRequestsByStatus(r.getReimbursementAuthor(),r.getReimbursementStatus());

		verify(ird).readAllRequestsByStatus(r.getReimbursementAuthor(),r.getReimbursementStatus());
	}

	@Test
	public void testGetAllRequestsByUserId() {
		List<Reimbursement> list = new ArrayList<>();
		Reimbursement r = new Reimbursement(1,10.00,new Date(),new Date(),"This is for create request test", 1,2,3,2);
		list.add(r);

		doReturn(list).when(ird).readAllRequestsById(r.getReimbursementAuthor());

		rs.getAllRequestsByUserId(r.getReimbursementAuthor());

		verify(ird).readAllRequestsById(r.getReimbursementAuthor());
	}

}