/*
Zadatak 1
Potrebno je napraviti test za ispravnost klase Account. Ako se na osnovu testova pronaðe neka greška 
u kodu potrebno je da se popravi originalnin kod.
a)	Proveriti da li konstruktor public Account(String name, String number) dobro postavlja argumente,
 kao i da li get metode vraæaju odgovarajuæe vrednosti. Kada se napravi raèun, 
 stanje treba da bude jednako 0.
b)	Proveriti da li set metode dobro postavljaju odgovarajuæe vrednosti, 
kao i da li get metode vraæaju odgovarajuæe vrednosti.
c)	Proveriti da li je ispravan format ispisa jednog Account-a. 
Oèekivani format ispisa: broj<tab>ime<tab>stanje. Stanje treba da bude zaokruženo na dve decimale.
d)	Napraviti 100 000 naloga pomoæu konstruktora public Account(String name) i 
proveriti da li se svi brojevi razlikuju.
*/
package bank_test;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import bank.Account;

public class Account_test {

	// Account constructor
	@Test 
	public void test1() {
		Account a1 = new Account("name1", "12345");
		
		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(a1.getNumber(), "12345");
		sa.assertEquals(a1.getName(), "name1");
		sa.assertEquals(a1.getAmount(), 0d); // error in constructor or getAmount method
		sa.assertAll();
	}

	// setAmount ideal amount value greater than 0
	@Test 
	public void test2() {
		Account a1 = new Account("name1", "12345");
		a1.setAmount(10000d);
		Assert.assertEquals(a1.getAmount(), 10000d);
	}

	// toString method
	@Test 
	public void test3() {
		Account a1 = new Account("name1", "12345");
		a1.setAmount(200.5389);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(a1.toString(), "12345	name1	200,54");
		sa.assertAll();
	}

	// open 100000 accounts, check if their acc.numbers are unique
	@Test 
	public void test4() {
		Account[] accounts = new Account[100000];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = new Account("test");
		}
		for (int i = 0; i < accounts.length; i++) {
			for (int j = i + 1; j < accounts.length; j++) {
				Assert.assertNotEquals(accounts[i].getNumber(), accounts[j].getNumber()); 
			}
		}

	}

}
