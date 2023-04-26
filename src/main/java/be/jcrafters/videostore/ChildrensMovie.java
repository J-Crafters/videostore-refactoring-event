package be.jcrafters.videostore;

import be.jcafters.videostore.Movie;

public class ChildrensMovie extends Movie {
	public ChildrensMovie(String title) {
		super(title, CHILDRENS);
	}

	@Override
	public double determineAmount(int daysRented) {
		double rentalAmount = 0;

		rentalAmount += 1.5;
		if (daysRented > 3) {
			rentalAmount += (daysRented - 3) * 1.5;
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
