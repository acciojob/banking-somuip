package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }

    }

    public String validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if (tradeLicenseId == null || tradeLicenseId.length() < 2) {
            throw new Exception("Valid License can not be generated");
        }

        char[] chars = tradeLicenseId.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                rearrange(chars, i);
            }
        }

        String validLicenseId = new String(chars);
        if (!isValid(validLicenseId)) {
            throw new Exception("Valid License can not be generated");
        }

        return validLicenseId;
    }

    private static void rearrange(char[] chars, int index) {
        char c = chars[index];
        if (index < chars.length - 1) {
            chars[index] = chars[index + 1];
            chars[index + 1] = c;
        } else {
            chars[index] = chars[index - 1];
            chars[index - 1] = c;
        }
    }

    private static boolean isValid(String licenseId) {
        for (int i = 1; i < licenseId.length(); i++) {
            if (licenseId.charAt(i) == licenseId.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

}
