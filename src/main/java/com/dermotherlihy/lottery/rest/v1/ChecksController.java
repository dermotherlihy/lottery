package com.dermotherlihy.lottery.rest.v1;

import com.dermotherlihy.lottery.rest.v1.resource.CheckRequest;
import com.dermotherlihy.lottery.rest.v1.resource.CheckResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
@RestController
@RequestMapping("/v1/checks")
public class ChecksController {

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CheckResponse checkTicket(@RequestBody CheckRequest check) {
        return new CheckResponse();
    }

}
