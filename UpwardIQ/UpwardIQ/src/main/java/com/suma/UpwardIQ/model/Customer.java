package com.suma.UpwardIQ.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "customers")
public class Customer {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true)
	    private String customerId;

	    private String name;

	    private String email;

	    private String gender;

	    private String customerType;

	    private String address;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(String customerId) {
	        this.customerId = customerId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getCustomerType() {
	        return customerType;
	    }

	    public void setCustomerType(String customerType) {
	        this.customerType = customerType;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

		public Customer(Long id, String customerId, String name, String email, String gender, String customerType,
				String address) {
			super();
			this.id = id;
			this.customerId = customerId;
			this.name = name;
			this.email = email;
			this.gender = gender;
			this.customerType = customerType;
			this.address = address;
		}

		public Customer() {}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", customerId=" + customerId + ", name=" + name + ", email=" + email
					+ ", gender=" + gender + ", customerType=" + customerType + ", address=" + address + "]";
		}
		

}

