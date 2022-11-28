import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This is Agency class.
 *
 * @author Amir Armion
 * @version V.01
 */
public class Agency
{
    private final String name;

    private final HashMap<String, Property> properties;

    public static final int MIN_NAME_LENGTH  = 1;
    public static final int MAX_NAME_LENGTH  = 30;
    public static final int NONE             = 0;

    /**
     * @param name is the agency's name. Its length must be between MIN_NAME_LENGTH and MAX_NAME_LENGTH characters.
     * @throws IllegalArgumentException if name's length is less than MIN_NAME_LENGTH and greater than
     *                                  MAX_NAME_LENGTH characters.
     */
    public Agency(final String name) throws IllegalArgumentException
    {
        if((name.length() < MIN_NAME_LENGTH) || (name.length() > MAX_NAME_LENGTH))
        {
            throw new IllegalArgumentException("Invalid name!");
        }

        this.name  = name;
        properties = new HashMap<>();
    }

    /**
     * This method adds the non-null property to the HashMap properties.
     *
     * @param property is the property.
     */
    public void addProperty(final Property property)
    {
        if(property != null)
        {
            properties.put(property.getPropertyId(), property);
        }
    }

    /**
     * @param propertyId is the property id.
     * @return the property from the HashMap properties whose ID matches with the parameter(property id).
     */
    public Property getProperty(final String propertyId)
    {
        if(propertyId != null &&
           properties.get(propertyId) != null &&
           properties.get(propertyId).getPropertyId().equals(propertyId))
        {
            return properties.get(propertyId);
        }
        else
        {
            return null;
        }
    }

    /**
     * This method removes the property from the HashMap properties whose ID matches(case insensitive) with the parameter(property id).
     *
     * @param propertyId is the property id.
     */
    public void removeProperty(final String propertyId)
    {
        if(properties.get(propertyId).getPropertyId().equalsIgnoreCase(propertyId))
        {
            properties.remove(propertyId);
        }
    }

    /**
     * @return the total amount in USD of all Properties.
     */
    public double getTotalPropertyValues()
    {
        double total = NONE;

        Set<String> keys;
        keys = properties.keySet();

        for(String key: keys)
        {
            total += properties.get(key).getPriceUsd();
        }

        return total;
    }

    /**
     * @return an ArrayList<Residence> type properties since only that type will now have swimming pools.
     */
    public ArrayList<Residence> getPropertiesWithPool()
    {
        ArrayList<Residence> propertiesWithPool;
        propertiesWithPool = new ArrayList<>();

        for(Property property: properties.values())
        {
            if((property instanceof Residence) && ((Residence) property).hasSwimmingPool())
            {
                Residence res = new Residence(property.getPriceUsd(), property.getAddress(), property.getType(),
                                              property.getPropertyId(), ((Residence) property).getNumberOfBedrooms(),
                                              ((Residence) property).hasSwimmingPool(), ((Residence) property).isStrata());

                propertiesWithPool.add(res);
            }
        }

        if(propertiesWithPool.size() > NONE)
        {
            return propertiesWithPool;
        }
        else // no property with pool
        {
            return null;
        }
   }

    /**
     * @param minUsd minimum price in USD.
     * @param maxUsd maximum price in USD.
     * @return an array of properties whose price falls in the range specified by the parameters, or null if there are none.
     */
    public Property[] getPropertiesBetween(final int minUsd, final int maxUsd)
    {
        ArrayList<Property> allMatches;
        allMatches = new ArrayList<>();

        Set<String> keys;
        keys = properties.keySet();

        for(String key: keys)
        {
            if((properties.get(key).getPriceUsd() >= minUsd) && (properties.get(key).getPriceUsd() <= maxUsd))
            {
                allMatches.add(properties.get(key));
            }
        }

        Property[] matches;
        matches = new Property[allMatches.size()];

        // Convert the ArrayList(allMatches) to an Array
        int i = 0;

        Iterator<Property> it;
        it = allMatches.iterator();

        while(it.hasNext())
        {
            matches[i] = (it.next());
            i++;
        }

        if(matches.length > NONE)
        {
            return matches;
        }
        else // no property found in this range of price
        {
            return null;
        }
    }

    /**
     * @param streetName is the street name.
     * @return an ArrayList of addresses which are on the specified street, or null if there are none.
     */
    public ArrayList<Address> getPropertiesOn(final String streetName)
    {
        ArrayList<Address> propertiesOn;
        propertiesOn = new ArrayList<>();

        for(Property property: properties.values())
        {
            if(property.getAddress().getStreetName().equalsIgnoreCase(streetName))
            {
                propertiesOn.add(property.getAddress());
            }
        }

        if(propertiesOn.size() > NONE)
        {
            return propertiesOn;
        }
        else // no address found on this specified street.
        {
            return null;
        }
    }

    /**
     * @param minBedrooms is the minimum bedroom number.
     * @param maxBedrooms is the maximum bedroom number.
     * @return a HashMap of properties (key is property id, value is the Residence) whose number of bedrooms falls in the
     *         range specified by the parameters, or null if there are none.
     */
    public HashMap<String, Residence> getPropertiesWithBedrooms(final int minBedrooms, final int maxBedrooms)
    {
        HashMap<String, Residence> expectedMatches;
        expectedMatches = new HashMap<>();

        for(Property property: properties.values())
        {
            if(property instanceof Residence)
            {
                if((((Residence) property).getNumberOfBedrooms() >= minBedrooms) &&
                   (((Residence) property).getNumberOfBedrooms() <= maxBedrooms))
                {
                    Residence res = new Residence(property.getPriceUsd(), property.getAddress(), property.getType(),
                                                  property.getPropertyId(), ((Residence) property).getNumberOfBedrooms(),
                                                  ((Residence) property).hasSwimmingPool(), ((Residence) property).isStrata());

                    expectedMatches.put(property.getPropertyId(), res);
                }
            }
        }

        if(expectedMatches.size() > NONE)
        {
            return expectedMatches;
        }
        else // no residence found on these range of bedrooms.
        {
            return null;
        }
    }

    /**
     * @param propertyType is the type of property.
     * @return return an ArrayList<Property> that hold the subtype specified in the parameter(case insensitive).
     */
    public ArrayList<Property> getPropertiesOfType(final String propertyType)
    {
        ArrayList<Property> propertiesOfType;
        propertiesOfType = new ArrayList<>();

        for(Property property: properties.values())
        {
            if(property.getType().equalsIgnoreCase(propertyType))
            {
                propertiesOfType.add(property);
            }
        }

        if(propertiesOfType.size() > NONE)
        {
            return propertiesOfType;
        }
        else
        {
            return null;
        }
    }

    /**
     * @return an ArrayList<Commercial> that holds only Commercial properties that have a loading dock available.
     */
    public ArrayList<Commercial> getPropertiesWithLoadingDock()
    {
        ArrayList<Commercial> propertiesWithLoadingDock;
        propertiesWithLoadingDock = new ArrayList<>();

        for(Property property: properties.values())
        {
            if(property instanceof Commercial)
            {
                if(((Commercial) property).isLoadingDock())
                {
                    Commercial com = new Commercial(property.getPriceUsd(), property.getAddress(), property.getType(),
                                                    property.getPropertyId(), ((Commercial) property).isLoadingDock(),
                                                    ((Commercial) property).isHighwayAccess());

                    propertiesWithLoadingDock.add(com);
                }
            }
        }

        return propertiesWithLoadingDock;
    }

    /**
     * @return an ArrayList<Commercial> that holds only Commercial properties that have highway access.
     */
    public ArrayList<Commercial> getPropertiesWithHighwayAccess()
    {
        ArrayList<Commercial> propertiesWithHighwayAccess;
        propertiesWithHighwayAccess = new ArrayList<>();

        for(Property property: properties.values())
        {
            if(property instanceof Commercial)
            {
                if(((Commercial) property).isHighwayAccess())
                {
                    Commercial com = new Commercial(property.getPriceUsd(), property.getAddress(), property.getType(),
                                                    property.getPropertyId(), ((Commercial) property).isLoadingDock(),
                                                    ((Commercial) property).isHighwayAccess());

                    propertiesWithHighwayAccess.add(com);
                }
            }
        }

        return propertiesWithHighwayAccess;
    }

    /**
     * @param squareFootage is the amount of floor space available.
     * @return an ArrayList<Retail> that holds properties where square footage is at least the parameter value.
     */
    public ArrayList<Retail> getPropertiesWithSquareFootage(final int squareFootage)
    {
        ArrayList<Retail> propertiesWithSquareFootage;
        propertiesWithSquareFootage = new ArrayList<>();

        for(Property property: properties.values())
        {
            if(property instanceof Retail)
            {
                if(((Retail) property).getSquareFootage() >= squareFootage)
                {
                    Retail ret = new Retail(property.getPriceUsd(), property.getAddress(), property.getType(),
                                            property.getPropertyId(), ((Retail) property).getSquareFootage(),
                                            ((Retail) property).hasCustomerParking());

                    propertiesWithSquareFootage.add(ret);
                }
            }
        }

        if(propertiesWithSquareFootage.size() > NONE)
        {
            return propertiesWithSquareFootage;
        }
        else // no such property with this minimum square foot
        {
            return null;
        }
    }
    /**
     * @return an ArrayList<Retail> that holds properties where customer parking is available.
     */
    public ArrayList<Retail> getPropertiesWithCustomerParking()
    {
        ArrayList<Retail> propertiesWithCustomerParking;
        propertiesWithCustomerParking = new ArrayList<>();

        for(Property property: properties.values())
        {
            if(property instanceof Retail)
            {
                if(((Retail) property).hasCustomerParking())
                {
                    Retail ret = new Retail(property.getPriceUsd(), property.getAddress(), property.getType(),
                                            property.getPropertyId(), ((Retail) property).getSquareFootage(),
                                            ((Retail) property).hasCustomerParking());

                    propertiesWithCustomerParking.add(ret);
                }
            }
        }

        return propertiesWithCustomerParking;
    }

    /**
     * @return ArrayList<Residence> that hold only the Residences that are in a strata.
     */
    public ArrayList<Residence> getPropertiesWithStrata()
    {
        ArrayList<Residence> propertiesWithStrata;
        propertiesWithStrata = new ArrayList<>();

        for(Property property: properties.values())
        {
            if(property instanceof Residence)
            {
                if(((Residence) property).isStrata())
                {
                    Residence res = new Residence(property.getPriceUsd(), property.getAddress(), property.getType(),
                                                  property.getPropertyId(), ((Residence) property).getNumberOfBedrooms(),
                                                  ((Residence) property).hasSwimmingPool(), ((Residence) property).isStrata());

                    propertiesWithStrata.add(res);
                }
            }
        }

        return propertiesWithStrata;
    }
}
