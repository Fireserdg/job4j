package ru.job4j.scan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Scanning file system
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-03-05
 */
public class Search {

    /**
     * Contains logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Search.class);

    /**
     * Count thread
     *
     */
    private final int countThread = Runtime.getRuntime().availableProcessors();

    /**
     * Executor service
     *
     */
    private final ExecutorService threadPool = Executors.newFixedThreadPool(countThread);

    /**
     * BlockingQueue
     *
     */
    private final BlockingQueue<File> queue = new LinkedBlockingQueue<>();

    /**
     * List files
     *
     */
    private final  List<File> files = new CopyOnWriteArrayList<>();

    /**
     * Get list files with extension
     *
     * @param parent parent
     * @param exts  extension
     * @return list of files
     */
    public List<File> files(String parent, List<String> exts) {
        String regExp = getRegExp(exts);
        queue.offer(new File(parent));
        while (!threadPool.isShutdown()) {
            try {
                FutureTask<File> task = new FutureTask<>(() -> queue.poll(10, TimeUnit.MILLISECONDS));
                threadPool.submit(task);
                File file = task.get();
                if (file == null) {
                    threadPool.shutdown();
                } else {
                    threadPool.submit(() -> operation(file, regExp));
                }
            } catch (InterruptedException | ExecutionException e) {
                LOG.error("Error", e);
            }
        }
        return files;
    }

    /**
     * Get regular expression
     *
     * @param exts list files with extension
     * @return regular expression
     */
    private String getRegExp(List<String> exts) {
        return exts.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.joining("|", "(", ")$"),
                        value -> String.format("%s%s", ".+\\.", value)));
    }

    /**
     * Operation for files
     *
     * @param file file
     * @param regExp regExp
     */
    private void operation(File file, String regExp) {
        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .forEach(queue::offer);
        } else {
            if (file.getName().matches(regExp)) {
                files.add(file);
            }
        }
    }
}