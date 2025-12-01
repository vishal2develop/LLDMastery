package AuctionSystem;

// Concrete AuctionSystem.Colleague
public class Bidder implements Colleague {
    String name;
    AuctionMediator auctionMediator;
    public Bidder(String name,AuctionMediator auctionMediator){
        this.name = name;
        this.auctionMediator = auctionMediator;
    }

    @Override
    public void placeBid(int bidAmount) {
        System.out.println(name + " placed a bid of " + bidAmount);
    }

    @Override
    public void receiveBidNotification(int bidAmount) {
        System.out.println("AuctionSystem.Bidder: "+name+" got a notification that someone placed a bid of "+bidAmount);
    }

    @Override
    public String getName() {
        return name;
    }
}
