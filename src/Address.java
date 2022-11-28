/**
 * This is Address class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class Address
{
    private final String unitNumber;
    private final int    streetNumber;
    private final String streetName;
    private final String postalCode;
    private final String city;

    public static final int MIN_UNIT_NUM_LENGTH            = 1;
    public static final int MAX_UNIT_NUM_LENGTH            = 4;
    public static final int MIN_STREET_NUM                 = 0;
    public static final int MAX_STREET_NUM                 = 999999;
    public static final int MIN_STREET_NAME_LENGTH         = 1;
    public static final int MAX_STREET_NAME_LENGTH         = 20;
    public static final int FIRST_GOOD_POSTAL_CODE_LENGTH  = 5;
    public static final int SECOND_GOOD_POSTAL_CODE_LENGTH = 6;
    public static final int MIN_CITY_LENGTH                = 1;
    public static final int MAX_CITY_LENGTH                = 30;

    /**
     * This is the address constructor.
     *
     * @param unitNumber is the unit number. Its length must be MIN_UNIT_NUM_LENGTH to MAX_UNIT_NUM_LENGTH characters.
     * @param streetNumber is the street number. It must be between MIN_STREET_NUM and MAX_STREET_NUM.
     * @param streetName is the street name. Its length must be MIN_STREET_NAME_LENGTH to MAX_STREET_NAME_LENGTH characters.
     * @param postalCode is the postal code. Its length must be FIRST_GOOD_POSTAL_CODE_LENGTH or
     *                   SECOND_GOOD_POSTAL_CODE_LENGTH characters.
     * @param city is the city name. Its length must be MIN_CITY_LENGTH to MAX_CITY_LENGTH characters.
     * @throws IllegalArgumentException if unit number is empty, or unit number's length is less than MIN_UNIT_NUM_LENGTH
     *                                  or greater than MAX_UNIT_NUM_LENGTH.
     * @throws IllegalArgumentException if street number is less than MIN_STREET_NUM or greater than MAX_STREET_NUM.
     * @throws NullPointerException if street name is null.
     * @throws IllegalArgumentException if street name is empty, or street name's length is less than
     *                                  MIN_STREET_NAME_LENGTH or greater than MAX_STREET_NAME_LENGTH.
     * @throws NullPointerException if postal code is null.
     * @throws IllegalArgumentException if postal code's length is not FIRST_GOOD_POSTAL_CODE_LENGTH or
     *                                  SECOND_GOOD_POSTAL_CODE_LENGTH.
     * @throws NullPointerException if city is null.
     * @throws IllegalArgumentException if city is empty, or city's length is less than MIN_CITY_LENGTH or greater than
     *                                  MAX_CITY_LENGTH.
     */
    public Address(final String unitNumber, final int streetNumber, final String streetName, final String postalCode,
                   final String city) throws IllegalArgumentException, NullPointerException
    {
        if(unitNumber != null)
        {
            if(unitNumber.equals(""))
            {
                throw new IllegalArgumentException("Invalid unit number!");
            }

            if((unitNumber.length() < MIN_UNIT_NUM_LENGTH) || (unitNumber.length() > MAX_UNIT_NUM_LENGTH))
            {
                throw new IllegalArgumentException("Invalid unit number: " + unitNumber);
            }
        }

        if((streetNumber < MIN_STREET_NUM) || (streetNumber > MAX_STREET_NUM))
        {
            throw new IllegalArgumentException("Invalid street number: " + streetNumber);
        }

        if(streetName == null)
        {
            throw new NullPointerException("Invalid street name: null");
        }
        else
        {
            if(streetName.equals(""))
            {
                throw new IllegalArgumentException("Invalid street name!");
            }

            if((streetName.length() < MIN_STREET_NAME_LENGTH) || (streetName.length() > MAX_STREET_NAME_LENGTH))
            {
                throw new IllegalArgumentException("Invalid street name: " + streetName);
            }
        }

        if(postalCode == null)
        {
            throw new NullPointerException("Invalid postal code: null");
        }
        else
        {
            if(postalCode.length() != FIRST_GOOD_POSTAL_CODE_LENGTH &&
                    postalCode.length() != SECOND_GOOD_POSTAL_CODE_LENGTH)
            {
                throw new IllegalArgumentException("Invalid postal code: " + postalCode);
            }
        }

        if(city == null)
        {
            throw new NullPointerException("Invalid city: null");
        }
        else
        {
            if(city.equals(""))
            {
                throw new IllegalArgumentException("Invalid city: ");
            }

            if((city.length() < MIN_CITY_LENGTH) || (city.length() > MAX_CITY_LENGTH))
            {
                throw new IllegalArgumentException("Invalid city: " + city);
            }
        }

        this.unitNumber   = unitNumber;
        this.streetNumber = streetNumber;
        this.streetName   = streetName;
        this.postalCode   = postalCode;
        this.city         = city;
    }

    /**
     * @return the unit number.
     */
    public String getUnitNumber()
    {
        return this.unitNumber;
    }

    /**
     * @return the street number.
     */
    public int getStreetNumber()
    {
        return this.streetNumber;
    }

    /**
     * @return the street name.
     */
    public String getStreetName()
    {
        return this.streetName;
    }

    /**
     * @return the postal code.
     */
    public String getPostalCode()
    {
        return this.postalCode;
    }

    /**
     * @return the city.
     */
    public String getCity()
    {
        return this.city;
    }

    /**
     * @return the state of Address objects
     */
    @Override
    public String toString()
    {
        return "Address ["        +
                "unitNumber='"    + unitNumber   + '\'' +
                ", streetNumber=" + streetNumber +
                ", streetName='"  + streetName   + '\'' +
                ", postalCode='"  + postalCode   + '\'' +
                ", city='"        + city         + '\'' +
                ']';
    }
}
