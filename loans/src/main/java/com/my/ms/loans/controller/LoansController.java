package com.my.ms.loans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.ms.loans.constants.LoansConstant;
import com.my.ms.loans.dto.ErrorResponseDTO;
import com.my.ms.loans.dto.LoansDTO;
import com.my.ms.loans.dto.ResponseDTO;
import com.my.ms.loans.service.LoansService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class LoansController {
	
	private LoansService loansService;
	
	@Operation(
			summary = "Create Loans REST API",
			description = "REST API to create new Loan EazyBank"
			)
	@PostMapping("/createloan")
	public ResponseEntity<ResponseDTO> createLoan(@RequestParam
			@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
			String mobileNumber) {
		loansService.createLoan(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(LoansConstant.STATUS_201, LoansConstant.MESSAGE_201));
	}
	
	@Operation(
			summary = "Fetch Loan Details REST API",
			description = "REST API to fetch Loan details based on a mobile number"
			)
	@GetMapping("/fetch")
	public ResponseEntity<LoansDTO> fetchLoan(@RequestParam
			@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
			String mobileNumber) {
		LoansDTO loan = loansService.fetchLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loan);
	}
	
	@Operation(summary = "Update Loan Details REST API", description = "REST API to update Loan details based on a loan number")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error"
						,content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
	})
	@PutMapping("/updateloan")
	public ResponseEntity<ResponseDTO> updateLoanDetails(@RequestBody @Valid LoansDTO loansDTO) {
		boolean updateLoan = loansService.updateLoan(loansDTO);
		if(updateLoan) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LoansConstant.STATUS_200, LoansConstant.MESSAGE_200));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(LoansConstant.STATUS_417, LoansConstant.MESSAGE_417_UPDATE));
		}
	}
	
	@Operation(summary = "Delete Loan Details REST API", description = "REST API to delete Loan details based on a mobile number")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Status Error"
						,content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
	})
	@DeleteMapping("deleteloan")
	public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam 
			@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
			String mobileNumber) {
		boolean deleteLoan = loansService.deleteLoan(mobileNumber);
		if(deleteLoan) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LoansConstant.MESSAGE_200, LoansConstant.MESSAGE_200));
		}else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LoansConstant.STATUS_417, LoansConstant.MESSAGE_417_DELETE));
		}
	}
}
