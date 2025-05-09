package analyzer.core;

import analyzer.Result;

public interface Analyzer
{
    <T extends Result> boolean analyze(T result);
}
