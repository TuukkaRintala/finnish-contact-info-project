import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.List;
// import main.java.rintalatuukka.contacts.objects.Contact;
import main.java.rintalatuukka.contacts.util.Validation;
public class Test {
    public static void main(final String[] args) {
        String csv = "310199-547C;Joni;Nikula;+358402245666;Hämeenkatu 17.33800.Tampere;joni.nikula@gmail.com";
        String[] splitCsv = csv.split("[;]", 6);
        for (int i = 0; i < splitCsv.length; i++) {
            System.out.println(splitCsv[i]);
        }
    }
}
