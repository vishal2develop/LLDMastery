package AuctionSystem;

public class Main {
    public static void main(String[] args) {
        AuctionMediator aucttionMediator = new Auction();
        Colleague bidder1 = new Bidder("Vishal",aucttionMediator);
        Colleague bidder2 = new Bidder("Rahul",aucttionMediator);

        aucttionMediator.addBidder(bidder1);
        aucttionMediator.addBidder(bidder2);

        bidder1.placeBid(100);
        bidder2.placeBid(200);
        bidder1.placeBid(300);

    }
}