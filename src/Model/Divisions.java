package Model;

/**This class controls the divisions*/

public class Divisions {
    public Integer Division_ID;
    public String Division;
    public Integer Country_ID;

    /**THis method gets the division ID
     @return returns division ID*/

    public Integer getDivision_ID() {
        return Division_ID;
    }

    /**THis method gets the division name
     @return returns division name*/

    public String getDivision() {
        return Division;
    }

    /**THis method gets the Country ID
     @return returns Country ID*/

    public Integer getCountry_ID() {
        return Country_ID;
    }

    /**Constructor for Divisions
     @param division_ID division ID
     @param division name of division
     @param country_ID ID of country*/

    public Divisions(Integer division_ID, String division, Integer country_ID) {
        Division_ID = division_ID;
        Division = division;
        Country_ID = country_ID;
    }

    /**Overrides to string method for combo box population*/

    @Override
    public String toString() {
        return Division;
    }
}
