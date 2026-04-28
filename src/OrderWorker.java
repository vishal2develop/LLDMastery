public class OrderWorker implements Runnable{

    private FlashSaleQueue queue;
    private OrderService orderService;

    public OrderWorker(FlashSaleQueue queue, OrderService orderService) {
        this.queue = queue;
        this.orderService = orderService;
    }

    @Override
    public void run() {
        while(true){
            try {
                PurchaseRequest purchaseRequest = queue.take();
                Order order = orderService.placeOrder(purchaseRequest.getUser(), purchaseRequest.getProduct());

                System.out.println(order);

            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }

        }

    }
}
