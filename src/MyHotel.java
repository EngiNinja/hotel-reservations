/**
 * This class represents my implementation of the hotel reservation system.
 */
public class MyHotel {

    /**
     * Represents the room availability.
     * The first dimension represents a room.
     * The second dimension represents a date.
     * roomIsBooked[0][1] == true means the room 0 is booked on the day 1
     */
    private boolean[][] roomIsBooked;
    int amountRooms;
    int amountDays;

    /**
     * @param amountRooms
     * @param amountDays
     */
    public MyHotel(int amountRooms, int amountDays) {
        this.roomIsBooked = new boolean[amountRooms][amountDays];
        this.amountRooms = amountRooms;
        this.amountDays = amountDays;
        System.out.println("A hotel with " + amountRooms
            + " rooms was successfully created. The planing period is 0-" + (amountDays - 1) + ".");
    }

    /**
     * @param start
     * @param end
     * @return true if the reservation was accepted, otherwise false.
     */
    public boolean requestReservation(int start, int end) {
        if (!validateDates(start, end)) {
            return false;
        }
        for (int i = 0; i < roomIsBooked.length; i++) {
            if (roomIsAvailableForPeriod(i, start, end)) {
                for (int j = start; j <= end; j++) {
                    roomIsBooked[i][j] = true;
                }
                System.out.println("The reservation was successfully made.");
                return true;
            }
        }
        System.out.println("The reservation was declined.");
        return false;
    }

    /**
     * @param roomId
     * @param start
     * @param end
     * @return true if room is available, otherwise false.
     */
    public boolean roomIsAvailableForPeriod(int roomId, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (roomIsBooked[roomId][i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param start
     * @param end
     * @return true if dates are valid, otherwise false.
     */
    public boolean validateDates(int start, int end) {
        boolean outputIsValid = true;
        if (start > end) {
            System.out.println("The start date should be before the end date.");
            outputIsValid = false;
        }
        if (start < 0) {
            System.out.println("The start date should be 0 or later.");
            outputIsValid = false;
        }
        if (end > this.amountDays - 1) {
            System.out.println("The end date should be " + this.amountDays + " or earlier.");
            outputIsValid = false;
        }
        return outputIsValid;
    }

    @Override public String toString() {
        String output = "";
        for (int i = 0; i < roomIsBooked.length; i++) {
            for (int j = 0; j < roomIsBooked[i].length; j++) {
                boolean currentRoomDayReservation = roomIsBooked[i][j];
                if (currentRoomDayReservation) {
                    output += true + "  ";
                } else {
                    output += false + " ";
                }
            }
            output += "\n";
        }
        return output;
    }
}
