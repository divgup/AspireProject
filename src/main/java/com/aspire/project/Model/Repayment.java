package com.aspire.project.Model;

import java.sql.Date;
import com.aspire.project.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

import lombok.Builder;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Repayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int repaymentnumber;
	private double actualAmount;
	@NotNull(message = "Repayment amount is mandatory.! ")
	private double paidAmount;
	@Enumerated(value = EnumType.STRING)
	private Status paymentstatus;
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"listofRepayments"})
	private Loan loan;
}
