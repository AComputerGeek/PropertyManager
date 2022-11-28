/**
 * This is Retail class. It's subtype of Property class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class Retail extends Property
{
    private final int     squareFootage;
    private final boolean customerParking;

    /**
     * @param priceUsd is the price in USD for this property. It must be positive.
     * @param address is the address for this property. It must be not null.
     * @param type is the type of this property. It must be one of these(case insensitive): Commercial, Residence, or Retail.
     * @param propertyId is the property id for this property. Its length must be between MIN_PROPERTY_ID_LENGTH and
     *                   MAX_PROPERTY_ID_LENGTH characters.
     * @param squareFootage is the amount of floor space available.
     * @param customerParking is true, if this property has customer parking; otherwise, it is false.
     */
    public Retail(final double priceUsd, final Address address, final String type, final String propertyId,
                  final int squareFootage, final boolean customerParking)
    {
        super(priceUsd, address, type, propertyId);

        this.squareFootage   = squareFootage;
        this.customerParking = customerParking;
    }

    /**
     * @return the amount of floor space available.
     */
    public int getSquareFootage()
    {
        return squareFootage;
    }

    /**
     * @return true, if this property has customer parking; otherwise, returns false.
     */
    public boolean hasCustomerParking()
    {
        return customerParking;
    }

    /**
     * @return the state of Retails types.
     */
    @Override
    public String toString()
    {
        return "\nRetail ["          +
                "squareFootage="     + squareFootage    +
                ", customerParking=" + customerParking  +
                ", toString()="      + super.toString() +
                ']';
    }
}
