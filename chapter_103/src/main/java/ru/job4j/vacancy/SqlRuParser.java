package ru.job4j.vacancy;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * Parser http://www.sql.ru/forum/job-offers/
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */

public class SqlRuParser {

    /**
     * Contains configuration
     *
     */
    private static final Config CONF = new Config();

    /**
     * Contains logger.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParser.class);

    /**
     * Main method for start application
     *
     * @param args arguments
     * @throws SchedulerException exception
     */
    public static void main(String[] args) throws SchedulerException {

        if (args.length != 1 && args[0].equals("app.properties")) {
            throw new NoCorrectlyArgumentException(
                    "Invalid argument, please input correctly argument");
        }
        CONF.loadConfig(args[0]);
        LOG.info("The application has started successfully");
        new SqlStorage(CONF).addVacancy();
        JobDetail jd = JobBuilder.newJob(SqlRuParser.SqlJob.class).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Trigger").
                withSchedule(CronScheduleBuilder.cronSchedule(
                        CONF.getValue("cron.time"))).build();
        Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
        sc.scheduleJob(jd, trigger);
        sc.start();
    }

    /**
     * Inner class contains job for Quartz
     *
     */
    public static class SqlJob implements Job {

        /**
         * Job
         *
         * @param jobExecutionContext exception
         */
        @Override
        public void execute(JobExecutionContext jobExecutionContext)  {
            LOG.info(String.format("Job start time: %s", LocalDateTime.now()));
            new SqlStorage(CONF).addVacancy();
            LOG.info(String.format("Job finish time: %s", LocalDateTime.now()));
        }
    }
}