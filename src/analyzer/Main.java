package analyzer;

import java.util.concurrent.ExecutionException;
import java.io.FileNotFoundException;

public class Main
{
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, ExecutionException
    {
        Commander commander = new Commander(args);
        commander.launchPool();

        System.out.println(commander.getPrintable());
    }
}