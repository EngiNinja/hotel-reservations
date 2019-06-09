import org.junit.Test;

import static org.junit.Assert.*;

public class RoomReservationServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void requestReservationBeforePlanningPeriod() {
        RoomReservationService roomReservationService = new RoomReservationService(1, 3);
        roomReservationService.requestReservation(-4, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void requestReservationAfterPlanningPeriod() {
        RoomReservationService roomReservationService = new RoomReservationService(1, 3);
        roomReservationService.requestReservation(0, 3);
    }

    @Test public void requestsAreAcceptedAndDeclined() {
        RoomReservationService roomReservationService = new RoomReservationService(3, 14);
        assertTrue(roomReservationService.requestReservation(0, 5));
        assertTrue(roomReservationService.requestReservation(7, 13));
        assertTrue(roomReservationService.requestReservation(3, 9));
        assertTrue(roomReservationService.requestReservation(5, 7));
        assertTrue(roomReservationService.requestReservation(6, 6));
        assertTrue(roomReservationService.requestReservation(0, 4));
    }

    @Test public void requestsAreDeclined() {
        RoomReservationService roomReservationService = new RoomReservationService(3, 16);
        assertTrue(roomReservationService.requestReservation(1, 3));
        assertTrue(roomReservationService.requestReservation(2, 5));
        assertTrue(roomReservationService.requestReservation(1, 9));
        assertFalse(roomReservationService.requestReservation(0, 15));
    }

    @Test public void requestsCanBeAcceptedAfterDecline() {
        RoomReservationService roomReservationService = new RoomReservationService(3, 16);
        assertTrue(roomReservationService.requestReservation(1, 3));
        assertTrue(roomReservationService.requestReservation(0, 15));
        assertTrue(roomReservationService.requestReservation(1, 9));
        assertFalse(roomReservationService.requestReservation(2, 5));
        assertTrue(roomReservationService.requestReservation(4, 9));
    }

    @Test public void complexRequests() {
        RoomReservationService roomReservationService = new RoomReservationService(2, 11);
        assertTrue(roomReservationService.requestReservation(1, 3));
        assertTrue(roomReservationService.requestReservation(0, 4));
        assertFalse(roomReservationService.requestReservation(2, 3));
        assertTrue(roomReservationService.requestReservation(5, 5));
        assertFalse(roomReservationService.requestReservation(4, 10));
        assertTrue(roomReservationService.requestReservation(10, 10));
        assertTrue(roomReservationService.requestReservation(6, 7));
        assertTrue(roomReservationService.requestReservation(8, 10));
        assertTrue(roomReservationService.requestReservation(8, 9));
    }

    @Test public void testReservationCanBeMadeForWholePeriod() {
        int amountDays = 6;
        RoomReservationService roomReservationService = new RoomReservationService(3, amountDays);
        assertTrue(roomReservationService.requestReservation(0, amountDays - 1));
    }

    @Test public void testAllRoomsCanBeReservedForAllDays() {
        int amountDays = 6;
        int amountRooms = 3;
        RoomReservationService roomReservationService =
            new RoomReservationService(amountRooms, amountDays);
        for (int i = 0; i < amountRooms; i++) {
            assertTrue(roomReservationService.requestReservation(0, amountDays - 1));
        }
    }

    @Test public void roomCantBeBookedSameDay() {
        RoomReservationService roomReservationService = new RoomReservationService(1, 5);
        assertTrue(roomReservationService.requestReservation(0, 0));
        assertFalse(roomReservationService.requestReservation(0, 1));
        assertTrue(roomReservationService.requestReservation(1, 2));
        assertFalse(roomReservationService.requestReservation(2, 3));
        assertTrue(roomReservationService.requestReservation(4, 4));
        assertFalse(roomReservationService.requestReservation(3, 4));
    }
}




