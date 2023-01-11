package banking;

import java.util.Arrays;
import java.util.Random;

public class Account {
    private String accountNumber;
    private String pinNumber;

    public Account() {
        Random random = new Random();
        luhnAlgorithmAccountGen();
        this.pinNumber = String.valueOf(random.nextInt(9000) + 1000);
        CardQuery.insertIntoCard(this.accountNumber, this.pinNumber);
    }
    public Account(String tempAccountNum, String tempPin) {
        this.accountNumber = tempAccountNum;
        this.pinNumber = tempPin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    private void luhnAlgorithmAccountGen(){
        Random random = new Random();
        long randomNum = random.nextLong(9_000_000_000L) + 1_000_000_000L;
        String temporaryAccount = "400000" + randomNum; // Generate a 16 digit account number
        temporaryAccount = temporaryAccount.substring(0,temporaryAccount.length() - 1); // Convert the account to 15 digits to create a checksum digit

        int[] findCheckSum = new int[16];

        for (int i = 0; i < temporaryAccount.length(); i++){
            if((i + 1) % 2 != 0) {
                String getNum = temporaryAccount.substring(i,i+1);
                findCheckSum[i] = Integer.valueOf(getNum) * 2;
            } else {
                findCheckSum[i] = Integer.valueOf(temporaryAccount.substring(i,i+1));
            }
        }
        int sum = 0;
        for (int i = 0; i < findCheckSum.length - 1; i++) {
            if (findCheckSum[i] > 9) {
                findCheckSum[i] -= 9;
            }
            sum += findCheckSum[i];
        }
        for (int i = 0; i <= 9; i++) {
            if ((sum + i) % 10 == 0) {
                findCheckSum[15] = i;
                break;
            }
        }
        temporaryAccount = temporaryAccount + findCheckSum[15];

        this.accountNumber = temporaryAccount;

    }
}
