package analyzer.core;

import analyzer.Result;
import analyzer.core.impl.KMPAnalyzer;
import analyzer.core.impl.NaiveAnalyzer;
import analyzer.core.impl.RKAnalyzer;

import java.util.Objects;

public class AnalyzeStrategy
{
    Analyzer analyzer;
    Result result;
    double start;
    double end;

    public AnalyzeStrategy(String algo)
    {
        if (Objects.equals(algo, "--naive"))
            setAnalyzer(new NaiveAnalyzer());

        else if (Objects.equals(algo, "--KMP"))
            setAnalyzer(new KMPAnalyzer());

        else if (Objects.equals(algo, "--RK"))
            setAnalyzer(new RKAnalyzer());

        // else ...
    }

    public <T extends Analyzer> void setAnalyzer(T analyzer) {
        this.analyzer = analyzer;
    }

    public void analyze(Result result)
    {
        this.result = result;
        this.start = System.nanoTime();

        this.result.isMatch = this.analyzer.analyze(result);
        this.end = System.nanoTime();
    }

    public Result getResult() {
        return result;
    }
}