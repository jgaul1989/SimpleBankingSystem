package banking;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountServices {
    private Scanner scanner;
    private Account accountToService;

    public AccountServices(Scanner scanner) {
        this.scanner = scanner;
        this.accountToService = new Account("0","0");
    }

    public void mainMenu() {

        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
            int menuOption = Integer.parseInt(this.scanner.nextLine());

            switch (menuOption) {
                case 1 -> createAccount();
                case 2 -> {
                    boolean validAccount = loginToAccount();

                    if (validAccount) {
                        System.out.println("You have successfully logged in!");
                        continueProgram = serviceValidAccount();

                    } else {
                        System.out.println("Wrong card number or PIN!");
                    }
                }
                case 0 -> {
                    System.out.println("Bye!");
                    continueProgram = false;
                }
                default -> System.out.println("Invalid input!");
            }

        }
    }
    private void createAccount() {
        Account newAccount = new Account();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(newAccount.getAccountNumber());
        System.out.println("Your card PIN:");
        System.out.println(newAccount.getPinNumber());

    }
    private boolean loginToAccount() {

        System.out.println("Enter your card number:");
        String accountNumber = this.scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = this.scanner.nextLine();
        this.accountToService.setAccountNumber(accountNumber);
        this.accountToService.setPinNumber(pin);

        return CardQuery.checkForAccount(accountNumber,pin);
    }

    private boolean serviceValidAccount() {

        while (true) {
            System.out.println("1. Balance");
            System.out.println("2. Add income");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");

            int userInput = Integer.parseInt(this.scanner.nextLine());

            if(userInput == 1) {
                System.out.print("Balance: ");
                System.out.println(CardQuery.getAccountBalance(this.accountToService.getAccountNumber(),this.accountToService.getPinNumber()));
            } else if (userInput == 2) {
                System.out.println("Enter income:");
                int income = Integer.parseInt(this.scanner.nextLine());
                CardQuery.addToAccountBalance(this.accountToService.getAccountNumber(), income);
                System.out.println("Income was added!");
            } else if (userInput == 3) {
                transferFunds();
            } else if (userInput == 4) {
                CardQuery.deleteAccount(this.accountToService.getAccountNumber());
                System.out.println("The account has been closed!");
                return true;
            } else if (userInput == 5) {
                System.out.println("You have successfully logged out");
                return true;
            } else if (userInput == 0) {
                System.out.println("Bye!");
                return false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
    private void transferFunds() {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        String cardNumber = this.scanner.nextLine();

        if(this.accountToService.getAccountNumber().equals(cardNumber)) {
            System.out.println("You cant transfer money to the same account!");
            return;
        }
        if(cardNumber.length() != 16) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }
        boolean validCardNum = checkValidCardNum(cardNumber);
        if (!validCardNum) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }
        validCardNum = CardQuery.accountLookup(cardNumber);
        if (!validCardNum) {
            System.out.println("Such a card does not exist.");
            return;
        }
        System.out.println("Enter how much money you want to transfer:");
        int moneyToTransfer = Integer.valueOf(this.scanner.nextLine());

        if (moneyToTransfer > CardQuery.getAccountBalance(this.accountToService.getAccountNumber(), this.accountToService.getPinNumber())) {
            System.out.println("Not enough money!");
        } else {
            CardQuery.deductAccountBalance(this.accountToService.getAccountNumber(), moneyToTransfer);
            CardQuery.addToAccountBalance(cardNumber, moneyToTransfer);
            System.out.println("Success!");
        }
    }
    private boolean checkValidCardNum(String cardNumber) {

        int checkSumDigit = Integer.valueOf(cardNumber.substring(15));
        String first15Digits = cardNumber.substring(0, 15);
        int[] checkCardVal = new int[16];

        for (int i = 0; i < first15Digits.length(); i++) {
            if ((i + 1) % 2 != 0) {
                String getNum = first15Digits.substring(i, i + 1);
                checkCardVal[i] = Integer.valueOf(getNum) * 2;
            } else {
                checkCardVal[i] = Integer.valueOf(first15Digits.substring(i, i + 1));
            }
        }
        int sum = 0;
        for (int i = 0; i < checkCardVal.length - 1; i++) {
            if (checkCardVal[i] > 9) {
                checkCardVal[i] -= 9;
            }
            sum += checkCardVal[i];
        }
        return ((sum + checkSumDigit) % 10 == 0);
    }
}
