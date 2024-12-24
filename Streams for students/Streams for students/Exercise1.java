

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;





public class Exercise1 {
    public static void main(String[] args) {
        WorldDao worldDao = InMemoryWorldDao.getInstance();

        List<Country> countries = worldDao.findAllCountries();

        for (Country country : countries) {
            Optional<City> highestPopulatedCity = worldDao.findAllCities()
                    .stream()
                    .filter(city -> city.getCountryCode().equals(country.getCode()))
                    .max(Comparator.comparingInt(City::getPopulation));

            System.out.println("Country: " + country.getName() +
                    " -> Highest Populated City: " + highestPopulatedCity.orElse(null));
        }
    }
}