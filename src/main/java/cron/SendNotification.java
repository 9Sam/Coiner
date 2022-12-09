package cron;

import bot.Coiner;
import org.quartz.*;

public class SendNotification implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        /* Retrieve the bot instance */
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        Coiner bot = (Coiner) schedulerContext.get("bot");

        bot.sendNotification();

    }

}

