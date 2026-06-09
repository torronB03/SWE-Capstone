package utils;

import java.util.HashMap;

// class that allows us to add any parameters to a filtered search.
// for example, if we wanted to filter by price range on Clothing Stores, we can add these to the DAO's query through this class
// an example usecase would be: 

/*

    private LocationService locationService = new LocationService();
    QueryFilter userFilter = new QueryFilter();

    userFilter.addToFilter("price_range", "mixed")
    locationService.GetFilteredMembers("ClothingStores", userFilter)

*/

/**
 * QueryFilter is a utility class that allows for the creation of dynamic filters for querying data.
 * It uses a HashMap to store key-value pairs representing filter criteria.
 */
public class QueryFilter {

    private HashMap<String, Object> FilterBody = new HashMap<>();

    // get every parameter of the filter
    /**
     * 
     * @return a HashMap containing all filter parameters
     */
    public HashMap<String, Object> getFilter() { 
        return FilterBody;
    }

    // get value of a specified key
    /**
     * @param key the key for which the value is to be retrieved
     * @return the value associated with the specified key, or null if the key does not exist   
     */
    public Object getFilterParameter(String key) {
        return FilterBody.get(key);
    }

    // add key-value pair to filter
    /**
     * @param key the key to be added to the filter
     * @param value the value associated with the key
     */
    public void addToFilter(String key, Object value) {
        FilterBody.put(key, value);
    }
}
