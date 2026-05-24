public class TransactionFactory {

    public static ATMTransaction createTransaction(TransactionType type, int amount){
        return switch (type){
            case WITHDRAW -> new WithdrawCashTransaction(amount);
            case BALANCE_INQUIRY -> new BalanceInquiryTransaction();
        };
    }
}
