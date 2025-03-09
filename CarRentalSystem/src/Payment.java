public class Payment {
    public void payBill(Bill bill){
        // Payment processing logic& update bill status
        System.out.println("bill payed:"+bill.totalBillAmount);
        bill.isBillPaid = true;
    }
}
