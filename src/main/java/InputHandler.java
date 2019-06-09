import java.util.Arrays;

/**
 * This class handles the inout from console.
 */
public class InputHandler {
    RoomReservationService roomReservationService;

    /**
     * This method expects 3 types of commands:
     * <ul>
     *      <li>create hotel (amountRooms, amountDays) - initializes a hotel.</li>
     *      <li>create reservation (startDate, endDate) - creates a reservation.</li>
     *      <li> exit - exits program.</li>
     * </ul>
     * Other commands are ignored.
     * <p>
     * Regexp is used to ensure the input is valid and to parse it.
     * <p>
     * An example of usage:
     * <p>
     *     <ul>
     *      <li>Enter command:</li>
     *      <li>create hotel 2 3</li>
     *      <li>A hotel with 2 rooms was successfully created. The planing period is 0-2</li>
     *      <li>create reservation 1 2</li>
     *      <li>The reservation was successfully made</li>
     *      <li>create reservation 1 2</li>
     *      <li>The reservation was successfully made</li>
     *      <li>create reservation 1 2</li>
     *      <li>The reservation was declined</li>
     * </ul>
     *
     * @param input user input from the console.
     * @return true if the program should run further, false if the program should stop.
     */
    public boolean handleInput(String input) {
        if (input.equals("exit")) {
            return false;
        }  else if (input.startsWith("create hotel")) {
            String[] tokens = input.split(" ");
            String inputExpected =
                "Expected input to create a hotel should look as follows: " + "\n"
                    + "create hotel amountRooms amountDays" + " e.g " + "create hotel 1 3";
            if (tokens.length != 4) {
                System.out.println(inputExpected);
                return true;
            }
            int amountRooms;
            int amountDays;

            try {
                amountRooms = Integer.parseInt(tokens[2]);
                amountDays = Integer.parseInt(tokens[3]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println(inputExpected);
                return true;
            }

            try {
                roomReservationService = new RoomReservationService(amountRooms, amountDays);
                System.out.println("A hotel with " + amountRooms
                    + " rooms was successfully created. The planing period is 0-" + (amountDays - 1) );
            } catch (IllegalArgumentException ex) {
                System.out.println("The hotel was not created");
                System.out.println(ex.getMessage());
            }

            return true;
        } else if (input.startsWith("create reservation")) {
            if (roomReservationService == null) {
                System.out.println("Please create a hotel before creating a reservation");
                return true;
            }
            String[] tokens = input.split(" ");
            String inputExpected =
                "Expected input to create a reservation should look as follows: " + "\n"
                    + "create reservation start end" + " e.g " + "create reservation 0 2";
            if (tokens.length != 4) {
                System.out.println(inputExpected);
                return true;
            }

            int start;
            int end;
            try {
                start = Integer.parseInt(tokens[2]);
                end = Integer.parseInt(tokens[3]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println(inputExpected);
                return true;
            }

            try {
                if (roomReservationService.requestReservation(start, end)) {
                    System.out.println("The reservation was successfully made");
                } else {
                    System.out.println("The reservation was declined");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("The arguments were invalid");
                System.out.println(ex.getMessage());
                System.out.println(inputExpected);
            }
            return true;
        } else {
            System.out.println("The command was not recognized");
            return true;
        }
    }
}
