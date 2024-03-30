package rgbaddon.core.listener;

import net.labymod.api.Laby;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.component.Component;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import rgbaddon.core.Configuration;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.Chat;
import java.util.ArrayList;

public class ChatReceiveListener {

  private final RgbAddon addon;
  private final Configuration config;
  private Chat chat;

  public ChatReceiveListener(RgbAddon addon) {
    this.addon = addon;
    this.config = addon.configuration();
    this.chat = addon.chat;
  }

  private int loosCount = 0;

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    String message = event.chatMessage().getOriginalPlainText();
    String[] list = {"2500", "5000", "10000", "20000", "40000", "80000", "160000", "320000", "640000", "1280000", "2560000"};
    if(message.startsWith("[GrieferGames]")) {
      if(message.contains("Du hast leider verloren und 1$ verloren.")) {
        loosCount++;
        if(loosCount==5) {
          new Thread(() -> {
              try {
                  Thread.sleep(2100);
                  addon.labyAPI().minecraft().chatExecutor().chat("/coinflip " + list[loosCount-5]);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }).start();
        }else {
          new Thread(() -> {
            try {
              Thread.sleep(2100);
              addon.labyAPI().minecraft().chatExecutor().chat("/coinflip 1");
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }).start();
        }
      }else if(message.contains("Du hast leider verloren und")) {
        loosCount++;
        if(loosCount>=5) {
          new Thread(() -> {
            try {
              Thread.sleep(2100);
              addon.labyAPI().minecraft().chatExecutor().chat("/coinflip " + list[loosCount-5]);
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }).start();
        }
      }else if(message.contains("Herzlichen Glückwunsch! Du hast die Summe von")) {
        loosCount=0;
        new Thread(() -> {
          try {
            Thread.sleep(2100);
            addon.labyAPI().minecraft().chatExecutor().chat("/coinflip 1");
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }).start();
      }
    }
    /*
    ArrayList<ChatMessage> duplicates = chat.getDuplicateMessages().computeIfAbsent(message, key -> new ArrayList<>());
    int count = chat.getDuplicateMessageCount().compute(message, (key, value) -> value == null ? 1 : value + 1);

    if (count >= config.stackSameMessageSubSetting.amount()) {
      duplicates.forEach(ChatMessage::delete);
      duplicates.clear();
      event.chatMessage().component().append(Component.text(chat.getDuplicateText(count)));
    }

    duplicates.add(event.chatMessage());
    */
  }
}