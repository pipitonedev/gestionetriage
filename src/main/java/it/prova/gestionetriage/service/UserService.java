package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetriage.model.User;

public interface UserService {

	List<User> listAll();

	Page<User> searchAndPaginate(User utenteExample, Integer pageNo, Integer pageSize, String sortBy);

	User get(Long idInput);

	User save(User input);
	
	User update(User input);

	void changeUserAbilitation(Long id);

}
