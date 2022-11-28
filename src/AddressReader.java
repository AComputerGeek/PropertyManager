import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is AddressReader class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class AddressReader
{
    public static final int INDEX_UNIT_NUM    = 0;
    public static final int INDEX_STREET_NUM  = 1;
    public static final int INDEX_STREET_NAME = 2;
    public static final int INDEX_POSTAL_CODE = 3;
    public static final int INDEX_CITY        = 4;

    /**
     * This method reads the text file, and adds Address objects to an ArrayList<Address> and then returns it.
     *
     * @param file is the text file that has address data.
     * @return address objects as an ArrayList.
     * @throws FileNotFoundException if file not found.
     */
    public static ArrayList<Address> readAddressData(final File file) throws FileNotFoundException
    {
        ArrayList<Address> addressData;
        addressData = new ArrayList<>();

        String   eachLine;
        String[] data;

        String   unitNumber;
        int      streetNumber;
        String   streetName;
        String   postalCode;
        String   city;

        FileReader addressFile = new FileReader(file);
        Scanner    scanner     = new Scanner(addressFile);

        while(scanner.hasNext())
        {
            eachLine = scanner.nextLine();

            data     = eachLine.split("\\|");

            unitNumber    = data[INDEX_UNIT_NUM];
            streetNumber  = Integer.parseInt(data[INDEX_STREET_NUM]);
            streetName    = data[INDEX_STREET_NAME];
            postalCode    = data[INDEX_POSTAL_CODE];
            city          = data[INDEX_CITY];

            Address adr = new Address(unitNumber, streetNumber, streetName, postalCode, city);

            addressData.add(adr);
        }

        scanner.close();

        return addressData;
    }
}
