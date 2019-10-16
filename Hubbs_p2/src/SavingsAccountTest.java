import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest
{

    @Test
    void instructionSpecifiedTest()
    {
        //sdhgtr
        SavingsAccount saver1 = new SavingsAccount(2000);
        SavingsAccount saver2 = new SavingsAccount(3000);
        SavingsAccount.modifyInterestRate(.04);
        // calculate 12 months of interest
        for (int i = 0; i < 12; i ++)
        {
            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
        }
        System.out.printf("Balance of saver1 is %.2f and Balance of saver2 is %.2f\n", saver1.getSavingsBalanceBalance(), saver2.getSavingsBalanceBalance());
        //change interest rate and get another month's balance
        SavingsAccount.modifyInterestRate(.05);
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.printf("Balance of saver1 is %.2f and Balance of saver2 is %.2f\n", saver1.getSavingsBalanceBalance(), saver2.getSavingsBalanceBalance());
    }

}