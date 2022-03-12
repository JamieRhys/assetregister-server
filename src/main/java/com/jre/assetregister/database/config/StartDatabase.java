package com.jre.assetregister.database.config;

import com.jre.assetregister.database.entities.Asset;
import com.jre.assetregister.database.entities.Contact;
import com.jre.assetregister.database.entities.LoginUser;
import com.jre.assetregister.database.repositories.AssetRepository;
import com.jre.assetregister.database.repositories.ContactRepository;
import com.jre.assetregister.database.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Configuration
public class StartDatabase {
    private static final Logger LOG = LoggerFactory.getLogger(StartDatabase.class);

    @Autowired ContactRepository contactRepository;

    @Bean
    CommandLineRunner initUserTable(UserRepository repository) {
        return args -> {
            if(((List<LoginUser>) repository.findAll()).size() == 0) {
                LOG.info("Preloading " + repository.save(
                        new LoginUser(
                                "Jamie",
                                "Edwards",
                                "jamie",
                                "$2a$12$Bw1hR14L2LfmKtqqjUuNquSP2L1iiyYT5olBdZLL4SHK4/1jMtVBm",
                                "ADMIN"
                        )));
            }
        };
    }

    @Bean
    CommandLineRunner initContactDatabase(ContactRepository repository) {
        return args -> {
            if(((List<Contact>) repository.findAll()).size() == 0) {
                LOG.info("Preloading " + repository.save(
                        new Contact(
                                "Jamie",
                                "Edwards"
                        )));
                LOG.info("Preloading " + repository.save(
                        new Contact(
                                "IT",
                                "Department"
                        )));
            }
        };
    }

    @Bean
    CommandLineRunner initAssetDatabase(AssetRepository repository) {
        return args -> {
            if(((List<Asset>) repository.findAll()).size() == 0) {
                Set<Contact> previousContacts = new HashSet<>();
                previousContacts.add(contactRepository.findById(1));
                previousContacts.add(contactRepository.findById(2));

                LOG.info("Preloading " + repository.save(
                        new Asset(
                                "LT1001",
                                "DELL",
                                "INSPIRON 15",
                                "DI152G55BL25",
                                Asset.AssetType.LAPTOP,
                                Asset.AssetLocation.STOCK,
                                Asset.AssetCondition.NEW,
                                Date.valueOf(LocalDate.of(2022, 3, 11)),
                                Date.valueOf(LocalDate.of(2022, 3, 11)),
                                contactRepository.findById(2),
                                previousContacts,
                                null,
                                null,
                                null,
                                null
                        )));
            }
        };
    }
}
