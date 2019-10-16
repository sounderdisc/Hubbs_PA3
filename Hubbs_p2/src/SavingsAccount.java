public class SavingsAccount
{
    private static double annualInterestRate;
    private double  savingsBalance;

    public SavingsAccount(double initialBalance)
    {
        savingsBalance = initialBalance;
    }

    public void calculateMonthlyInterest()
    {
        savingsBalance += (savingsBalance * annualInterestRate) / 12;
    }

    public static void  modifyInterestRate(double newRate)
    {
        annualInterestRate = newRate;
    }
    public double getSavingsBalanceBalance()
    {
        return savingsBalance;
    }
}
