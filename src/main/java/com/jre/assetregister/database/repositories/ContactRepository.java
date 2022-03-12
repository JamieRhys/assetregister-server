package com.jre.assetregister.database.repositories;

import com.jre.assetregister.database.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findById(@Param("contact_id") long id);
}
