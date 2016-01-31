package com.dermotherlihy.lottery.rest.v1.controller;

import com.dermotherlihy.lottery.domain.exception.MaxLinesExceededException;
import com.dermotherlihy.lottery.domain.exception.NotFoundException;
import com.dermotherlihy.lottery.domain.exception.TicketExpiredException;
import com.dermotherlihy.lottery.domain.model.Ticket;
import com.dermotherlihy.lottery.domain.service.TicketsService;
import com.dermotherlihy.lottery.rest.v1.mapper.TicketsResponseMapper;
import com.dermotherlihy.lottery.rest.v1.resource.request.LinesRequest;
import com.dermotherlihy.lottery.rest.v1.resource.request.TicketRequest;
import com.dermotherlihy.lottery.rest.v1.resource.response.TicketResponse;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by dermot.herlihy on 28/01/2016.
 *
 */
@RestController
@RequestMapping(value = {TicketsController.PATH}, consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketsController {

    public static final String PATH = "/v1/tickets";

    @Autowired
    private TicketsService ticketsService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<TicketResponse> createTicket(@RequestBody TicketRequest resource) {
        Ticket ticket = ticketsService.createTicket(resource.getNumberOfLines());
        TicketResponse ticketResponse = TicketsResponseMapper.mapTicketResponse(ticket);
        ticketResponse.add(linkTo(methodOn(TicketsController.class).getTicketById(ticket.getId())).withSelfRel());
        return new ResponseEntity<TicketResponse>(ticketResponse, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.POST, value ="/{id}/lines")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<TicketResponse> addLinesToTicket(@PathVariable long id, @RequestBody LinesRequest request) {
        Ticket ticket =  ticketsService.addLines(id,request.getNumberOfLines());
        TicketResponse ticketResponse = TicketsResponseMapper.mapTicketResponse(ticket);
        ticketResponse.add(linkTo(methodOn(TicketsController.class).getTicketById(ticket.getId())).withSelfRel());
        return new ResponseEntity<TicketResponse>(ticketResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PagedResources<Resource<TicketResponse>> getTickets(Pageable pageRequest, PagedResourcesAssembler<TicketResponse> assembler) {

        Page<Ticket> tickets = ticketsService.getTickets(pageRequest);
        Page<TicketResponse> ticketResponses = TicketsResponseMapper.mapTicketsPageToTicketsResponsePage(tickets);
        PagedResources<Resource<TicketResponse>> result = assembler.toResource(ticketResponses);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public HttpEntity<TicketResponse> getTicketById(@PathVariable long id) {
        Optional<Ticket> optionalTicket = ticketsService.getTicket(id);
        if(optionalTicket.isPresent()){
            Ticket ticket = optionalTicket.get();
            TicketResponse ticketResponse = TicketsResponseMapper.mapTicketResponse(ticket);
            ticketResponse.add(linkTo(methodOn(TicketsController.class).getTicketById(ticket.getId())).withSelfRel());
            return new ResponseEntity<TicketResponse>(ticketResponse, HttpStatus.OK);
        }
        throw new NotFoundException("Please specify a valid ticket id"); // Picked up by Exception Handler
    }

    @ExceptionHandler
    void handleMaxLinesExceededException(MaxLinesExceededException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage() );
    }
    @ExceptionHandler
    void handleTicketExpiredException(TicketExpiredException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ExceptionHandler
    void handleNotFoundException(NotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
