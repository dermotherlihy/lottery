package com.dermotherlihy.lottery.utils;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Status;
import com.dermotherlihy.lottery.domain.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class TicketTestData {

    private static Random random = new Random();

    public static Ticket generateRandomTicketWithId(){
        Ticket ticket = getRandomTicket();
        ticket.setId(random.nextLong());
        return ticket;
    }
    public static Ticket generateRandomTicketForPersistence(){
        Ticket ticket = getRandomTicket();
        return ticket;
    }

    private static Ticket getRandomTicket() {
        List<Line> lines = new ArrayList();

        for(int i = 0; i < random.nextInt(10); i++){
            lines.add(getRandomLine());
        }
        return new Ticket(lines, Status.NEW);
    }

    private static Line getRandomLine(){
        Line line = new Line(random.nextInt(3),random.nextInt(3),random.nextInt(3));
        return line;
    }
}
