package it.prova.gestionetriage.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import it.prova.gestionetriage.model.User;
import it.prova.gestionetriage.service.UserService;

@RestController
@RequestMapping(value = "/api/user", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/{idInput}")
	public User getAutomobile(@PathVariable(required = true) Long idInput) {
		return userService.get(idInput);
	}

	@GetMapping
	public List<User> getAll() {
		return userService.listAll();
	}

	@PostMapping("/search")
	public ResponseEntity<Page<User>> searchAndPagination(@RequestBody User userExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {

		Page<User> results = userService.searchAndPaginate(userExample, pageNo, pageSize, sortBy);

		return new ResponseEntity<Page<User>>(results, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public User createNewUser(@RequestBody User userInput) {
		return userService.save(userInput);
	}

	@PutMapping("/{id}")
	public User updateUser(@RequestBody User userInput, @PathVariable Long id) {
		User userToUpdate = userService.get(id);
		userToUpdate.setUsername(userInput.getUsername());
		userToUpdate.setNome(userInput.getNome());
		userToUpdate.setCognome(userInput.getCognome());
		userToUpdate.setStatoUtente(userInput.getStatoUtente());
		return userService.update(userToUpdate);
	}

	@PutMapping("/change/{id}")
	public void cambiaStatoUtente(@PathVariable(required = true) Long id) {
		userService.changeUserAbilitation(id);
	}

}
