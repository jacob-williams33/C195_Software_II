package Model;

public class Countries {
    private Integer Country_ID;
    private String Country;

    public Countries(Integer country_ID, String country) {
        Country_ID = country_ID;
        Country = country;
    }

    public Integer getCountry_ID() {
        return Country_ID;
    }

    public String getCountry() {
        return Country;
    }
    @Override
    public String toString() {
        return Country;
    }

}
