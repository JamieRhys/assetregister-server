package com.jre.assetregister.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "table_assets")
public class Asset implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    @Column(name = "asset_id")
    private long assetId;

    @Column(name = "asset_tag", nullable = false, unique = true)
    private String assetTag;

    @Column(name = "asset_manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "asset_model", nullable = false)
    private String model;

    /** Also known as Serial Number or IMEI if mobile device. */
    @Column(name = "asset_identification_number", nullable = false)
    private String identificationNumber;

    @Column(name = "asset_type", nullable = false)
    private AssetType type;

    @Column(name = "asset_location", nullable = false)
    private AssetLocation location;

    @Column(name = "asset_condition", nullable = false)
    private AssetCondition condition;

    @Column(name = "asset_date_acquired", nullable = false)
    private Date dateAcquired;

    @Column(name = "asset_date_built")
    private Date dateBuilt;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact currentUser;

    // TODO: Add many to one relationship for previous users.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "previous_contact_id")
    private Set<Contact> previousUsers;

    @Column(name = "asset_date_retired")
    private Date dateRetired;

    @Column(name = "asset_retired_reason")
    private String retiredReason;

    @Column(name = "asset_date_disposed")
    private Date dateDisposed;

    @Column(name = "asset_disposed_reason")
    private String disposedReason;

    // TODO: Add one to many relationship for comments once implemented.d

    // TODO: Add one to many relationship for attachments once implemented.

    public Asset() {}

    /**
     * Asset Constructor
     * @param assetTag The Asset Tag for the item in question.
     * @param manufacturer The manufacturer of the item.
     * @param model The model of the item.
     * @param identificationNumber The serial number of the item or IMEI if this is a mobile phone.
     * @param type The item type.
     * @param location Where is the item currently located. Most of the time.
     * @param condition What condition is the item in
     * @param dateAcquired When was the item acquired (This tends to be delivery date).
     * @param dateBuilt If this is a laptop or desktop or mobile phone, this will be when the item was prepared.
     * @param currentUser Who is the current user?
     * @param previousUsers If there are previous users, list them.
     * @param dateRetired If the item has been retired, what was the date it was retired.
     * @param retiredReason What is the reason for it being retired.
     * @param dateDisposed If the item has been disposed of, what was the disposal date.
     * @param disposedReason What was the reason for the disposal (This could be the same reason it was retired).
     */
    public Asset(
            String assetTag,
            String manufacturer,
            String model,
            String identificationNumber,
            AssetType type,
            AssetLocation location,
            AssetCondition condition,
            Date dateAcquired,
            Date dateBuilt,
            Contact currentUser,
            Set<Contact> previousUsers,
            Date dateRetired,
            String retiredReason,
            Date dateDisposed,
            String disposedReason
    ) {
        this.assetTag = assetTag;
        this.manufacturer = manufacturer;
        this.model = model;
        this.identificationNumber = identificationNumber;
        this.type = type;
        this.location = location;
        this.condition = condition;
        this.dateAcquired = dateAcquired;
        this.dateBuilt = dateBuilt;
        this.currentUser = currentUser;
        //this.previousUsers = previousUsers;
        this.dateRetired = dateRetired;
        this.retiredReason = retiredReason;
        this.dateDisposed = dateDisposed;
        this.disposedReason = disposedReason;
    }

    public long getAssetId() {
        return assetId;
    }

    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public AssetLocation getLocation() {
        return location;
    }

    public void setLocation(AssetLocation location) {
        this.location = location;
    }

    public AssetCondition getCondition() {
        return condition;
    }

    public void setCondition(AssetCondition condition) {
        this.condition = condition;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public Date getDateBuilt() {
        return dateBuilt;
    }

    public void setDateBuilt(Date dateBuilt) {
        this.dateBuilt = dateBuilt;
    }

    public Contact getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Contact currentUser) {
        this.currentUser = currentUser;
    }

    public Date getDateRetired() {
        return dateRetired;
    }

    public void setDateRetired(Date dateRetired) {
        this.dateRetired = dateRetired;
    }

    public String getRetiredReason() {
        return retiredReason;
    }

    public void setRetiredReason(String retiredReason) {
        this.retiredReason = retiredReason;
    }

    public Date getDateDisposed() {
        return dateDisposed;
    }

    public void setDateDisposed(Date dateDisposed) {
        this.dateDisposed = dateDisposed;
    }

    public String getDisposedReason() {
        return disposedReason;
    }

    public void setDisposedReason(String disposedReason) {
        this.disposedReason = disposedReason;
    }

    public Set<Contact> getPreviousUsers() {
        return previousUsers;
    }

    public void setPreviousUsers(Set<Contact> previousUsers) {
        this.previousUsers = previousUsers;
    }

    //public Set<Contact> getPreviousUsers() {
    //    return previousUsers;
    //}

    //public void setPreviousUsers(Set<Contact> previousUsers) {
    //    this.previousUsers = previousUsers;
    //}

    public enum AssetLocation {
        STOCK("Stock"),
        BUILD_ROOM("Build Room"),
        IN_TRANSIT("In Transit"),
        WITH_USER("With User"),
        UNKNOWN("Unknown");

        private final String text;

        AssetLocation(final String text) { this.text = text; }

        @Override
        public String toString() { return this.text; }
    }

    public enum AssetCondition {
        NEW("New"),
        GOOD("Good"),
        FAULTY("Faulty"),
        FOR_PARTS("For Parts"),
        RETIRED("Retired"),
        DISPOSED("Disposed");

        private final String text;

        AssetCondition(final String text) { this.text = text; }

        @Override
        public String toString() { return this.text; }
    }

    public enum AssetType {
        LAPTOP("Laptop"),
        DESKTOP("Desktop"),
        MOBILE_PHONE("Mobile Phone"),
        MONITOR("Monitor"),
        PRINTER("Printer"),
        SCANNER("Scanner");

        private final String text;

        AssetType(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }
}
