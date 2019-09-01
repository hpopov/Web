package ua.kpi.iasa.web.lab3.rest.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.kpi.iasa.web.lab3.LoggableComponent;
import ua.kpi.iasa.web.lab3.converter.PublicUserConverter;
import ua.kpi.iasa.web.lab3.data.PublicUserData;
import ua.kpi.iasa.web.lab3.data.RegistrationData;
import ua.kpi.iasa.web.lab3.model.UserModel;
import ua.kpi.iasa.web.lab3.rest.UserController;
import ua.kpi.iasa.web.lab3.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class ApiUserController extends LoggableComponent implements UserController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PublicUserConverter publicUserConverter;

	@GetMapping("/{username}")
	@Override
	public PublicUserData getUserByLogin(@PathVariable("username") String username) {
		return publicUserConverter.modelToData(userService.getUserByUsername(username));
	}

	@PutMapping
	@Override
	public boolean registerUser(@RequestBody RegistrationData registrationData) {
		userService.createRegularUser(registrationData);
		return true;
	}

	@GetMapping
	@Override
	public List<PublicUserData> getAllUsers() {
		return userService.getAllUsers().stream().map(publicUserConverter::modelToData)
				.collect(Collectors.toList());
	}


	@PostMapping("/{username}")
	@Override
	public boolean updatePublicUserByLogin(@PathVariable String username,
			@RequestBody PublicUserData publicUserData) {
		publicUserData.setLogin(username);
		final Optional<UserModel> updatedUser = userService.updateUser(publicUserData);
		return updatedUser.isPresent();
	}
	
	@DeleteMapping("/{username}")
	@Override
	public boolean deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
		return true;
	}

}
