package com.jre.assetregister.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "table_contacts")
public class Contact implements Serializable {
    public static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    @Column(name = "contact_id")
    private long contactId;

    @Column(name = "contact_first_name")
    private String firstName;

    @Column(name = "contact_last_name")
    private String lastName;

    // TODO: Add many to one relationship for current Assets
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id")
    private Set<Asset> currentAssets;


    // TODO: Add many to one relationship for previous Assets

    public Contact() {}

    public Contact(
            String firstName,
            String lastName
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Asset> getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(Set<Asset> currentAssets) {
        this.currentAssets = currentAssets;
    }
}
