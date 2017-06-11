package fi.uba.parking.domain;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "CREDITS")
public class Credit {

	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@Column(name="amount")
	private BigInteger amount;
	
	@Column(name="expiration_date")
	private Date expirationDate;

	public Credit() {
	}

	public Credit(BigInteger amount, Date expirationDate) {
		this.amount = amount;
		this.expirationDate = expirationDate;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void substract(BigInteger amount) {
		this.amount = this.amount.subtract(amount);
	}
	
	public void add(BigInteger amount) {
		this.amount = this.amount.add(amount);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(amount).append(expirationDate).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Credit)) {
			return false;
		}
		Credit other = (Credit) obj;
		return new EqualsBuilder().append(amount, other.amount).append(expirationDate, other.expirationDate).isEquals();
	}

}
