/**
 * This is Property class. It's supertype for Residence class, Commercial class, and Retail class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class Property
{
    private final Address address;
    private final String  type;
    private final String  propertyId;

    private double priceUsd; // This is not final, because we have set method for priceUsd

    public static final int MIN_PROPERTY_ID_LENGTH = 1;
    public static final int MAX_PROPERTY_ID_LENGTH = 6;
    public static final int NONE                   = 0;

    /**
     * @param priceUsd is the price in USD for this property. It must be positive.
     * @param address is the address for this property. It must be not null.
     * @param type is the type of this property. It must be one of these(case insensitive): Commercial, Residence,
     *             or Retail.
     * @param propertyId is the property id for this property. Its length must be between MIN_PROPERTY_ID_LENGTH and
     *                   MAX_PROPERTY_ID_LENGTH characters.
     * @throws IllegalArgumentException if price in USD of this property is less than NONE.
     * @throws NullPointerException if address of this property is null.
     * @throws NullPointerException if type of this property is null.
     * @throws IllegalArgumentException if type of this property is not one of these(case insensitive): Commercial,
     *                                  Residence, or Retail.
     * @throws NullPointerException if property id of this property is null.
     * @throws IllegalArgumentException if property id of this property is empty, or length of property id is less than
     *                                  MIN_PROPERTY_ID_LENGTH or greater than MAX_PROPERTY_ID_LENGTH characters.
     */
    public Property(final double priceUsd, final Address address, final String type, final String propertyId)
                    throws IllegalArgumentException, NullPointerException
    {
        if(priceUsd < NONE)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUsd);
        }

        if(address == null)
        {
            throw new NullPointerException("Invalid address: null");
        }

        if(type == null)
        {
            throw new NullPointerException("Invalid property type: null");
        }
        else
        {
            if(!type.equalsIgnoreCase("residence")  &&
               !type.equalsIgnoreCase("commercial") &&
               !type.equalsIgnoreCase("retail"))
            {
                throw new IllegalArgumentException("Invalid property type: " + type);
            }
        }

        if(propertyId == null)
        {
            throw new NullPointerException("Invalid property id: null");
        }
        else
        {
            if(propertyId.equals(""))
            {
                throw new IllegalArgumentException("Invalid property id!");
            }

            if((propertyId.length() < MIN_PROPERTY_ID_LENGTH) || (propertyId.length() > MAX_PROPERTY_ID_LENGTH))
            {
                throw new IllegalArgumentException("Invalid property id: " + propertyId);
            }
        }

        this.priceUsd   = priceUsd;
        this.address    = address;
        this.type       = type;
        this.propertyId = propertyId;
    }

    /**
     * @return the price in USD of this property.
     */
    public double getPriceUsd()
    {
        return priceUsd;
    }

    /**
     * @return the address of this property.
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * @return the type of this property.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return the property id of this property.
     */
    public String getPropertyId()
    {
        return propertyId;
    }

    /**
     * This method sets the price in USD for this property.
     *
     * @param priceUsd is the price in USD for this property.
     * @throws IllegalArgumentException if price in USD of this property is less than NONE.
     */
    public void setPriceUsd(final double priceUsd)
    {
        if(priceUsd < NONE)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUsd);
        }

        this.priceUsd = priceUsd;
    }

    /**
     * @return the state of Property objects
     */
    @Override
    public String toString()
    {
        return "Property ["      +
                "priceUsd="      + priceUsd   +
                ", address="     + address    +
                ", type='"       + type       + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ']';
    }
}
