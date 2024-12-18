package main.java.rintalatuukka.contacts.objects;
 
/**
 * This interface provides a structure to the objects containing contact info,
 * so they may be used uniformly elsewhere.
 *
 * @author Tuukka Rintala
 */
public interface Info {
    /**
     * This method is implemented to get the information that is stored onto a
     * text file from this object.
     *
     * The method is used specifically to store data from this object into a
     * text file, so it should be validated and stored in a way that will not
     * cause errors when importing it later.
     *
     * @return a String containing contact information that is formatted in a
     * way that can be read by this program.
     */
    public String getInfo();
    /**
     * This method is implemented to be able to change the information in the 
     * object while validating it.
     *
     * The method should be implemented such that each time new information is
     * stored it is validated, and throws an error if invalid and non-optional,
     * otherwise we risk storing invalidly formatted data in the text file.
     *
     * @param newInfo is a String containing the information you wish to store.
     */
    public void setInfo(String newInfo);
    /**
     * This method is implemented to get new information from the user and store
     * it into this object.
     *
     * The method should get inputs from the user until we get a valid one if
     * the information is non-optional. If it is optional, first ask the user
     * if they wish to add this information.
     */
    public void inputInfo();
    /**
     * This method is implemented to retrieve the information from the object in
     * a way that can be displayed correctly in the console.
     *
     * This method is called when we wish to print this objects information into
     * the console. This is relevant if the object contains more than one piece
     * of information that need to be separated differently in the text file
     * than the console.
     *
     * @return a String containing the information from this object that can be
     * displayed correctly.
     */
    public String toString();
}