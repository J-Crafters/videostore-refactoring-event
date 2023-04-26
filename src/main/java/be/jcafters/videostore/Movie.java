package be.jcafters.videostore;

public abstract class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private String title;
	private int priceCode;

	protected Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public String getTitle() {
		return title;
	}

	public abstract double determineAmount(int daysRented);

	public abstract int determineFrequentRenterPoints(int daysRented);
}
