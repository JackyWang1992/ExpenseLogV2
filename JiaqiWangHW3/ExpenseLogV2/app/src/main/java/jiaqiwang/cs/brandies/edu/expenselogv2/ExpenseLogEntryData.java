package jiaqiwang.cs.brandies.edu.expenselogv2;

/**
 * Created by yuchenrong on 11/19/17.
 */

public class ExpenseLogEntryData {
    private String id;
    private String itemDescription;
    private String itemNotes;
    private String itemTime;

    public String getItemId(){ return this.id; };

    public void setItemId(String id){ this.id = id;}

    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String description){
        this.itemDescription= description;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(String notes) {
        this.itemNotes = notes;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String time) {
        this.itemTime = time;
    }
}
