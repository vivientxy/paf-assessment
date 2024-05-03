package ibf2024.assessment.paf.batch4.repositories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;

@Repository
public class BeerRepository {

	@Autowired
    JdbcTemplate template;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// Task 2
		final String SQL_GET_STYLES = """
			SELECT styles.id, style_name, COUNT(beers.name) AS beer_count
			FROM styles
			JOIN beers
			ON styles.id = beers.style_id
			GROUP BY styles.id, style_name
			ORDER BY beer_count DESC, style_name ASC;
			""";
		SqlRowSet rs = template.queryForRowSet(SQL_GET_STYLES);
		List<Style> styles = new LinkedList<>();
		while (rs.next()) {
			Style style = new Style();
			style.setStyleId(rs.getInt("id"));
			style.setName(rs.getString("style_name"));
			style.setBeerCount(rs.getInt("beer_count"));
			styles.add(style);
		}
		return Collections.unmodifiableList(styles);
	}

		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(Integer style_id) {
		// Task 3
		final String SQL_GET_BREWERIES_BY_BEER = """
			SELECT beers.id AS beer_id, 
				beers.name AS beer_name, 
				beers.descript AS beer_description, 
				breweries.id AS brewery_id, 
				breweries.name AS brewery_name
			FROM beers
			JOIN breweries
			ON beers.brewery_id = breweries.id
			WHERE beers.style_id = ?
			ORDER BY beer_name ASC;
			""";
		SqlRowSet rs = template.queryForRowSet(SQL_GET_BREWERIES_BY_BEER, style_id);
		List<Beer> beers = new LinkedList<>();
		while (rs.next()) {
			Beer beer = new Beer();
			beer.setBeerId(rs.getInt("beer_id"));
			beer.setBeerName(rs.getString("beer_name"));
			beer.setBeerDescription(rs.getString("beer_description"));
			beer.setBreweryId(rs.getInt("brewery_id"));
			beer.setBreweryName(rs.getString("brewery_name"));
			beers.add(beer);
		}
		return Collections.unmodifiableList(beers);
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(Integer brewery_id) {
		// Task 4
		final String SQL_GET_BEERS_FROM_BREWERY = """
			SELECT breweries.id AS brewery_id, 
				breweries.name AS brewery_name, 
				breweries.address1, 
				breweries.address2, 
				breweries.city,
				breweries.phone,
				breweries.website,
				breweries.descript AS description,
				beers.id AS beer_id,
				beers.name AS beer_name,
				beers.descript AS beer_description
			FROM breweries
			JOIN beers
			ON beers.brewery_id = breweries.id
			WHERE brewery_id = ?
			ORDER BY beer_name ASC;
			""";
		SqlRowSet rs = template.queryForRowSet(SQL_GET_BEERS_FROM_BREWERY, brewery_id);

		try {
			Brewery brewery = new Brewery();
			while (rs.next()) {
				Beer beer = new Beer();
				beer.setBeerId(rs.getInt("beer_id"));
				beer.setBeerName(rs.getString("beer_name"));
				beer.setBeerDescription(rs.getString("beer_description"));
				beer.setBreweryId(rs.getInt("brewery_id"));
				beer.setBreweryName(rs.getString("brewery_name"));
				brewery.addBeer(beer);
			}
			
			rs.first();
			brewery.setBreweryId(rs.getInt("brewery_id"));
			brewery.setName(rs.getString("brewery_name"));
			brewery.setAddress1(rs.getString("address1"));
			brewery.setAddress2(rs.getString("address2"));
			brewery.setCity(rs.getString("city"));
			brewery.setPhone(rs.getString("phone"));
			brewery.setWebsite(rs.getString("website"));
			brewery.setDescription(rs.getString("description"));
	
			return Optional.ofNullable(brewery);

		} catch (ArrayIndexOutOfBoundsException e) {
			return Optional.empty();
		}

	}
}
