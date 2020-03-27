/*
Zadatak 2
Potrebno je napraviti test za ispravnost klase Bank. Za sve testove potrebno je napraviti jedinstvenu 
banku koja se na poèetku testa resetuje. Smatrati da ova banka ne može da ima nalog kod kojeg je 
stanje negativno. Ako se na osnovu testova naðe neka greška u kodu potrebno jej da se popravi 
originalni kod.

a)	Potrebno je ispitati da li adekvatno radi otvaranje naloga, 
tako što æe se ubaciti 5 naloga i zatim ispitati da li se ti nalozi mogu dohvatiti.
b)	Proveriti da li dobro radi metoda za uplatu novca.
c)	Proveriti da li dobro radi metoda za transfer novca.
d)	Proveriti da li dobro radi metoda za raèunanje ukupnog novca u banci (getSumMoney).
*/
package bank_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import bank.Account;
import bank.Bank;

public class Bank_test {

	private static Bank bank = new Bank();
	private SoftAssert sa;

	// before each @test bank resets
	
	@BeforeMethod
	public void resetBank() {
		bank.reset();
		sa = new SoftAssert();
	}

	// setAmount method
	@Test 
	public void test01() {
		Account a1 = bank.openAccount("name1");
		a1.setAmount(-5000d);
		sa.assertEquals(a1.getAmount(), 0d);
		sa.assertAll();
	}

	// check if bank is reset before next test
	@Test 
	public void test02() {
		sa.assertEquals(bank.getAccounts().isEmpty(), true);
		sa.assertAll();
	}

	// payInMoney method ideal values (value greater than 0)
	@Test  
	public void test04() {
		Account a1 = bank.openAccount("name1");
		bank.payInMoney(a1.getNumber(), 1000d);
		sa.assertEquals(a1.getAmount(), 1000d);
		sa.assertAll();
	}

	// openAccount method
	@Test 
	public void test03() {
		Account[] accounts = new Account[5];
		String[] names = { "test", "name", "testing", "pera", "zika" };

		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = bank.openAccount(names[i]);
		}
		sa.assertEquals(bank.getAccount(accounts[2].getNumber()).getName(), "testing");
		sa.assertAll();
	}

	/*transferMoney method ideal values (from amount greather than to amountm 
	 transfer amount greather than 0 and smaller from from acc.amount)*/
	@Test 
	public void test05() {
		Account a1 = bank.openAccount("name1");
		a1.setAmount(5000d);
		Account a2 = bank.openAccount("name2");
		a2.setAmount(1000d);

		bank.transferMoney(a1.getNumber(), a2.getNumber(), 3000d);

		sa.assertEquals(a1.getAmount(), 2000d);
		sa.assertEquals(a2.getAmount(), 4000d);
		sa.assertAll();
	}

	//getSumMoney method
	@Test 
	public void test06() {
		Account a1 = bank.openAccount("name1");
		Account a2 = bank.openAccount("name2");
		Account a3 = bank.openAccount("name3");
		a1.setAmount(10000d);
		a2.setAmount(5000d);
		a3.setAmount(1000d);

		sa.assertEquals(bank.getSumMoney(), 16000d);
		sa.assertAll();
	}

	// closeAccount method
	@Test 
	public void test07() {
		Account a1 = bank.openAccount("test");
		bank.closeAccount(a1);
		Assert.assertEquals(bank.getAccount(a1.getNumber()), null);
	}

	/*transferMoney method - if acc.from has smaller
	 amount than amount that need to be transfered into acc.to method should return false */
	
	@Test 
	public void test08() {
		Account a1 = bank.openAccount("test1");
		a1.setAmount(100d);
		Account a2 = bank.openAccount("test2");
		a2.setAmount(500d);
		bank.transferMoney(a1.getNumber(), a2.getNumber(), 300d);

		sa.assertEquals(bank.transferMoney(a1.getNumber(), a2.getNumber(), 300d), false);

		sa.assertAll();
	}
	
	/*transferMoney method - if amount that need to be
	transfered is 0 or less return false*/
	
	@Test 
	public void test09() {
		Account a1 = bank.openAccount("test1");
		a1.setAmount(100d);
		Account a2 = bank.openAccount("test2");
		a2.setAmount(50d);

		sa.assertEquals(bank.transferMoney(a1.getNumber(), a2.getNumber(), -50d), false);
		sa.assertAll();
	}

	/*payInMoney method - if amount that need to be payed
	 in is 0 or less amount shouldn't be added to acc.amount*/
	
	@Test 
	public void test10() {
		Account a1 = bank.openAccount("testaccountname");
		bank.payInMoney(a1.getNumber(), -1000d);
		sa.assertEquals(a1.getAmount(), 0d);
		sa.assertAll();
	}

}
