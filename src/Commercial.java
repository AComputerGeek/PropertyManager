/**
 * This is Commercial class. It's subtype of Property class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class Commercial extends Property
{
    private final boolean loadingDock;
    private final boolean highwayAccess;

    /**
     * @param priceUsd is the price in USD for this property. It must be positive.
     * @param address is the address for this property. It must be not null.
     * @param type is the type of this property. It must be one of these(case insensitive): Commercial, Residence, or Retail.
     * @param propertyId is the property id for this property. Its length must be between MIN_PROPERTY_ID_LENGTH and
     *                   MAX_PROPERTY_ID_LENGTH characters.
     * @param loadingDock is true, if this property has loading dock; otherwise, it is false.
     * @param highwayAccess is true, if this property has highway access; otherwise, it is false.
     */
    public Commercial(final double priceUsd, final Address address, final String type, final String propertyId,
                      final boolean loadingDock, final boolean highwayAccess)
    {
        super(priceUsd, address, type, propertyId);

        this.loadingDock   = loadingDock;
        this.highwayAccess = highwayAccess;
    }

    /**
     * @return true, if this property has loading dock; otherwise, returns false.
     */
    public boolean isLoadingDock()
    {
        return loadingDock;
    }

    /**
     * @return true, if this property has highway access; otherwise, returns false.
     */
    public boolean isHighwayAccess()
    {
        return highwayAccess;
    }

    /**
     * @return the state of Commercial types.
     */
    @Override
    public String toString()
    {
        return "\nCommercial ["    +
                "loadingDock="     + loadingDock      +
                ", highwayAccess=" + highwayAccess    +
                ", toString()="    + super.toString() +
                ']';
    }
}
