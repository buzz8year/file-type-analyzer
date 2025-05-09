package analyzer.core;

import analyzer.Result;
import analyzer.core.impl.KMPAnalyzer;
import analyzer.core.impl.NaiveAnalyzer;
import analyzer.core.impl.RKAnalyzer;
import lombok.Getter;

import java.util.Objects;

public class AnalyzeStrategy
{
    @Getter
    Result result;
    Analyzer analyzer;
    double start;
    double end;

    public AnalyzeStrategy(String algo)
    {
        if (Objects.equals(algo, "--naive"))
            setAnalyzer(new NaiveAnalyzer());

        else if (Objects.equals(algo, "--RK"))
            setAnalyzer(new RKAnalyzer());

        else if (Objects.equals(algo, "--KMP"))
            setAnalyzer(new KMPAnalyzer());

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

}