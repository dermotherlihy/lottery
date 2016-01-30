package com.dermotherlihy.lottery.rest.v1;

import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.service.TicketService;
import com.dermotherlihy.lottery.rest.v1.mapper.response.TicketResponseMapper;
import com.dermotherlihy.lottery.rest.v1.resource.LinesRequest;
import com.dermotherlihy.lottery.rest.v1.resource.TicketRequest;
import com.dermotherlihy.lottery.rest.v1.resource.TicketResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by dermot.herlihy on 28/01/2016.
 */

/**

 Proposed solution

 /ticket POST Create a ticket

 /ticket GET Return list of tickets

 /ticket/{id} GET Get individual ticket

 /ticket/{id} PUT Amend ticket lines

 /status/{id} PUT Retrieve status of ticket
 *
 --- My Solution

 /tickets POST Create a ticket

 /tickets GET Return list of tickets, would need to be paginated

 /tickets/{id} GET individual ticket

 /tickets/{id}/lines Add Ticket ticket lines. Post because you are in effect creating a subresource

 /checks POST check status of ticket

Ticket will always have a status. When ticket is created status will be new/something like this
Ticket will then be checked. Once ticket has been checked, status is updated to used


**/



@RestController
@RequestMapping(value = "/v1/tickets", consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketsController {

    public static final String URL = "/v1/tickets";
    private static Log log = LogFactory.getLog(TicketsController.class);

    @Resource
    private TicketService ticketService;

    @Resource
    private TicketResponseMapper ticketResponseMapper;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<TicketResponse> createTicket(@RequestBody TicketRequest resource) {
        log.info("Post Method Hit");
        Ticket ticket = ticketService.createTicket(resource.getNumberOfLines());
        TicketResponse ticketResponse = ticketResponseMapper.mapTicketResponse(ticket);
        ticketResponse.add(linkTo(methodOn(TicketsController.class)).withSelfRel());
        return new ResponseEntity<TicketResponse>(ticketResponse, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.POST, value ="/{id}/lines")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse addLinesToTicket(@RequestBody LinesRequest request) {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<TicketResponse> getTickets(Pageable pageRequest) {

        Page<Ticket> tickets = ticketService.getTickets(pageRequest);
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public TicketResponse getTicketById(@PathVariable Integer ticketId) {
        return null;
    }


}
