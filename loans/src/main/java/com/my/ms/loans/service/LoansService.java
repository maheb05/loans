package com.my.ms.loans.service;

import com.my.ms.loans.dto.LoansDTO;

public interface LoansService {
	
	void createLoan(String mobileNumber);
	LoansDTO fetchLoan(String mobileNumber);
	boolean updateLoan(LoansDTO loansDTO);
	boolean deleteLoan(String mobileNumber);
}
