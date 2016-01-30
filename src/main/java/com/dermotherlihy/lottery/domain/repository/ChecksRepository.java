package com.dermotherlihy.lottery.domain.repository;

import com.dermotherlihy.lottery.domain.model.Check;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public interface ChecksRepository extends CrudRepository<Check, Long> {
}
