/**
 * This is Residence class. It's subtype of Property class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class Residence extends Property
{
    private final int     numberOfBedrooms;
    private final boolean swimmingPool;
    private final boolean strata;

    public static final int MIN_BEDROOM_NUM = 1;

    /**
     * @param priceUsd is the price in USD for this property. It must be positive.
     * @param address is the address for this property. It must be not null.
     * @param type is the type of this property. It must be one of these(case insensitive): Commercial, Residence, or Retail.
     * @param propertyId is the property id for this property. Its length must be between MIN_PROPERTY_ID_LENGTH and
     *                   MAX_PROPERTY_ID_LENGTH characters.
     * @param numberOfBedrooms is number of bedrooms, and it must be not less than MIN_BEDROOM_NUM.
     * @param swimmingPool is true, if this property has swimming pool; otherwise, it is false.
     * @param strata is true, if this property is part of strata; otherwise, it is false.
     * @throws IllegalArgumentException if number of bedrooms is less than MIN_BEDROOM_NUM.
     */
    public Residence(final double priceUsd, final Address address, final String type, final String propertyId,
                     final int numberOfBedrooms, final boolean swimmingPool, final boolean strata)
    {
        super(priceUsd, address, type, propertyId);

        if(numberOfBedrooms < MIN_BEDROOM_NUM)
        {
            throw new IllegalArgumentException("Invalid number of bedroom: " + numberOfBedrooms);
        }

        this.numberOfBedrooms = numberOfBedrooms;
        this.swimmingPool     = swimmingPool;
        this.strata           = strata;
    }

    /**
     * @return the number of bedrooms.
     */
    public int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    /**
     * @return true, if this property has swimming pool; otherwise, returns false.
     */
    public boolean hasSwimmingPool()
    {
        return swimmingPool;
    }

    /**
     * @return true if this property is part of a strata; otherwise, returns false
     */
    public boolean isStrata()
    {
        return strata;
    }

    /**
     * @return the state of Residence types.
     */
    @Override
    public String toString()
    {
        return "\nResidence ["      +
                "numberOfBedrooms=" + numberOfBedrooms +
                ", swimmingPool="   + swimmingPool     +
                ", strata="         + strata           +
                ", toString()="     + super.toString() +
                ']';
    }
}
