package bot;

//import commons.Callback;
import commons.Command;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import utils.stateTracker.State;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.persistence.PersistenceService;
//import utils.l10n.L10nHelper;
import utils.logger.Logger;
import utils.stateTracker.StateTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static commons.Components.createInlineKeyboardButton;
import static commons.Components.createMessage;
import static java.lang.Math.toIntExact;

public class Coiner extends TelegramLongPollingBot {

    /* resource bundles to retrieve configurations and authentications for
     * Telegram API and any external service you may use */
    private static final ResourceBundle authBundle = ResourceBundle.getBundle("auth/bot-config");

    /* a helper to manage users with different language settings, if your bot needs one */
//    private static final L10nHelper langBundle = new L10nHelper("lang/strings");

    /* a simple manager to track the user state in a conversation, if you bot needs one */
    private static final StateTracker stateTracker = new StateTracker(State.getList());

    /* a helper to log events */
    private static final Logger LOGGER = new Logger("log/log-messages");

    /* services your bot may use */
    /* TODO: instantiate the service you actually implemented */
    private static PersistenceService persistenceService;

    /* ---------------------------------------------------- */
    @Override
    public String getBotUsername() {
        return authBundle.getString("bot-username");
    }

    @Override
    public String getBotToken() {
        return authBundle.getString("bot-token");
    }

    /* basically this is the only method your bot will call.
     * The design proposed below turns it into a simple switcher, just recognizing
     * the meaning of the update and delegating its handling to specific methods
     */
    public void onUpdateReceived(Update update) {
            String data = update.getMessage().getText();
            System.out.println(data);
            if (data.equals("in")) {
                System.out.println("Entró");
                try {
                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboard = new ArrayList<>();
                    KeyboardRow row = new KeyboardRow();
                    KeyboardRow row2 = new KeyboardRow();
                    row.add("💰 Wallet");
                    row.add("📆 History");
                    row.add("🏆 Prizes");

                    row2.add("💳 Buy");
                    row2.add("🔣 Settings");
                    row2.add("😣 Help");
                    keyboard.add(row);
                    keyboard.add(row2);

                    keyboardMarkup.setKeyboard(keyboard);
                    keyboardMarkup.setResizeKeyboard(true);
                    keyboardMarkup.setOneTimeKeyboard(true);

                    // Create a message object
                    SendMessage message = new SendMessage();
                            message.setChatId(update.getMessage().getChatId().toString());
                            message.enableMarkdown(true);
                            message.setText("Message text");
                            message.setReplyMarkup(keyboardMarkup);
                    execute(message);
                } catch (TelegramApiException e) {
                    //exception handling
                }
            }
            //Check another options for data
    }


    /* --------------- COMMAND HANDLERS --------------- */
    private void onCommandStart(Update update) {
        SendMessage response = createMessage(update, "This is the starzz button");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        rowInline.add(createInlineKeyboardButton("Update message text", "update_msg_text"));
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        response.setReplyMarkup(markupInline);

        try {
            execute(response);
        }catch (TelegramApiException ex){
            ex.printStackTrace();
        }
    }

    private void onCommandHelp(Update update) {
        //TODO: do stuff
    }

    /* --------------- CALLBACK HANDLERS --------------- */
    // These are the events that can be trigger with buttons or commands
    private void onCallbackFoo(Update update) {
        Message originalMessage = update.getCallbackQuery().getMessage();
        String callbackData = update.getCallbackQuery().getData();
        User sender = update.getCallbackQuery().getFrom();

        LOGGER.log("incoming_update", sender.getId().toString(), "callback", "onCallbackFoo");

        /* this will edit the message from which the callback came from */
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(String.valueOf(sender.getId()));
        editMessageReplyMarkup.setMessageId(originalMessage.getMessageId());

        SendMessage replyMessage = new SendMessage();
        replyMessage.setChatId(originalMessage.getChatId().toString());
        replyMessage.setText("Hello world!");

        //TODO: do stuff
        try {
            execute(editMessageReplyMarkup);
            execute(replyMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /* --------------- RESPONSE TO PLAIN TEXT --------------- */

    private void onInsertData(Update update) {
        //TODO: do stuff
    }

    private void onInsertOtherData(Update update) {
        //TODO: do stuff
    }

    /* operations to be executed not in response to an update */
    public void sendNotification() {
        //TODO: do stuff for example send a notification to some user
    }
}
