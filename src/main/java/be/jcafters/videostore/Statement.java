package be.jcafters.videostore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Statement {

	private String customerName;
	private List<Rental> rentals = new ArrayList<>();
	private double totalAmount;
	private int frequentRenterPoints;

	public Statement(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getTotalAmount() {
		return BigDecimal.valueOf(totalAmount);
	}

	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String generate() {
		initialise();

		return header() +
			   rentalLines() +
			   footer();
	}

	private void initialise() {
		totalAmount = 0;
		frequentRenterPoints = 0;
	}

	private String header() {
		return "Rental Record for %s\n".formatted(getCustomerName());
	}

	private String rentalLines() {
		StringBuilder rentalLines = new StringBuilder();

		for (Rental rental : rentals) {
			rentalLines.append(rentalLine(rental));
		}

		return rentalLines.toString();
	}

	private String rentalLine(Rental rental) {
		double rentalAmount = determineAmount(rental);
		frequentRenterPoints += determineFrequentRenterPoints(rental);
		totalAmount += rentalAmount;

		return formatRentalLine(rental, rentalAmount);
	}

	private String formatRentalLine(Rental rental, double rentalAmount) {
		return String.format("\t%s\t%.1f\n", rental.getTitle(), rentalAmount);
	}

	private double determineAmount(Rental rental) {
		double rentalAmount = 0;

		switch (rental.getMovie().getPriceCode()) {
		case Movie.REGULAR -> {
			rentalAmount += 2;
			if (rental.getDaysRented() > 2) {
				rentalAmount += (rental.getDaysRented() - 2) * 1.5;
			}
		}
		case Movie.NEW_RELEASE -> rentalAmount += rental.getDaysRented() * 3;
		case Movie.CHILDRENS -> {
			rentalAmount += 1.5;
			if (rental.getDaysRented() > 3) {
				rentalAmount += (rental.getDaysRented() - 3) * 1.5;
			}
		}
		default -> throw new IllegalArgumentException();
		}
		return rentalAmount;
	}

	private int determineFrequentRenterPoints(Rental rental) {
		if (bonusPointsEarned(rental)) {
			return 2;
		}
		return 1;
	}

	private boolean bonusPointsEarned(Rental rental) {
		return rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1;
	}

	private String footer() {
		return "You owed " + totalAmount + "\n" +
			   "You earned " + frequentRenterPoints + " frequent renter points\n";
	}
}
