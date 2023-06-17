package com.app.system.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.appointment.Appointment;
import com.app.exception.AppointmentNotFoundException;
import com.app.exception.EmailAlreadyExistsException;
import com.app.exception.UserAlreadyExistsException;
import com.app.exception.UserNotFoundException;

@Service
public class UserService {

	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User addUser(User user) {

		String username = user.getUsername();
		String email = user.getEmail();

		if(userRepo.findByEmail(email) != null) {
			throw new EmailAlreadyExistsException("Email already in use.");
		}

		if(userRepo.findByUsername(username) != null) {
			throw new UserAlreadyExistsException("Username already taken.");
		}

		userRepo.save(user);
		return user;
	}

	public User getUser(long id) {

		User user = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found."));

		return user;
	}

	public User updateUser(User user, long id) {

		User currUser = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found."));

		currUser.setEmail(user.getEmail());
		currUser.setPassword(user.getPassword());
		currUser.setUsername(user.getUsername());

		userRepo.save(currUser);

		user.clearPassword();
		return currUser;
	}

	public boolean deleteUser(long id) {

		if(!userRepo.existsById(id)) {
			throw new UserNotFoundException("User not found");
		}

		userRepo.deleteById(id);
		return true;
	}

	public List<Appointment> getUserAppointments(long id){

		User user = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found."));

		return user.getAppointments();
	}

	public Appointment getUserAppointment(long userId, long appointmentId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found."));

		Appointment appt = user.getAppointments()
				.stream()
				.filter(curr -> curr.getId() == appointmentId)
				.findFirst()
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found."));
		

		return appt;
	}
	
	public Appointment addUserAppointment(Appointment appt, long userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
		
		user.getAppointments().add(appt);
		userRepo.save(user);
		
		return appt;
	}
	
	public Appointment updateUserAppointment(long userId, long appointmentId, Appointment appointment) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found."));
		
		Appointment currAppt = user.getAppointments()
				.stream()
				.filter(curr -> curr.getId() == appointment.getId())
				.findFirst()
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found."));
		
		currAppt.setName(appointment.getName());
		currAppt.setDate(appointment.getDate());
		currAppt.setPlace(appointment.getPlace());
		currAppt.setTime(appointment.getTime());
		
		userRepo.save(user);
		return currAppt;
	}

}
