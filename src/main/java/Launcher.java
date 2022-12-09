import bot.Coiner;
import cron.SendNotification;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Launcher {

    public static void main(String[] args) {
        Coiner bot = new Coiner();

        /* Register your bot to poll updates */
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        /* Schedule tasks not related to updates via Quartz */
        try {
            /* Instantiate the job that will call the bot function */
            JobDetail jobSendNotification = JobBuilder.newJob(SendNotification.class)
                    .withIdentity("sendNotification")
                    .build();

            /* Define a trigger for the call */
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("everyMorningAt8")
                    .withSchedule(
                            CronScheduleBuilder.dailyAtHourAndMinute(8, 0)) //TODO: define your schedule
                    .build();

            /* Create a scheduler to manage triggers */
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.getContext().put("bot", bot);
            scheduler.start();
            scheduler.scheduleJob(jobSendNotification, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}