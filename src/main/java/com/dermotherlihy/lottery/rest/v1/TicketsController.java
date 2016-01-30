package com.dermotherlihy.lottery.rest.v1;

import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.service.TicketService;
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


 Lottery Rules

 You have a series of lines on a ticket with 3 numbers, each of which has a value of 0, 1, or 2.

 For each ticket if the sum of the values is 2, the result is 10. Otherwise if they are all the

 same, the result is 5. Otherwise so long as both 2nd and 3rd numbers are different from the

 1st, the result is 1. Otherwise the result is 0.

 Implementation

 Implement a REST interface to generate a ticket with n lines. Additionally we will need to

 have the ability to check the status of lines on a ticket. We would like the lines sorted into

 outcomes before being returned. It should be possible for a ticket to be amended with n

 additional lines. Once the status of a ticket has been checked it should not be possible to

 amend.

 We would like tested, clean code to be returned.


 Have validation at resource level

 *
 *
 */

/**
 *
 * Maybe add interface here
 */


@RestController
@RequestMapping(value = "/v1/tickets", consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketsController {

    public static final String URL = "/v1/tickets";
    private static Log log = LogFactory.getLog(TicketsController.class);

    @Resource
    private TicketService ticketService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<TicketResponse> createTicket(@RequestBody TicketRequest resource) {
        log.info("Post Method Hit");

        Ticket ticket = ticketService.createTicket(resource.getNumberOfLines());


        TicketResponse ticketResponse = new TicketResponse("hello from the other side");
        return new ResponseEntity<TicketResponse>(ticketResponse, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.POST, value ="/{id}/lines")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse addLinesToTicket(@RequestBody LinesRequest request) {
        return new TicketResponse("hello");
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
        return new TicketResponse("hello");
    }


}
