

import main.java.rintalatuukka.contacts.util.Validation;

public class Contact {
    private String id = "";
    private String[] firstNames = {""};
    private String lastName = "";
    private String phoneNumber = "";
    private String address = "";
    // private int zipCode;
    // private String city;
    private String email = "";
    public Contact() {
        
    }
    public Contact(String startId, String[] startFirstNames,
                   String startLastName, String startPhoneNumber,
                   String startAddress, String startEmail) {
        setId(startId);
        setFirstNames(startFirstNames);
        setLastName(startLastName);
        setPhoneNumber(startPhoneNumber);
        setAddress(startAddress);
        setEmail(startEmail);
    }
    public String getId() {
        return (id);
    }
    public void setId(String newId) {
        if (Validation.idValidation(newId)) {
            id = newId;
        } else {
            throw new IllegalArgumentException("Invalid id");
        }
    }
    public String getFirstNames() {
        return firstNames[0];
    }
    public void setFirstNames(String[] newFirstNames) {
        boolean namesValid = false;
        for(int i = 0; newFirstNames.length; i++) {
            if (Validation.nameValidation(newFirstNames[i])) {
                namesValid = true;
            } else {
                namesValid = false;
                throw new IllegalArgumentException("Invalid first names");
            }
        }
        if (namesValid) {
            firstNames = newFirstNames;
        }
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String newLastName) {
        if (true) {
            lastName = newLastName;
        }
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String newPhoneNumber) {
        if (Validation.phoneValidation(newPhoneNumber)) {
            phoneNumber = newPhoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
    public String getAddress() {
        return (address);
    }
    public void setAddress(String newAddress) {
        if (true) {
            address = newAddress;
        }
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String newEmail) {
        if (Validation.emailValidation(newEmail)) {
            email = newEmail;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }
    public String toString() {
        names = "";
        for (int i = 0; i < firstNames.length; i++) {
            names += firstNames[i] + " ";
        }
        return (id + " | " + names + "| " + lastName + " | " + phoneNumber + " | " + address + " | " + email);
    }
}