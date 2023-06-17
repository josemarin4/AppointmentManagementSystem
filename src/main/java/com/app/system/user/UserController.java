package com.app.system.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.appointment.Appointment;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {

		User user = userService.getUser(id);
		return ResponseEntity.ok(user);

	}


	@PostMapping("/add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User addedUser = userService.addUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);


	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {

		User updatedUser = userService.updateUser(user, id);

		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {

		boolean userDeleted = userService.deleteUser(id);

		if(userDeleted) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/get/{id}/appointments")
	public ResponseEntity<List<Appointment>> getUserAppointments(@PathVariable long id){

		List<Appointment> appts = userService.getUserAppointments(id);

		return ResponseEntity.ok(appts);

	}

	@GetMapping("/get/{userId}/appointments/{appointmentId}")
	public ResponseEntity<Appointment> getUserAppointment(@PathVariable long userId, @PathVariable long appointmentId){

		Appointment appt = userService.getUserAppointment(userId, appointmentId);

		return ResponseEntity.ok(appt);


	}
	
	@PostMapping("/add/{userId}/appointments")
	public ResponseEntity<Appointment> addUserAppointment(@Valid @RequestBody Appointment appt, @PathVariable long userId){
		
		Appointment addedAppt = userService.addUserAppointment(appt, userId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addedAppt);
		
	}
	
	@PutMapping("update/{userId}/appointments/{appointmentId}")
	public ResponseEntity<Appointment> updateUserAppointment(@PathVariable long userId, @PathVariable long appointmentId, @RequestBody Appointment appointment){
		
		Appointment updatedAppointment = userService.updateUserAppointment(userId, appointmentId, appointment);
		
		return ResponseEntity.ok(updatedAppointment);
	}
	
	@DeleteMapping("/delete/{userId}/appointments/{appointmentId}")
	public ResponseEntity<Void> deleteUserAppointment(@PathVariable long userId, @PathVariable long appointmentId){
		
		boolean appointmentDeleted = userService.deleteUserAppointment(userId, appointmentId);
			
		if(appointmentDeleted) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
