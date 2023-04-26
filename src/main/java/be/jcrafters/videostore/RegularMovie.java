package be.jcrafters.videostore;

import be.jcafters.videostore.Movie;

public class RegularMovie extends Movie {
	public RegularMovie(String title) {
		super(title, REGULAR);
	}

	@Override
	public double determineAmount(int daysRented) {
		double rentalAmount = 2;
		if (daysRented > 2) {
			rentalAmount += (daysRented - 2) * 1.5;
		}
		return rentalAmount;
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
