package com.my.ms.loans.mapper;

import com.my.ms.loans.dto.LoansDTO;
import com.my.ms.loans.entity.Loans;

public class LoansMapper {
	
	public static LoansDTO mapToLoansDTO(Loans loan, LoansDTO loansDTO) {
		loansDTO.setLoanNumber(loan.getLoanNumber());
		loansDTO.setLoanType(loan.getLoanType());
		loansDTO.setMobileNumber(loan.getMobileNumber());
		loansDTO.setTotalLoan(loan.getTotalLoan());
		loansDTO.setAmountPaid(loan.getAmountPaid());
		loansDTO.setOutstandingAmount(loan.getOutstandingAmount());
		return loansDTO;
	}
	
	public static Loans mapToLoans(LoansDTO loansDTO, Loans loans) {
		loans.setLoanNumber(loansDTO.getLoanNumber());
		loans.setLoanType(loansDTO.getLoanType());
		loans.setMobileNumber(loansDTO.getMobileNumber());
		loans.setTotalLoan(loansDTO.getTotalLoan());
		loans.setAmountPaid(loansDTO.getAmountPaid());
		loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
		return loans;
	}
}
