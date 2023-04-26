package be.jcrafters.videostore;

import be.jcafters.videostore.Movie;

public class NewReleaseMovie extends Movie {

	public NewReleaseMovie(String title) {
		super(title, NEW_RELEASE);
	}

	@Override
	public double determineAmount(int daysRented) {
		return daysRented * 3;
	}

	@Override
	public int determineFrequentRenterPoints(int daysRented) {
		boolean bonusIsEarned = getPriceCode() == NEW_RELEASE && daysRented > 1;

		if (bonusIsEarned) {
			return 2;
		}
		return 1;
	}
}
