package Balance;

import Split.Split;
import User.User;

import java.util.List;
import java.util.Map;

public class BalanceSheetController {
    /**
     * Updates the balance sheets of all users involved in an expense.
     * This method is called whenever a new expense is created or modified.
     *
     * @param expensePaidBy The user who paid for the expense
     * @param splits List of splits indicating how the expense is divided among users
     * @param totalExpenseAmount The total amount of the expense
     */
    public void updateUserExpenseBalanceSheet(User expensePaidBy, List<Split> splits, double totalExpenseAmount){
        // Get the balance sheet of the user who paid to update it
        UserExpenseBalanceSheet paidByUserExpenseSheet = expensePaidBy.getUserExpenseBalanceSheet();

        // update the total amount paid by the user - the one who paid the whole expense
        paidByUserExpenseSheet.setTotalPayment(paidByUserExpenseSheet.getTotalPayment()+totalExpenseAmount);

        // Iterate through each split to update individual balances
        for (Split split: splits){
            // Get the individual user who owes money and their balanceSheet
            User userOwe = split.getUser();
            UserExpenseBalanceSheet oweUserExpenseSheet = userOwe.getUserExpenseBalanceSheet();

            // get Amount Owed
            double amountOwed = split.getAmountOwe();

            // If the user who owes is same as the user who paid for the whole expense
            if(expensePaidBy.getUserId().equals(userOwe.getUserId())){
                // update their own expense - (they paid for themselves)
                // eg: 3 Users, expense = 90, paid by U1. Splits = U1-30, U2-30, U3-30
                paidByUserExpenseSheet.setTotalYourExpense(paidByUserExpenseSheet.getTotalYourExpense()+amountOwed);

            }
            else{
                // Update the Balance Sheet of the user who paid
                // They should get back the amount from the user who owes
                paidByUserExpenseSheet.setTotalYouGetBack(paidByUserExpenseSheet.getTotalYouGetBack()+amountOwed);

                // Get or create the balance object between the payer and the user who owes
                Balance userOweBalance;
                // if the user who owes is an existing users in the balance sheet of the payer - update the balance
                // else if New User in the balance sheet - Create balance
                if(paidByUserExpenseSheet.getUserVsBalance().containsKey(userOwe.getUserId())){
                    // get the owe users balance object
                    userOweBalance = paidByUserExpenseSheet.getUserVsBalance().get(userOwe.getUserId());
                }
                else{
                    // create new balance for new user
                    userOweBalance = new Balance();
                    // add the new balance entry into the balance sheet
                    paidByUserExpenseSheet.getUserVsBalance().put(userOwe.getUserId(),userOweBalance);
                }

                //TODO: Update the amount the payer should get back from this user
                userOweBalance.setAmountGetBack(userOweBalance.getAmountGetBack()+amountOwed);

                // Update the balance sheet of the user who owes
                // They owe the amount, and it's part of their expenses
                oweUserExpenseSheet.setTotalYouOwe(oweUserExpenseSheet.getTotalYouOwe()+amountOwed);

                oweUserExpenseSheet.setTotalYourExpense(oweUserExpenseSheet.getTotalYourExpense()+amountOwed);

                // Get or create the balance object between the user who owes and the payer
                Balance userPaidBalance;
                if(oweUserExpenseSheet.getUserVsBalance().containsKey(expensePaidBy.getUserId())){
                    // If balance already exists, get it
                    userPaidBalance = oweUserExpenseSheet.getUserVsBalance().get(expensePaidBy.getUserId());
                }
                else{
                    userPaidBalance = new Balance();

                    oweUserExpenseSheet.getUserVsBalance().put(expensePaidBy.getUserId(),userPaidBalance);
                }

                userPaidBalance.setAmountOwe(userPaidBalance.getAmountOwe()+amountOwed);
            }
        }


    }

    /**
     * Displays the balance sheet of a specific user.
     * Shows all their expenses, payments, and balances with other users.
     *
     * @param user The user whose balance sheet needs to be displayed
     */
    public void showBalanceSheetOfUser(User user) {
        // Print separator for better readability
        System.out.println("---------------------------------------");

        // Print user ID
        System.out.println("Balance sheet of user : " + user.getUserId());

        // Get the user's balance sheet
        UserExpenseBalanceSheet userExpenseBalanceSheet = user.getUserExpenseBalanceSheet();

        // Print summary of the user's financial status
        System.out.println("TotalYourExpense: " + userExpenseBalanceSheet.getTotalYourExpense());
        System.out.println("TotalGetBack: " + userExpenseBalanceSheet.getTotalYouGetBack());
        System.out.println("TotalYourOwe: " + userExpenseBalanceSheet.getTotalYouOwe());
        System.out.println("TotalPaymentMade: " + userExpenseBalanceSheet.getTotalPayment());

        // Print detailed balances with each user
        for(Map.Entry<String, Balance> entry : userExpenseBalanceSheet.getUserVsBalance().entrySet()) {
            String userID = entry.getKey();
            Balance balance = entry.getValue();

            System.out.println("userID:" + userID +
                    " YouGetBack:" + balance.getAmountGetBack() +
                    " YouOwe:" + balance.getAmountOwe());
        }

        // Print separator for better readability
        System.out.println("---------------------------------------");
    }
}
