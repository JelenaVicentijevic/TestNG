package bank;

import java.util.Random;
import java.util.UUID;

public class Account {

	private String number;
	private String name;
	private double amount;

	public Account(String name, String number) {
		this.number = number;
		this.name = name;
		this.amount = 0d;
	}

	public Account(String name) {
		this(name, UUID.randomUUID().toString());
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		if (amount > 0)
			this.amount = amount;
	}

	@Override
	public String toString() {
		return String.format("%s\t%s\t%.2f", number, name, amount);
	}
}
