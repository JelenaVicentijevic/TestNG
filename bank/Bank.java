package bank;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<Account> accounts;

	public Bank() {
		accounts = new ArrayList<>();
	}

	public Account openAccount(String name) {
		Account account = new Account(name);
		accounts.add(account);
		return account;
	}

	public void closeAccount(Account account) {
		accounts.remove(account);
	}

	public boolean transferMoney(String accountNumberFrom, String accountNumberTo, double amount) {
		Account from = getAccount(accountNumberFrom);
		if (amount <= from.getAmount() && amount > 0) {
			if (from != null)
				from.setAmount(from.getAmount() - amount);
			Account to = getAccount(accountNumberTo);
			if (to != null)
				to.setAmount(to.getAmount() + amount);
			return true;
		}
		return false;
	}

	public void payInMoney(String accountNumber, double amount) {
		Account account = getAccount(accountNumber);
		if (account != null)
			account.setAmount(account.getAmount() + amount);
	}

	public Account getAccount(String accountNumber) {
		for (Account acc : accounts)
			if (acc.getNumber() == accountNumber)
				return acc;

		return null;
	}

	// added method to test reset method
	public List<Account> getAccounts() {
		return accounts;
	}

	public double getSumMoney() {
		double res = 0d;
		for (int i = 0; i < accounts.size(); i++) {
			res += accounts.get(i).getAmount();
		}
		return res;
	}

	public void reset() {
		accounts.clear();
	}

}
