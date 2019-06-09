/**
 * This class represents my implementation of the hotel reservation system.
 */
public class RoomReservationService {

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
    public RoomReservationService(int amountRooms, int amountDays) throws IllegalArgumentException{
        if (amountRooms <= 0 || amountDays <= 0) {
            throw new IllegalArgumentException(
                "The amount of rooms and the amount of days should be bigger than 0");
        }
        this.roomIsBooked = new boolean[amountRooms][amountDays];
        this.amountRooms = amountRooms;
        this.amountDays = amountDays;
    }

    /**
     * @param start
     * @param end
     * @return true if the reservation was accepted, otherwise false.
     */
    public boolean requestReservation(int start, int end) throws IllegalArgumentException {
        for (int i = 0; i < amountRooms; i++) {
            if (roomIsAvailableForPeriod(i, start, end)) {
                for (int j = start; j <= end; j++) {
                    roomIsBooked[i][j] = true;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @param roomId
     * @param start
     * @param end
     * @return true if room is available, otherwise false.
     */
    public boolean roomIsAvailableForPeriod(int roomId, int start, int end)
        throws IllegalArgumentException {
        validateDates(start, end);
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
    public void validateDates(int start, int end) throws IllegalArgumentException {
        boolean outputIsValid = true;
        StringBuilder exceptionMessage = new StringBuilder();
        if (start > end) {
            exceptionMessage.append("The start date should be before the end date" + "\n");
            outputIsValid = false;
        }
        if (start < 0) {
            exceptionMessage.append("The start date should be 0 or later" + "\n");
            outputIsValid = false;
        }
        if (end > this.amountDays - 1) {
            exceptionMessage
                .append("The end date should be " + this.amountDays + " or earlier" + "\n");
            outputIsValid = false;
        }
        if (!outputIsValid) {
            throw new IllegalArgumentException(exceptionMessage.toString());
        }
    }

    @Override public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < amountRooms; i++) {
            for (int j = 0; j < amountDays; j++) {
                boolean currentRoomDayReservation = roomIsBooked[i][j];
                if (currentRoomDayReservation) {
                    output.append("X");
                } else {
                    output.append(".");
                }
            }
            output.append("\n");
        }
        return output.toString();
    }
}
