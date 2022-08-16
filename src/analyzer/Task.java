package analyzer;

import analyzer.core.AnalyzeStrategy;
import java.util.concurrent.Callable;

public class Task implements Callable<Result> {
    String path, patternString, algo = "--RK";

    Task(String path, String patternString) {
        this.patternString = patternString;
        this.path = path;
    }

    @Override
    public Result call() throws Exception {
        AnalyzeStrategy strategy = new AnalyzeStrategy(algo);
        Result result = new Result(path, patternString.split(";"));

        strategy.analyze(result);
        return strategy.getResult();
    }
}