package Model;

public class Divisions {
    public Integer Division_ID;
    public String Division;
    public Integer Country_ID;

    public Integer getDivision_ID() {
        return Division_ID;
    }

    public String getDivision() {
        return Division;
    }

    public Integer getCountry_ID() {
        return Country_ID;
    }

    public Divisions(Integer division_ID, String division, Integer country_ID) {
        Division_ID = division_ID;
        Division = division;
        Country_ID = country_ID;
    }
    @Override
    public String toString() {
        return Division;
    }
}
