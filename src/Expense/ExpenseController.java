package Expense;

import Balance.BalanceSheetController;
import Split.Enums.SplitType;
import Split.Factory.SplitFactory;
import Split.Interfaces.ExpenseSplit;
import Split.Split;
import User.User;

import java.util.List;

public class ExpenseController {
    BalanceSheetController balanceSheetController;

    public ExpenseController(){
        this.balanceSheetController = new BalanceSheetController();
    }

    public Expense createExpense(String expenseId, String description, double expenseAmount,
                                 List<Split> splitDetails, SplitType splitType, User paidByUser){
        ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
        expenseSplit.validateSplitRequest(splitDetails,expenseAmount);

        Expense expense = new Expense(expenseId,expenseAmount,description,paidByUser,splitType,splitDetails);
        // Update User's Expense Balance Sheet
        balanceSheetController.updateUserExpenseBalanceSheet(paidByUser,splitDetails,expenseAmount);

        return expense;
    }
}
