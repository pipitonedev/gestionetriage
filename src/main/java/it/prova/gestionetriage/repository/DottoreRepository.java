package it.prova.gestionetriage.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetriage.model.Dottore;

public interface DottoreRepository
		extends PagingAndSortingRepository<Dottore, Long>, JpaSpecificationExecutor<Dottore> {

	Dottore findByCodiceDipendente(String codice);

}
