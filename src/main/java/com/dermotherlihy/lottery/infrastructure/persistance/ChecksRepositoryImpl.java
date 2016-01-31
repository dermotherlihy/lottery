package com.dermotherlihy.lottery.infrastructure.persistance;

import com.dermotherlihy.lottery.domain.model.Check;
import com.dermotherlihy.lottery.domain.repository.ChecksRepository;
import com.dermotherlihy.lottery.infrastructure.persistance.dao.ChecksDAO;
import com.google.common.base.Optional;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
@Repository
public class ChecksRepositoryImpl implements ChecksRepository {

    @Resource
    private ChecksDAO checksDAO;


    @Override
    public Check createCheck(Check check) {
        return checksDAO.save(check);
    }

    @Override
    public Optional<Check> findCheck(long id) {
        return Optional.fromNullable(checksDAO.findOne(id));
    }

    @Override
    public Check findCheckByTicketId(long ticketId) {
        return checksDAO.findByTicketId(ticketId);
    }
}
