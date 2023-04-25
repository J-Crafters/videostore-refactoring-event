package be.jcafters.videostore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String name;
	private List<Rental> rentals = new ArrayList<>();
	private double totalAmount;
	private int frequentRenterPoints;

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public BigDecimal getTotalAmount() {
		return BigDecimal.valueOf(totalAmount);
	}

	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}

	public String getName() {
		return name;
	}

	public String statement() {
		totalAmount = 0;
		frequentRenterPoints = 0;

		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

		for (Rental rental : rentals) {
			double thisAmount = 0;

			// determines the amount for each line
			switch (rental.getMovie().getPriceCode()) {
			case Movie.REGULAR -> {
				thisAmount += 2;
				if (rental.getDaysRented() > 2) {
					thisAmount += (rental.getDaysRented() - 2) * 1.5;
				}
			}
			case Movie.NEW_RELEASE -> thisAmount += rental.getDaysRented() * 3;
			case Movie.CHILDRENS -> {
				thisAmount += 1.5;
				if (rental.getDaysRented() > 3) {
					thisAmount += (rental.getDaysRented() - 3) * 1.5;
				}
			}
			default -> throw new IllegalArgumentException();
			}

			frequentRenterPoints++;

			if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
				frequentRenterPoints++;
			}

			result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;

		}

		result.append("You owed ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

		return result.toString();
	}
}
