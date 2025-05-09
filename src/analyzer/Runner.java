package analyzer;

import java.util.Scanner;
import java.io.FileReader;
import java.util.concurrent.ExecutionException;
import java.io.FileNotFoundException;
import java.util.concurrent.Future;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.io.File;

public class Runner
{
    HashMap<String, Result> resultMap = new HashMap<>();
    File[] files;
    Pool pool;

    Runner(String[] args) throws FileNotFoundException
    {
        files = (new File(args[0])).listFiles(File::isFile);
        if (files != null)
            pool = new Pool(readPatterns(args[1]), files);
    }

    public void launchPool() throws InterruptedException, ExecutionException
    {
        pool.executeTasks();
        fillResultMap();
    }

    public void fillResultMap() throws ExecutionException, InterruptedException
    {
        for (Future<Result> future : pool.futures)
        {
            Result result = future.get();
            String name = result.getFile().getName();
            Result x = resultMap.get(name);

            if (result.isMatch)
            {
                if (x == null || x.getRank() < result.getRank())
                    resultMap.put(name, result);
            }
            else if (resultMap.size() < files.length)
            {
                if (x == null)
                    resultMap.put(name, result);
            }
        }
    }

    public String getPrintable()
    {
        StringBuilder sb = new StringBuilder();

        for (Result r : resultMap.values())
            sb.append(String.format("%s: %s\n", r.getFile().getName(),
                    r.isMatch ? r.getExpected() : r.getUnknown()));

        return sb.toString();
    }

    public List<String> readPatterns(String path) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new FileReader(path));
        List<String> patterns = new LinkedList<>();

        while (scanner.hasNextLine())
            patterns.add(scanner.nextLine());

        scanner.close();
        return patterns;
    }
}
