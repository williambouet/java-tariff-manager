package dev.wcs.nad.tariffmanager.persistence.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.wcs.nad.tariffmanager.persistence.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


}