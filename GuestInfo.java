
public class GuestInfo {
    private String guest_name;
    private int guest_id;

    public GuestInfo(String guest_name, int guest_id) {
        this.guest_name = guest_name;
        this.guest_id = guest_id;
    }
    public int getId() {
        return guest_id;
    }

    public String getName() {
        return guest_name;
    }

    public void setName(String guest_name) {
        this.guest_name = guest_name;
    }

    

    public void setId(int guest_id) {
        this.guest_id = guest_id;
    }
    
}
