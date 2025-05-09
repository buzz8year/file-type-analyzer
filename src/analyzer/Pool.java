package analyzer;

import java.util.concurrent.*;
import java.util.LinkedList;
import java.util.List;
import java.io.File;

public class Pool
{
    List<Callable<Result>> taskList = new LinkedList<>();
    ExecutorService executor;
    List<String> patterns;
    File[] files;

    public List<Future<Result>> futures;

    public Pool(List<String> patterns, File[] files)
    {
        executor = Executors.newFixedThreadPool(files.length);
        this.patterns = patterns;
        this.files = files;
    }

    public void executeTasks() throws InterruptedException
    {
        for (File file : files)
            createTask(file);

        futures = executor.invokeAll(taskList);
        executor.shutdown();
    }

    public void createTask(File file)
    {
        //if (file.getPath().contains("patterns.db")) return;
        for (String pattern : patterns)
            taskList.add(new Task(file.getAbsolutePath(), pattern));
    }
}