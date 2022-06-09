import java.util.*;

public class SushiOrderManager {

    int currentSeatId = 0;
    String menuItem;
    ArrayList<SushiOrder> request = new ArrayList<>();

    public static void main(String args[]) {
        ArrayList<SushiOrder> request = new ArrayList<>(
                Arrays.asList(
                        new SushiOrder(1, // seatId for this order
                                new ArrayList<>(Arrays.asList(
                                        new String[] {
                                                // menuItemIds for this order
                                                "tuna", "california", "avocado"
                                        }))),
                        new SushiOrder(3,
                                new ArrayList<>(Arrays.asList(
                                        new String[] { "avocado" }))),
                        new SushiOrder(2,
                                new ArrayList<>(Arrays.asList(
                                        new String[] {
                                                "salmon", "tuna", "crab", "scallop"
                                        })))));
        SushiOrderManager sushiOrderManager = new SushiOrderManager();
        sushiOrderManager.addOrder(request);
        sushiOrderManager.request = request;
        SushiItem sushiItem;

        for (int i = 0; i < 8; i++) {
            sushiItem = sushiOrderManager.nextItem();
            //++sushiOrderManager.currentSeatId;
            if (sushiItem != null)
                System.out.println(sushiItem.getSeatId() + " " + sushiItem.getMenuItemId());
        }
    }

    public SushiOrderManager() {
        /* initialize class state */
    }

    public void addOrder(ArrayList<SushiOrder> request) {
        for (SushiOrder s : request)
            this.request.add(s);

        this.request = request;
        // System.out.println("Size of the list is " + request.size());
        for (int i = 0; i < this.request.size() - 1; i++) {
            for (int j = 1; j < this.request.size(); j++) {
                if (this.request.get(i).getSeatId() > this.request.get(j).getSeatId()) {
                    // System.out.println("Seat Id of " + this.request.get(i).seatId + " record is
                    // swapping with seat id " + this.request.get(j).seatId);
                    SushiOrder temp = this.request.get(i);
                    // request.remove(i);
                    this.request.set(i, this.request.get(j));
                    this.request.set(j, temp);
                    // System.out.println(i + " and " + j + " are swapped");
                }
            }
        }

        // for (SushiOrder s : request)
        // System.out.println(s.seatId +" " + s.menuItemIds);
    }

    public SushiItem nextItem() {
        // System.out.println("Current Seat Id : " + currentSeatId);
        // for (int i = 0; i < request.size(); i++) {
            ++currentSeatId;
            if (currentSeatId > this.request.size()) 
                currentSeatId = 1;
        System.out.println(
                "Seat id is " + this.request.get(currentSeatId-1).getSeatId() + " current seat id is " + (currentSeatId ));
        // if (this.request.get(i).getSeatId() == currentSeatId) {
        // System.out.println("Reference of MenuItemsIds" +
        // this.request.get(i).getMenuItemIds().size());
        if (this.request.get(currentSeatId-1).getMenuItemIds().size() > 0) {
            menuItem = this.request.get(currentSeatId-1).getMenuItemIds().get(0);
            // System.out.println(this.request.get(i).getSeatId() + " " + menuItem);
            this.request.get(currentSeatId-1).getMenuItemIds().remove(0);
            int seatId = this.request.get(currentSeatId-1).getSeatId();
            if(this.request.get(currentSeatId-1).getMenuItemIds().size()==0){
                this.request.remove(currentSeatId-1);
            }
            // System.out.println("Item left");
            // for (SushiOrder s : request)
            // System.out.print(s.seatId +" " + s.menuItemIds + "\t");

            // System.out.println();

                return new SushiItem(seatId, menuItem);
            }
            
        
        // }
        // }
        return null;
    }
}

class SushiItem {
    int seatId;
    String menuItemId;

    public SushiItem(int seatId, String menuItemId) {
        this.seatId = seatId;
        this.menuItemId = menuItemId;
    }

    public int getSeatId() {
        return seatId;
    }

    public String getMenuItemId() {
        return menuItemId;
    }
    // public String toString();
    // public boolean equals(Object o);
}

class SushiOrder {
    int seatId;
    ArrayList<String> menuItemIds;

    public SushiOrder(int seatId, ArrayList<String> menuItemIds) {
        this.seatId = seatId;
        this.menuItemIds = menuItemIds;
    }

    public int getSeatId() {
        return seatId;
    }

    public ArrayList<String> getMenuItemIds() {
        return menuItemIds;
    }
}
