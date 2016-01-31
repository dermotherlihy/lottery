package com.dermotherlihy.lottery.rest.v1.mapper;

import com.dermotherlihy.lottery.domain.model.Line;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.rest.v1.resource.response.LineResponse;
import com.dermotherlihy.lottery.rest.v1.resource.response.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */

public class TicketResponseMapper {

    public static TicketResponse mapTicketResponse(Ticket ticket){

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketId(ticket.getId());
        ticketResponse.setStatus(ticket.getStatus().name());

        List<LineResponse> lineResponses = new LinkedList<LineResponse>();

        for(int i = 0; i < ticket.getLines().size(); i++){
            Line line = ticket.getLines().get(i);
            LineResponse lineResponse = LineResponseMapper.mapLineResponse(line);
            lineResponses.add(lineResponse);
        }
        ticketResponse.setLines(lineResponses);
        ticketResponse.setStatus(ticket.getStatus().name());
        return ticketResponse;
    }

    public static Page<TicketResponse> mapTicketsPageToTicketsResponsePage(Page<Ticket> tickets) {
        List<TicketResponse> ticketResponses = new ArrayList<TicketResponse>();
        for(Ticket ticket : tickets){
            ticketResponses.add(mapTicketResponse(ticket));
        }
        PageRequest request = new PageRequest(tickets.getNumber(),tickets.getSize());
        Page<TicketResponse> page = new PageImpl<TicketResponse>(ticketResponses,request,tickets.getTotalElements());
        return page;
    }
}
