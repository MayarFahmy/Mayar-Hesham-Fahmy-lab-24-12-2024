

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;



public class Exercise2 {

    public static void main(String[] args) {
        CountryDao countryDao = InMemoryWorldDao.getInstance();

        // Find the most populated city of each continent
        Map<String, Optional<City>> mostPopulatedCityByContinent = countryDao.findAllCountries().stream()
                .collect(Collectors.groupingBy(
                        Country::getContinent,
                        Collectors.flatMapping(
                                country -> country.getCities().stream(),
                                Collectors.maxBy(Comparator.comparingInt(City::getPopulation))
                        )
                ));

        mostPopulatedCityByContinent.forEach((continent, city) -> {
            System.out.println("Continent: " + continent + ", Most Populated City: " + city.orElse(null));
        });
    }
}