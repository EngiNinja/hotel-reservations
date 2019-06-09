import org.junit.Test;

import static org.junit.Assert.*;

public class MyHotelTest {
    public static final int AMOUNT_DAYS = 6;

    @Test public void requestReservationOutsidePlanningPeriod() {
        MyHotel myHotel = new MyHotel(1, 3);
        assertFalse(myHotel.requestReservation(-4, 2));
        assertFalse(myHotel.requestReservation(0, 3));
    }

    @Test public void requestsAreAcceptedAndDeclined() {
        MyHotel myHotel = new MyHotel(3, 14);
        assertTrue(myHotel.requestReservation(0, 5));
        assertTrue(myHotel.requestReservation(7, 13));
        assertTrue(myHotel.requestReservation(3, 9));
        assertTrue(myHotel.requestReservation(5, 7));
        assertTrue(myHotel.requestReservation(6, 6));
        assertTrue(myHotel.requestReservation(0, 4));
    }

    @Test public void requestsAreDeclined() {
        MyHotel myHotel = new MyHotel(3, 16);
        assertTrue(myHotel.requestReservation(1, 3));
        assertTrue(myHotel.requestReservation(2, 5));
        assertTrue(myHotel.requestReservation(1, 9));
        assertFalse(myHotel.requestReservation(0, 15));
    }

    @Test public void requestsCanBeAcceptedAfterDecline() {
        MyHotel myHotel = new MyHotel(3, 16);
        assertTrue(myHotel.requestReservation(1, 3));
        assertTrue(myHotel.requestReservation(0, 15));
        assertTrue(myHotel.requestReservation(1, 9));
        assertFalse(myHotel.requestReservation(2, 5));
        assertTrue(myHotel.requestReservation(4, 9));
    }

    @Test public void complexRequests() {
        MyHotel myHotel = new MyHotel(2, 11);
        assertTrue(myHotel.requestReservation(1, 3));
        assertTrue(myHotel.requestReservation(0, 4));
        assertFalse(myHotel.requestReservation(2, 3));
        assertTrue(myHotel.requestReservation(5, 5));
        assertFalse(myHotel.requestReservation(4, 10));
        assertTrue(myHotel.requestReservation(10, 10));
        assertTrue(myHotel.requestReservation(6, 7));
        assertTrue(myHotel.requestReservation(8, 10));
        assertTrue(myHotel.requestReservation(8, 9));
    }

    @Test public void testReservationCanBeMadeForWholePeriod() {
        int amountDays = 6;
        MyHotel myHotel = new MyHotel(3, amountDays);
        assertTrue(myHotel.requestReservation(0, amountDays - 1));
    }

    @Test public void testAllRoomsCanBeReservedForAllDays() {
        int amountDays = 6;
        int amountRooms = 3;
        MyHotel myHotel = new MyHotel(amountRooms, amountDays);
        for (int i = 0; i < amountRooms; i++) {
            assertTrue(myHotel.requestReservation(0, amountDays - 1));
        }
    }

    @Test public void roomCantBeBookedSameDay() {
        MyHotel myHotel = new MyHotel(1, 5);
        assertTrue(myHotel.requestReservation(0, 0));
        assertFalse(myHotel.requestReservation(0, 1));
        assertTrue(myHotel.requestReservation(1,2));
        assertFalse(myHotel.requestReservation(2, 3));
        assertTrue(myHotel.requestReservation(4, 4));
        assertFalse(myHotel.requestReservation(3, 4));
    }
}




