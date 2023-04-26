package be.jcafters.videostore;

public class Rental {

	private Movie movie;
	private int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	public String getTitle() {
		return movie.getTitle();
	}

	double determineAmount() {
		return movie.determineAmount(daysRented);
	}

	int determineFrequentRenterPoints() {
		if (bonusPointsEarned()) {
			return 2;
		}
		return 1;
	}

	private boolean bonusPointsEarned() {
		return getMovie().getPriceCode() == Movie.NEW_RELEASE && daysRented > 1;
	}
}
