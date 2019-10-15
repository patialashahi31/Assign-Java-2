
public class BookingInfo {
    
    private int guest_id,room_number,number_of_guests,checkin_day, checkin_month,checkout_month,checkout_day, starting, ending;

    public BookingInfo(int guest_id, int room_number, int number_of_guests) {
        this.guest_id = guest_id;
        this.room_number = room_number;
        this.number_of_guests = number_of_guests;
    }

    public int getId() {
        return guest_id;
    }

    

    public int getRoomNumber() {
        return room_number;
    }

    

    public int getNumberOfGuests() {
        return number_of_guests;
    }

    public void setNumberOfGuests(int number_of_guests) {
        this.number_of_guests = number_of_guests;
    }

    public int getCheckinDay() {
        return checkin_day;
    }

    

    public int getCheckinMonth() {
        return checkin_month;
    }

    

    public int getCheckoutDay() {
        return checkout_day;
    }

    

    public int getCheckoutMonth() {
        return checkout_month;
    }

    

    public int getStarting() {
        return starting;
    }

    

    public int getEnding() {
        return ending;
    }
    
    public void setCheckinDay(int checkin_day) {
        this.checkin_day = checkin_day;
    }
    
    public void setRoomNumber(int room_number) {
        this.room_number = room_number;
    }
    
    public void setCheckinMonth(int checkin_month) {
        this.checkin_month = checkin_month;
    }
    
    
    public void setCheckoutDay(int checkout_day) {
        this.checkout_day = checkout_day;
    }
    
    public void setCheckoutMonth(int checkout_month) {
        this.checkout_month = checkout_month;
    }
    
    public void setStarting(int starting) {
        this.starting = starting;
    }

    public void setEnding(int ending) {
        this.ending = ending;
    }
    public void setGuestId(int guestId) {
        this.guest_id = guest_id;
    }
}
