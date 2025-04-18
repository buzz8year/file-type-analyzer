package analyzer;

import java.util.concurrent.ExecutionException;
import java.io.FileNotFoundException;

public class Main
{
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, ExecutionException
    {
        Runner runner = new Runner(args);
        runner.launchPool();

        System.out.println(runner.getPrintable());
    }
}