
import java.util.ArrayList;
import java.util.Scanner;


public class HotelBooking {
    Scanner sc;
    ArrayList<Integer> starting = new ArrayList<Integer>();
    ArrayList<Integer> ending = new ArrayList<Integer>();
    ArrayList<GuestInfo> guestId = new ArrayList<GuestInfo>();
    ArrayList<RoomInfo> rooms = new ArrayList<RoomInfo>();
    ArrayList<BookingInfo> bookings = new ArrayList<BookingInfo>();
    
    
    
    public HotelBooking(){
        sc = new Scanner(System.in);
        starting.add(0);
        ending.add(0);
        menu();
        
    }
    public static void main(String[] args) {
        HotelBooking hotel = new HotelBooking();
    }
    
    void guestAdd() {
        System.out.println("Please Enter Guest Name");
        String name = sc.nextLine();
        int id = 0;
        if (guestId.isEmpty()) {
            id = 1;
        } else {
            id = guestId.size() + 1;
        }
        guestId.add(new GuestInfo(name, id));
        System.out.println("Guest " + name + " has been created with guest ID: " + id);
        System.out.println("Would you like to [A]dd a new guest or [R]eturn to the previous menu?");
        char option = sc.nextLine().toLowerCase().charAt(0);
        while(true){
        if (option == 'a') {
            guestAdd();
        } else if (option == 'r') {
            menu();
        } else {
            System.out.println("Invalid Input!");
            System.out.println("Would you like to [A]dd a new guest or [R]eturn to the previous menu?");
            option = sc.nextLine().toLowerCase().charAt(0);
            
            
        }
        }

    }
    
    
    void menu(){
        System.out.println("Welcome to FedUni Hotel");
        System.out.println("");
        System.out.println("Main Menu - please select an option:");
        System.out.println("");
        System.out.println("1. Add Guest");
        System.out.println("2. Add Room");
        System.out.println("3. Add Booking");
        System.out.println("4. View Bookings");
        System.out.println("5. Quit");
        System.out.print("Enter Choice: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch(option){
            case 1 : guestAdd();
                     break;
            case 2 : roomAdd();
                     break;
            case 3 : bookingAdd();
                     break;
            case 4 : bookingView();
                     break;
            case 5 : quit();
                     break;
            default : System.out.println("Invalid input");
                     menu();
        }
    }
    
    void quit(){
        System.out.println("Thanks for using FedUni Hotel bookings");
    }
    
    void roomAdd(){
        System.out.println("Please enter room number");
        int number = sc.nextInt();
        RoomInfo temp = new RoomInfo(number, 0);
        if (!rooms.contains(temp)) {
            System.out.println("Please enter room capacity:");
            int capacity = sc.nextInt();
            sc.nextLine();
            rooms.add(new RoomInfo(number, capacity));
            System.out.println("Would you like to [A]dd a new room or [R]eturn to the previous menu?");
            char option = sc.nextLine().toLowerCase().charAt(0);
            while(true){
            if (option == 'a') {
                roomAdd();
                break;
            } else if (option == 'r') {
                menu();
                break;
            } else {
                System.out.println("Invalid Input!");
                System.out.println("Would you like to [A]dd a new room or [R]eturn to the previous menu?");
                option = sc.nextLine().toLowerCase().charAt(0);
            }
            }
        } else {
            System.out.println("Room already exists");
            roomAdd();
        }
    }
    
    static int dateToDayNumber(int month, int day) {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return 0;
        }
        if (month == 1) {
            return day;
        }
        if (month == 2) {
            return 31 + day;
        }
        if (month == 3) {
            return 59 + day;
        }
        if (month == 4) {
            return 90 + day;
        }
        if (month == 5) {
            return 120 + day;
        }
        if (month == 6) {
            return 151 + day;
        }
        if (month == 7) {
            return 181 + day;
        }
        if (month == 8) {
            return 212 + day;
        }
        if (month == 9) {
            return 243 + day;
        }
        if (month == 10) {
            return 273 + day;
        }
        if (month == 11) {
            return 304 + day;
        }
        return 334 + day;
    }
    
    int days(int month) {
        if (month == 2) {
            return 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }
    
    void bookingAdd(){
        System.out.println("Please enter guest ID ");
        int id = sc.nextInt();
        while (id > guestId.size() || id <= 0) {
            System.out.println("Guest ID does not exist");
            bookingAdd();
        }

        RoomInfo selected = null;
        while (true) {
            System.out.println("Please enter room number: ");
            int number = sc.nextInt();
            
            if(roomexists(number)) {
                selected = getRoom(number);
                break;
            } else {
                System.out.println("This Room number not exist!");
            }
        }
        BookingInfo booking = null;
        while (true) {
            System.out.println("Please enter number of guests : ");
            int number_of_guests = sc.nextInt();
            if(number_of_guests > selected.getCapacity()) {
                System.out.println("Number of guests exceeds the capacity of room : " + selected.getCapacity());
            } else {
                booking = new BookingInfo(id, selected.getRoomNumber(), number_of_guests);
                break;
            }
        }
        
        System.out.println("Please enter check-in month: ");
        int checkin_month = sc.nextInt();
        while (checkin_month > 12 || checkin_month <= 0) {
            System.out.println("Invalid month. Please enter check-in month:");
            checkin_month = sc.nextInt();
        }

        System.out.println("Please enter check-in day:");
        int checkin_day = sc.nextInt();
        while (days(checkin_month) < checkin_day) {
            System.out.println("Invalid day. Please enter check-in day:");
            checkin_day = sc.nextInt();
        }
        int start = dateToDayNumber(checkin_month, checkin_day);

        System.out.println("Please enter check-out month:");
        int checkout_month = sc.nextInt();

        System.out.println("Please enter check-out day:");
        int checkout_day = sc.nextInt();
        sc.nextLine();
        int end = dateToDayNumber(checkout_month, checkout_day);
         
        if (isBooked(selected.getRoomNumber(),booking.getStarting(),booking.getEnding())) {
            System.out.println("*** Booking Successful ***");
            booking.setStarting(start);
            booking.setEnding(end);
            booking.setCheckinDay(checkin_day);
            booking.setCheckoutDay(checkout_day);
            booking.setCheckinMonth(checkin_month);
            booking.setCheckoutMonth(checkout_month);
            bookings.add(booking);
            rooms.add(selected);

        } else {
            System.out.println("Room is not available during that period.");
            bookingAdd();
       }
       

        System.out.println("Would you like to [A]dd a new booking or [R]eturn to the previous menu?");

        char option = sc.nextLine().toLowerCase().charAt(0);
        while(true){
        if (option == 'a') {
            bookingAdd();
            break;
        } else if(option == 'r') {
            menu();
            break;
        } else {
            System.out.println("Invalid Input!");
            System.out.println("Would you like to [A]dd a new booking or [R]eturn to the previous menu?");
            
            option = sc.nextLine().toLowerCase().charAt(0);
        }
    }
    }
    
    void bookingView() {
        System.out.println("Would you like to view [G]uest bookings, [R]oom booking, or e[X]it?");
        char option = sc.nextLine().toLowerCase().charAt(0);
        if (option == 'g') {
            bookingGuests();
        } else if (option == 'r') {
            bookingRooms();
        } else if (option == 'x'){
            menu();
        } else {
            System.out.println("Invalid Input!");
            bookingView();
        }

    }
    
    void bookingGuests(){
        System.out.println("Please enter guest ID:");
        int id = sc.nextInt();
        sc.nextLine();
        int index = id - 1;
        if (guestId.size() >= index) {
            System.out.println("Guest " + id + " :  " + guestId.get(index).getName());
            BookingInfo booking = getbyGuest(id);
            System.out.println("Booking : Room " + booking.getRoomNumber() + " ," + booking.getNumberOfGuests() + "guest[s] from  "
                    + booking.getCheckinMonth() + "/" + booking.getCheckinDay()+ " to " + booking.getCheckoutMonth() + "/" + booking.getCheckoutDay());
            bookingView();
        } else {
            System.out.println("Guest does not exist");
            bookingGuests();
        }
    }
    
    void bookingRooms(){
        System.out.println("Please enter room number:");
        int number = sc.nextInt();
        sc.nextLine();
        BookingInfo booking = getbyRoom(number);
        
        if (booking.getRoomNumber()==number) {
              int index = rooms.indexOf(number);
              
             System.out.println("Room " + number + " bookings");
             System.out.println("Guest :  " + booking.getId() + " ," + booking.getNumberOfGuests() + "guest[s] from  "
                    + booking.getCheckinMonth() + "/" + booking.getCheckinDay() + " to " + booking.getCheckoutMonth() + "/" + booking.getCheckoutDay());
            
            bookingView();

        } else {
            System.out.println("Room does not exists");
            bookingRooms();
        }
    }
    
    
    private BookingInfo getbyGuest(int id) {
        for (BookingInfo booking : bookings) {
            if(booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }
    
    private BookingInfo getbyRoom(int number) {
        for (BookingInfo booking : bookings) {
            if(booking.getRoomNumber() == number) {
                return booking;
            }
        }
        return null;
    }
    
    private boolean isBooked(int number, int start,int end){
        int flag=0;
        for(BookingInfo booking : bookings){
            if(booking.getRoomNumber()==number){
                flag=1;
                if(booking.getEnding()<start || booking.getStarting()>end){
                    return true;
                }
            }
        }
        if(flag==0) return true;
        return false;
    }
    
    public boolean roomexists(int number){
        for(RoomInfo room : rooms){
            if(room.getRoomNumber()==number){
                return true;
            }
        }
        return false;
    }
    
    public RoomInfo getRoom(int number){
        for(RoomInfo room : rooms){
            if(room.getRoomNumber()==number){
                return room;
            }
        }
        return null;
        
    }
    
    
}
