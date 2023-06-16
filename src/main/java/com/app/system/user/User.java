package com.app.system.user;


import java.util.Arrays;
import java.util.List;

import com.app.appointment.Appointment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	private String email;
	private char[] password;
	private List<Appointment> appointments;
	
	
	
	public void clearPassword() {
		
		Arrays.fill(password, '\0');
	}

}
