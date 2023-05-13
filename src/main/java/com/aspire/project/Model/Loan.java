package com.aspire.project.Model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.EnumType;
import com.aspire.project.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message = "Loan Amount is mandatory.! ")
	private int amount;
	@NotNull(message = "Loan term is mandatory.! ")
	private int loanTerm;
	@Enumerated(value = EnumType.STRING)
	private Status loanStatus;
	@ManyToOne
	@JsonIgnoreProperties({"loanList"})
	private Customer customer;
	@CreationTimestamp
	private Date createdOn;
	@OneToMany(mappedBy="loan",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({"loan"})
	List<Repayment> listofRepayments;
}
