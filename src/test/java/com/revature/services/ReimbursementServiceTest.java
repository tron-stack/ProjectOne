package com.revature.services;

import com.revature.dao.IReimbursementDao;
import com.revature.models.Reimbursement;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReimbursementServiceTest {

    @BeforeClass
    public static void setupBeforeClass(){
        System.out.println("This runs once before the entire class.");
    }

    @Before
    public void setupBeforeMethods(){
        System.out.println("This runs once before each method in this class.");
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDownAfterMethod(){
        System.out.println("This will run once after each method in this class.");
    }
    @Mock
    static IReimbursementDao ird;

    @InjectMocks
    static ReimbursementService rs;

    @Test
    public void testRegisterReimbursement() {

        // overriding dao functionality by return expected return value
        doNothing().when(ird).createReimbursement(any());

        // now call actual method for testing
        rs.registerReimbursement(10.00,new Date(),new Date(),"This is for create request test", 1,2,3,2);

        // verifying that the reimbursement service called ReimbursementDao.createReimbursement()
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