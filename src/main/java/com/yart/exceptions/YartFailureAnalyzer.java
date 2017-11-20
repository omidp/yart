package com.yart.exceptions;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class YartFailureAnalyzer extends AbstractFailureAnalyzer<YartFailureException>
{

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, YartFailureException cause)
    {
        return new FailureAnalysis("this is a showcase of failure", "remove me", cause);
    }

}
