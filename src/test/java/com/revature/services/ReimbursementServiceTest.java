package com.revature.services;

import com.revature.dao.IReimbursementDao;
import com.revature.models.Reimbursement;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ReimbursementServiceTest extends TestCase {

    @Mock
    static IReimbursementDao ird;

    @InjectMocks
    static ReimbursementService rs;

    @Test
    public void testRegisterReimbursement() {

        // overriding dao functionality by return expected return value
        when(ird.createReimbursement(any())).thenReturn(new Reimbursement(10.00,new Date(),new Date(),"This is for create request test",1,2,3,2));

        // now call actual method for testing
        rs.registerReimbursement(10.00,new Date(),new Date(),"This is for create request test", 1,2,3,2);

        // verifying that the reimbursement service called ReimbursementDao.createReimbursement()
        verify(ird).createReimbursement(any());
    }

    public void testGetAllRequestsByStatus() {
    }

    public void testGetAllRequestsByUserId() {
    }
}