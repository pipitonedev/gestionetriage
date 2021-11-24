package it.prova.gestionetriage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.exceptions.UserNotFoundException;
import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.User;
import it.prova.gestionetriage.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Page<User> searchAndPaginate(User utenteExample, Integer pageNo, Integer pageSize, String sortBy) {

		Specification<User> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(utenteExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + utenteExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(utenteExample.getCognome()))
				predicates.add(
						cb.like(cb.upper(root.get("cognome")), "%" + utenteExample.getCognome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(utenteExample.getUsername()))
				predicates.add(
						cb.like(cb.upper(root.get("username")), "%" + utenteExample.getUsername().toUpperCase() + "%"));

			if (utenteExample.getDataRegistrazione() != null)
				predicates.add(
						cb.greaterThanOrEqualTo((root.get("DATACREAZIONE")), utenteExample.getDataRegistrazione()));

			if (utenteExample.getStatoUtente() != null)
				predicates.add(cb.like(cb.upper(root.get("STATO")), "%" + utenteExample.getStatoUtente() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return userRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public User get(Long idInput) {
		return userRepository.findById(idInput)
				.orElseThrow(() -> new UserNotFoundException("Element with id " + idInput + " not found."));
	}

	@Override
	public User save(User input) {
		input.setDataRegistrazione(new Date());
		return userRepository.save(input);
	}

	@Override
	public void changeUserAbilitation(Long id) {

		User caricoUser = get(id);
		
		if(caricoUser.getStatoUtente().equals(StatoUtente.ATTIVO)) {
			caricoUser.setStatoUtente(StatoUtente.DISABILITATO);
		}
		
		if(caricoUser.getStatoUtente().equals(StatoUtente.CREATO)) {
			caricoUser.setStatoUtente(StatoUtente.DISABILITATO);
		}

	}

	@Override
	public User update(User input) {
		return userRepository.save(input);
	}

}
