package com.app.system.user;


import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	private String email;
	private char[] password;
	
	@OneToMany
	private List<Appointment> appointments;
	
	
	
	public void clearPassword() {
		
		Arrays.fill(password, '\0');
	}
	
	@JsonManagedReference
	public List<Appointment> getAppointments(){
		 return appointments;
	}

}
