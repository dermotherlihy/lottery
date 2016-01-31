package com.dermotherlihy.lottery.domain.service;

import com.dermotherlihy.lottery.domain.model.Check;
import com.google.common.base.Optional;

/**
 * Created by dermot.herlihy on 29/01/2016.
 */
public interface CheckService {

    Check createCheck(long ticketId);

    Optional<Check> getCheck(long id);
}
