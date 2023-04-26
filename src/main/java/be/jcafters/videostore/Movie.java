package be.jcafters.videostore;

public abstract class Movie {

	private String title;

	protected Movie(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public abstract double determineAmount(int daysRented);

	public abstract int determineFrequentRenterPoints(int daysRented);
}
