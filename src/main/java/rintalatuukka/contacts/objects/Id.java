package main.java.rintalatuukka.contacts.objects;

import main.java.rintalatuukka.contacts.Info;
import java.io.Console;

/**
 * This object contains the info for a finnish ID and methods to manipulate that
 * data.
 */

public class Id implements Info {
    private String info = "";
    public Id(String startId) {
        setInfo(startId);
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String newInfo) {
        if (Validation.idValidation(newInfo)) {
            info = newInfo;
        } else {
            throw new IllegalArgumentException("Invalid id")
        }
    }
    private boolean validate(String info) {

    }
    public void inputInfo() {
        boolean validInput = false;
        Contact addThis = new Contact();
        while (!validInput) {
            System.out.println("Please give the id of the contact:");
            validInput = true;
            try {
                addThis.setId(c.readLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        }
    }
    public String toString() {
        return info;
    }
}