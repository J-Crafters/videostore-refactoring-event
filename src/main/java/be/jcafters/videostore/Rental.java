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
		return switch (getMovie().getPriceCode()) {
			case Movie.REGULAR -> {
				double rentalAmount = 0;
				rentalAmount += 2;
				if (getDaysRented() > 2) {
					rentalAmount += (getDaysRented() - 2) * 1.5;
				}
				yield rentalAmount;
			}
			case Movie.NEW_RELEASE -> {
				double rentalAmount = 0;
				rentalAmount += getDaysRented() * 3;
				yield rentalAmount;
			}
			case Movie.CHILDRENS -> {
				double rentalAmount = 0;
				rentalAmount += 1.5;
				if (getDaysRented() > 3) {
					rentalAmount += (getDaysRented() - 3) * 1.5;
				}
				yield rentalAmount;
			}
			default -> throw new IllegalArgumentException();
		};
	}

	int determineFrequentRenterPoints() {
		if (bonusPointsEarned()) {
			return 2;
		}
		return 1;
	}

	private boolean bonusPointsEarned() {
		return getMovie().getPriceCode() == Movie.NEW_RELEASE && getDaysRented() > 1;
	}
}
