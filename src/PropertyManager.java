import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a Property Manager.
 *
 * @author Amir Armion
 * @version V.01
 */
public class PropertyManager
{
    private final Agency agency;

    public static final int RESIDENCE_PRICE    = 0;
    public static final int RESIDENCE_BEDROOMS = 1;
    public static final int RESIDENCE_POOL     = 2;
    public static final int RESIDENCE_TYPE     = 3;
    public static final int RESIDENCE_ID       = 4;
    public static final int RESIDENCE_STRATA   = 5;

    public static final int COMMERCIAL_PRICE   = 0;
    public static final int COMMERCIAL_TYPE    = 1;
    public static final int COMMERCIAL_ID      = 2;
    public static final int COMMERCIAL_LOADING = 3;
    public static final int COMMERCIAL_HIGHWAY = 4;

    public static final int RETAIL_PRICE            = 0;
    public static final int RETAIL_TYPE             = 1;
    public static final int RETAIL_ID               = 2;
    public static final int RETAIL_SQUARE_FOOT      = 3;
    public static final int RETAIL_CUSTOMER_PARKING = 4;

    // For Interface(Main Menu)
    public static final int GENERAL_QUERIES    = 1;
    public static final int RESIDENCE_QUERIES  = 2;
    public static final int COMMERCIAL_QUERIES = 3;
    public static final int RETAIL_QUERIES     = 4;
    public static final int EXIT               = 5;

    // For Interface(General Queries Menu)
    public static final int GENERAL_QUERIES_ID     = 1;
    public static final int GENERAL_QUERIES_PRICE  = 2;
    public static final int GENERAL_QUERIES_STREET = 3;
    public static final int GENERAL_QUERIES_TYPE   = 4;
    public static final int GENERAL_QUERIES_BACK   = 5;

    // For Interface(Residence Queries Menu)
    public static final int RESIDENCE_QUERIES_BEDROOM = 1;
    public static final int RESIDENCE_QUERIES_POOL    = 2;
    public static final int RESIDENCE_QUERIES_STRATA  = 3;
    public static final int RESIDENCE_QUERIES_BACK    = 4;

    // For Interface(Commercial Queries Menu)
    public static final int COMMERCIAL_QUERIES_LOADING_DOCK = 1;
    public static final int COMMERCIAL_QUERIES_HIGHWAY      = 2;
    public static final int COMMERCIAL_QUERIES_BACK         = 3;

    // For Interface(Retail Queries Menu)
    public static final int RETAIL_QUERIES_SQUARE_FOOT      = 1;
    public static final int RETAIL_QUERIES_CUSTOMER_PARKING = 2;
    public static final int RETAIL_QUERIES_BACK             = 3;

    /**
     * This is Assignment2's constructor.
     */
    public PropertyManager()
    {
        agency = new Agency("Assignment 2");
    }

    /**
     * This method gets the ArrayList<Address> and ArrayList<String> form AddressReader and PropertyReader, and uses
     * them to create subtype objects and then adds them to the Agency.HashMap<String, Property> properties.
     *
     * @throws FileNotFoundException if file address_data.txt or file property_data.txt not found.
     */
    public void init() throws FileNotFoundException
    {
        ArrayList<Address> adrData;
        ArrayList<String>  proData;

        File addressFile   = new File("address_data.txt");
        adrData            = AddressReader.readAddressData(addressFile);

        File propertyFile = new File("property_data.txt");
        proData           = PropertyReader.readPropertyData(propertyFile);

        for(int i = 0; i < proData.size(); i++)
        {
            // Properties with residence type in file property_data.txt
            if(proData.get(i).toLowerCase().contains("residence"))
            {
                String[] prData      = proData.get(i).split("\\|");

                double   priceUsd    = Double.parseDouble(prData[RESIDENCE_PRICE]);
                int      numBedrooms = Integer.parseInt(prData[RESIDENCE_BEDROOMS]);
                boolean  pool        = Boolean.parseBoolean(prData[RESIDENCE_POOL]);
                String   type        = prData[RESIDENCE_TYPE];
                String   id          = prData[RESIDENCE_ID];
                boolean  strata      = Boolean.parseBoolean(prData[RESIDENCE_STRATA]);

                Residence res = new Residence(priceUsd, adrData.get(i), type, id, numBedrooms, pool, strata);

                agency.addProperty(res);
            }
            else if(proData.get(i).toLowerCase().contains("commercial")) // Properties with commercial type in file property_data.txt
            {
                String[] prData     = proData.get(i).split("\\|");

                double  priceUsd    = Double.parseDouble(prData[COMMERCIAL_PRICE]);
                String  type        = prData[COMMERCIAL_TYPE];
                String  id          = prData[COMMERCIAL_ID];
                boolean loading     = Boolean.parseBoolean(prData[COMMERCIAL_LOADING]);
                boolean highway     = Boolean.parseBoolean(prData[COMMERCIAL_HIGHWAY]);

                Commercial com = new Commercial(priceUsd, adrData.get(i), type, id, loading, highway);

                agency.addProperty(com);
            }
            else // Properties with retail type in file property_data.txt
            {
                String[] prData     = proData.get(i).split("\\|");

                double  priceUsd    = Double.parseDouble(prData[RETAIL_PRICE]);
                String  type        = prData[RETAIL_TYPE];
                String  id          = prData[RETAIL_ID];
                int     squareFoot  = Integer.parseInt(prData[RETAIL_SQUARE_FOOT]);
                boolean customer    = Boolean.parseBoolean(prData[RETAIL_CUSTOMER_PARKING]);

                Retail ret = new Retail(priceUsd, adrData.get(i), type, id, squareFoot, customer);

                agency.addProperty(ret);
            }
        }
    }

    /**
     * This method provides the primary user interface through command prompts that will allow the user to choose which
     * search operations to perform. Each search will display results to the console.
     */
    public void doSearches()
    {
        boolean keepContinueMainMenu          = true;
        boolean keepContinueGeneralQueries    = true;
        boolean keepContinueResidenceQueries  = true;
        boolean keepContinueCommercialQueries = true;
        boolean keepContinueRetailQueries     = true;

        String  propId;
        String  streetName;
        String  type;

        int     userInput;
        int     minPriceUsd;
        int     maxPriceUsd;
        int     minBedroomNum;
        int     maxBedroomNum;
        int     minSquareFoot;

        System.out.println("\n>> Welcome to our Property search <<");

        while(keepContinueMainMenu)
        {
            Scanner mainScanner = new Scanner(System.in);
            System.out.println("\n>> Please choose one of following options:\n1. General Queries"    +
                                                                           "\n2. Residence Queries"  +
                                                                           "\n3. Commercial Queries" +
                                                                           "\n4. Retail Queries"     +
                                                                           "\n5. Exit");
            userInput = mainScanner.nextInt();

            if(userInput == GENERAL_QUERIES) // 1. General Queries
            {
                while(keepContinueGeneralQueries)
                {
                    Scanner generalQueriesScanner = new Scanner(System.in);
                    System.out.println("\n>> General Queries:\n1. By Property ID" +
                                                            "\n2. By Price"       +
                                                            "\n3. By Street"      +
                                                            "\n4. By Type"        +
                                                            "\n5. Back");
                    userInput = generalQueriesScanner.nextInt();

                    if(userInput == GENERAL_QUERIES_ID) // 1. By Property ID
                    {
                        Scanner idScanner = new Scanner(System.in);
                        System.out.print("\nPlease enter the Property ID: ");
                        propId = idScanner.next();

                        System.out.println(agency.getProperty(propId));

                        continue;
                    }
                    else if(userInput == GENERAL_QUERIES_PRICE) // 2. By Price
                    {
                        Scanner minPriceScanner = new Scanner(System.in);
                        System.out.print("\nPlease enter the Minimum Price in USD: ");
                        minPriceUsd = minPriceScanner.nextInt();

                        Scanner maxPriceScanner = new Scanner(System.in);
                        System.out.print("Please enter the Maximum Price in USD: ");
                        maxPriceUsd = maxPriceScanner.nextInt();

                        if(minPriceUsd <= maxPriceUsd)
                        {
                            if(agency.getPropertiesBetween(minPriceUsd, maxPriceUsd) == null)
                            {
                                System.out.println(agency.getPropertiesBetween(minPriceUsd, maxPriceUsd));
                            }
                            else
                            {
                                for(Property property: agency.getPropertiesBetween(minPriceUsd, maxPriceUsd))
                                {
                                    System.out.println(property);
                                }
                            }

                            continue;
                        }
                        else // If minimum price is greater than maximum price.
                        {
                            System.out.println("\n>> Sorry! Wrong inputs! Minimum Price must be less than or at least equal Maximum Price!");
                        }
                    }
                    else if(userInput == GENERAL_QUERIES_STREET) // 3. By Street
                    {
                        Scanner streetScanner = new Scanner(System.in);
                        System.out.print("\nPlease enter the Street Name: ");
                        streetName = streetScanner.nextLine();

                        if(agency.getPropertiesOn(streetName) == null)
                        {
                            System.out.println(agency.getPropertiesOn(streetName));
                        }
                        else
                        {
                            for(Address address: agency.getPropertiesOn(streetName))
                            {
                                System.out.println("\n" + address);
                            }
                        }

                        continue;
                    }
                    else if(userInput == GENERAL_QUERIES_TYPE) // 4. By Type
                    {
                        Scanner typeScanner = new Scanner(System.in);
                        System.out.print("\nPlease enter the Property Type (Residence, Commercial, or Retail): ");
                        type = typeScanner.next();

                        if(agency.getPropertiesOfType(type) == null)
                        {
                            System.out.println(agency.getPropertiesOfType(type));
                        }
                        else
                        {
                            for(Property property: agency.getPropertiesOfType(type))
                            {
                                System.out.println(property);
                            }
                        }

                        continue;
                    }
                    else if(userInput == GENERAL_QUERIES_BACK) // 5. Back
                    {
                        break;
                    }
                    else // Wrong input
                    {
                        System.out.println("\n>> Sorry! Wrong input! Please try again...");
                    }
                }
            }
            else if(userInput == RESIDENCE_QUERIES) // 2. Residence Queries
            {
                while(keepContinueResidenceQueries)
                {
                    Scanner residenceQueriesScanner = new Scanner(System.in);
                    System.out.println("\n>> Residence Queries:\n1. By Bedroom" +
                                                              "\n2. By Pool"    +
                                                              "\n3. By Strata"  +
                                                              "\n4. Back");
                    userInput = residenceQueriesScanner.nextInt();

                    if(userInput == RESIDENCE_QUERIES_BEDROOM) // 1. By Bedroom
                    {
                        Scanner minBedroomScanner = new Scanner(System.in);
                        System.out.print("\nPlease enter the Minimum Number of Bedrooms: ");
                        minBedroomNum = minBedroomScanner.nextInt();

                        Scanner maxBedroomScanner = new Scanner(System.in);
                        System.out.print("Please enter the Maximum Number of Bedrooms: ");
                        maxBedroomNum = maxBedroomScanner.nextInt();

                        if(minBedroomNum <= maxBedroomNum)
                        {
                            if(agency.getPropertiesWithBedrooms(minBedroomNum, maxBedroomNum) == null)
                            {
                                System.out.println(agency.getPropertiesWithBedrooms(minBedroomNum, maxBedroomNum));
                            }
                            else
                            {
                                for(Property property: agency.getPropertiesWithBedrooms(minBedroomNum, maxBedroomNum).values())
                                {
                                    System.out.println(property);
                                }
                            }
                        }
                        else // If minimum number of bedrooms is greater than maximum number of bedrooms.
                        {
                            System.out.println("\n>> Sorry! Wrong inputs! Minimum number must be less than or at least equal Maximum number!");
                        }

                        continue;
                    }
                    else if(userInput == RESIDENCE_QUERIES_POOL) // 2. By Pool
                    {
                        for(Property property: agency.getPropertiesWithPool())
                        {
                            System.out.println(property);
                        }

                        continue;
                    }
                    else if(userInput == RESIDENCE_QUERIES_STRATA) // 3. By Strata
                    {
                        for(Property property: agency.getPropertiesWithStrata())
                        {
                            System.out.println(property);
                        }

                        continue;
                    }
                    else if(userInput == RESIDENCE_QUERIES_BACK) // 4. Back
                    {
                        break;
                    }
                    else // Wrong input
                    {
                        System.out.println("\n>> Sorry! Wrong input! Please try again...");
                    }
                }
            }
            else if(userInput == COMMERCIAL_QUERIES) // 3. Commercial Queries
            {
                while(keepContinueCommercialQueries)
                {
                    Scanner commercialQueriesScanner = new Scanner(System.in);
                    System.out.println("\n>> Commercial Queries:\n1. By Loading Dock"   +
                                                               "\n2. By Highway Access" +
                                                               "\n3. Back");
                    userInput = commercialQueriesScanner.nextInt();

                    if(userInput == COMMERCIAL_QUERIES_LOADING_DOCK) // 1. By Loading Dock
                    {
                        for(Property property: agency.getPropertiesWithLoadingDock())
                        {
                            System.out.println(property);
                        }

                        continue;
                    }
                    else if(userInput == COMMERCIAL_QUERIES_HIGHWAY) // 2. By Highway Access
                    {
                        for(Property property: agency.getPropertiesWithHighwayAccess())
                        {
                            System.out.println(property);
                        }

                        continue;
                    }
                    else if(userInput == COMMERCIAL_QUERIES_BACK) // 3. Back
                    {
                        break;
                    }
                    else // Wrong input
                    {
                        System.out.println("\n>> Sorry! Wrong input! Please try again...");
                    }
                }
            }
            else if(userInput == RETAIL_QUERIES) // 4. Retail Queries
            {
                while(keepContinueRetailQueries)
                {
                    Scanner retailQueriesScanner = new Scanner(System.in);
                    System.out.println("\n>> Retail Queries:\n1. By Square Footage"   +
                                                           "\n2. By Customer Parking" +
                                                           "\n3. Back");
                    userInput = retailQueriesScanner.nextInt();

                    if(userInput == RETAIL_QUERIES_SQUARE_FOOT) // 1. By Square Footage
                    {
                        Scanner minSquareFootScanner = new Scanner(System.in);
                        System.out.print("\nPlease enter the Minimum Square Footage: ");
                        minSquareFoot = minSquareFootScanner.nextInt();

                        if(agency.getPropertiesWithSquareFootage(minSquareFoot) == null)
                        {
                            System.out.println(agency.getPropertiesWithSquareFootage(minSquareFoot));
                        }
                        else
                        {
                            for(Property property: agency.getPropertiesWithSquareFootage(minSquareFoot))
                            {
                                System.out.println(property);
                            }
                        }

                        continue;
                    }
                    else if(userInput == RETAIL_QUERIES_CUSTOMER_PARKING) // 2. By Customer Parking
                    {
                        for(Property property: agency.getPropertiesWithCustomerParking())
                        {
                            System.out.println(property);
                        }

                        continue;
                    }
                    else if(userInput == RETAIL_QUERIES_BACK) // 3. Back
                    {
                        break;
                    }
                    else // Wrong input
                    {
                        System.out.println("\n>> Sorry! Wrong input! Please try again...");
                    }
                }
            }
            else if(userInput == EXIT) // 5. Exit
            {
                System.out.println("\nGoodbye for now! :)");

                break;
            }
            else // Wrong input
            {
                System.out.println("\n>> Sorry! Wrong number! Please try again...");
            }
        }
    }

    /**
     *  This is the main method (our driver).
     *
     * @param args unused.
     */
    public static void main(String[] args)
    {
        try
        {
            PropertyManager a2 = new PropertyManager();

            a2.init();

            a2.doSearches();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("\n>> Error! " + e.getMessage());
        }
    }
}
