import static java.util.Comparator.comparing;
import java.util.Comparator;
import java.util.Optional;
public class Exercise3 {

    public static void main(String[] args) {
        CountryDao countryDao = InMemoryWorldDao.getInstance();
        CityDao cityDao = InMemoryWorldDao.getInstance();

        Optional<City> highestPopulatedCapital = countryDao.findAllCountries().stream()
                .map(country -> cityDao.findCityById(country.getCapital()))
                .filter(city -> city != null)
                .max(Comparator.comparingInt(City::getPopulation));

        highestPopulatedCapital.ifPresent(city ->
                System.out.println("Highest Populated Capital City: " + city));
    }
}