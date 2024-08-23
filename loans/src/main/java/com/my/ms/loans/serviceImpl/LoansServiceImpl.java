package com.my.ms.loans.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.my.ms.loans.constants.LoansConstant;
import com.my.ms.loans.dto.LoansDTO;
import com.my.ms.loans.entity.Loans;
import com.my.ms.loans.exception.LoanAlreadyExistException;
import com.my.ms.loans.exception.ResourceNotFoundException;
import com.my.ms.loans.mapper.LoansMapper;
import com.my.ms.loans.repository.LoansRepository;
import com.my.ms.loans.service.LoansService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements LoansService {
	
	private LoansRepository loansRepository;

	@Override
	public void createLoan(String mobileNumber) {
		Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
		if(loan.isPresent()) {
			throw new LoanAlreadyExistException("A loan already exist with the given mobile number "+mobileNumber);
		}
		loansRepository.save(createNewLoan(mobileNumber));
	}
	
	private Loans createNewLoan(String mobileNumer) {
		Loans loans = new Loans();
		long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		loans.setMobileNumber(mobileNumer);
		loans.setLoanNumber(Long.toString(randomLoanNumber));
		loans.setLoanType(LoansConstant.HOME_LOAN);
		loans.setTotalLoan(LoansConstant.NEW_LOAN_LIMIT);
		loans.setAmountPaid(0);
		loans.setOutstandingAmount(LoansConstant.NEW_LOAN_LIMIT);
		//loans.setCreatedAt(LocalDateTime.now());
	    //loans.setCreatedBy("System");
		
		return loans;
	}

	@Override
	public LoansDTO fetchLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loans", "Mobile Number", mobileNumber));
		return LoansMapper.mapToLoansDTO(loans, new LoansDTO());
	}

	@Override
	public boolean updateLoan(LoansDTO loansDTO) {
		Loans loans = loansRepository.findByLoanNumber(loansDTO.getLoanNumber()).orElseThrow(
				() -> new ResourceNotFoundException("Loans", "Loan Number", loansDTO.getLoanNumber()));
		LoansMapper.mapToLoans(loansDTO, loans);
		loansRepository.save(loans);
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
		Long loanId = loans.getLoanId();
		loansRepository.deleteById(loanId);
		return true;
	}
	
}
