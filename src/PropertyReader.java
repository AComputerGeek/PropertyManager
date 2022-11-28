import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is PropertyReader class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class PropertyReader
{

    /**
     * This method reads the text file, and adds Strings (for each line) to an ArrayList<String> and then returns it.
     *
     * @param file is the text file that has property data.
     * @return an ArrayList of string that has strings (for each line) of the text file.
     * @throws FileNotFoundException if file not found.
     */
    public static ArrayList<String> readPropertyData(final File file)throws FileNotFoundException
    {
        ArrayList<String> propertyData;
        propertyData = new ArrayList<>();

        String eachLine;

        FileReader propertyFile = new FileReader(file);
        Scanner    scanner      = new Scanner(propertyFile);

        while(scanner.hasNext())
        {
            eachLine = scanner.nextLine();

            propertyData.add(eachLine);
        }

        scanner.close();

        return propertyData;
    }
}
