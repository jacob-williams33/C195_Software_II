package Model;

/**This class controls countries*/

public class Countries {
    private Integer Country_ID;
    private String Country;

    /**Constructor for country class
     @param country country name
     @param country_ID ID for country*/

    public Countries(Integer country_ID, String country) {
        Country_ID = country_ID;
        Country = country;
    }

    /**this method gets the ID of the country
     @return returns the ID of the country*/

    public Integer getCountry_ID() {
        return Country_ID;
    }

    /**this method gets the name of the country
     @return returns the name of the country*/

    public String getCountry() {
        return Country;
    }

    /**This method overrides to string method to populate combo box names*/

    @Override
    public String toString() {
        return Country;
    }

}
