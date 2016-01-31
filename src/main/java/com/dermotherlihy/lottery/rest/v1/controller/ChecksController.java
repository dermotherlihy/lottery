package com.dermotherlihy.lottery.rest.v1.controller;

import com.dermotherlihy.lottery.domain.exception.NotFoundException;
import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.service.ChecksService;
import com.dermotherlihy.lottery.rest.v1.mapper.ChecksResponseMapper;
import com.dermotherlihy.lottery.rest.v1.resource.request.CheckRequest;
import com.dermotherlihy.lottery.rest.v1.resource.response.CheckResponse;
import com.google.common.base.Optional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
@RestController
@RequestMapping(value = {ChecksController.PATH})
public class ChecksController {

    public static final String PATH = "/v1/checks";

    @Resource
    private ChecksService checksService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<CheckResponse> checkCheck(@RequestBody CheckRequest checkRequest) {
        Check check = checksService.createCheck(checkRequest.getTicketId());
        CheckResponse checkResponse = ChecksResponseMapper.mapCheckResponse(check);
        checkResponse.add(linkTo(methodOn(ChecksController.class).getCheckById(check.getId())).withSelfRel());
        return new ResponseEntity<CheckResponse>(checkResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public HttpEntity<CheckResponse> getCheckById(@PathVariable long id) {
        Optional<Check> optionalCheck = checksService.getCheck(id);
        if(optionalCheck.isPresent()){
            Check check = optionalCheck.get();
            CheckResponse checkResponse = ChecksResponseMapper.mapCheckResponse(check);
            checkResponse.add(linkTo(methodOn(ChecksController.class).getCheckById(check.getId())).withSelfRel());
            return new ResponseEntity<CheckResponse>(checkResponse, HttpStatus.OK);

        }else{
            throw new NotFoundException("Please specify a valid check id"); // Picked up by Exception Handler
        }
    }


    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler
    void handleIllegalArgumentException(NotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

}
