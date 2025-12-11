package com.n.in.strategy;

import com.n.in.model.Step;

public interface IAClientStrategy {
    Object generate(Step step);
}
