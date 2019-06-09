/**
 * This class handles the inout from console.
 */
public class InputHandler {
    MyHotel myHotel;

    /**
     * This method expects 4 types of commands:
     * create hotel (amountRooms, amountDays) - initializes a hotel.
     * create reservation (startDate, endDate) - creates a reservation.
     * exit - exits program.
     * other - ignored.
     * Regexp is used to ensure the input is valid and to parse it.
     * <p>
     * An example of usage:
     * <p>
     * Enter command:
     * create hotel (2,3)
     * A hotel with 2 rooms was successfully created. The planing period is 0-2.
     * create reservation (1,2)
     * The reservation was successfully made
     * create reservation (1,2)
     * The reservation was successfully made
     * create reservation (1,2)
     * The reservation was declined
     *
     * @param input user input from the console.
     * @return true if the program should run further, false if the program should stop.
     */
    public boolean handleInput(String input) {
        if (input.equals("exit")) {
            return false;
        } else if (input.startsWith("create hotel")) {
            String[] tokens = input.split(",|\\(|\\)");
            String inputExpected =
                "Expected input to create a hotel should look as follows: " + "\n"
                    + "create hotel (amountRooms, amountDays)" + " e.g " + "create hotel (1, 3)";
            if (tokens.length != 3) {
                System.out.println(inputExpected);
                return true;
            }
            int amountRooms;
            int amountDays;
            try {
                amountRooms = Integer.parseInt(tokens[1]);
                amountDays = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException ex) {
                System.out.println(inputExpected);
                return true;
            }
            myHotel = new MyHotel(amountRooms, amountDays);
            return true;
        } else if (input.startsWith("create reservation")) {
            if (myHotel == null) {
                System.out.println("Please create a hotel before creating a reservation.");
                return true;
            }
            String[] tokens = input.split(",|\\(|\\)");
            String inputExpected =
                "Expected input to create a reservation should look as follows: " + "\n"
                    + "create reservation (start, end)" + " e.g " + "create hotel (0, 2)";
            if (tokens.length > 3) {
                System.out.println(inputExpected);
                return true;
            }
            int start;
            int end;
            try {
                start = Integer.parseInt(tokens[1]);
                end = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println(inputExpected);
                return true;
            }

            myHotel.requestReservation(start, end);
            return true;
        } else {
            System.out.println("The command was not recognized.");
            return true;
        }
    }
}
