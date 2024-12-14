package main.java.rintalatuukka.contacts.objects;
 
 /**
  * This interface provides a structure to the objects containing contact info,
  * so they may be used uniformly elsewhere.
  *
  * @author Tuukka Rintala
  */
public interface Info {
    public String getInfo();
    public void setInfo(String newInfo);
    public boolean validate(String info);
    public void inputInfo();
}