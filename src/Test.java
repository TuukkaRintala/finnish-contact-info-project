import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.List;
// import main.java.rintalatuukka.contacts.objects.Contact;
import main.java.rintalatuukka.contacts.util.Validation;
public class Test {
    public static void main(final String[] args) {
        // System.out.println(Charset.defaultCharset());
        // List<Contact> contactList = new List<Contact>;
        System.out.println(Validation.nameValidation("Tuukka"));
        System.out.println(Validation.nameValidation("TuKka"));
        System.out.println(Validation.nameValidation("tuukka"));
    }
}
