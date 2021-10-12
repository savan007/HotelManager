package com.savan.lil.learningspring.web;

import com.savan.lil.learningspring.business.domain.RoomReservation;
import com.savan.lil.learningspring.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RoomReservationWebController.class)
public class RoomReservationWebControllerTest {

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getReservations() throws Exception{
        String dateString = "2020-01-01";
        Date date = DateUtils.createDateFromDateString(dateString);

        List<RoomReservation> roomReservations = new ArrayList<>();
        RoomReservation roomReservation = new RoomReservation();

        roomReservation.setLastName("JUnit");
        roomReservation.setFirstName("TEST");
        roomReservation.setDate(date);
        roomReservation.setGuestId(1);
        roomReservation.setRoomId(100);
        roomReservation.setRoomName("sweet room");
        roomReservation.setRoomNumber("S1");
        roomReservations.add(roomReservation);
        given(reservationService.getRoomReservationsForDate(date)).willReturn(roomReservations);

        this.mockMvc.perform(get("/reservations?date=2020-01-01"))
                .andExpect(status().isOk());
    }
}
