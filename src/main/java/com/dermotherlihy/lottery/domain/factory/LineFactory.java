package com.dermotherlihy.lottery.domain.factory;

import com.dermotherlihy.lottery.domain.model.Line;

import java.util.List;

/**
 * Created by dermot.herlihy on 01/02/2016.
 */
public interface LineFactory {
    List<Line> createUniqueLines(int numberOfLines);
    List<Line> addLines(List<Line> lines, int numberOfLines);
}
