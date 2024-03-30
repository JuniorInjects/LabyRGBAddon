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
import rgbaddon.core.imports.GGCoinflip;
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
  private int lastFlip = 0;

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    String message = event.chatMessage().getOriginalPlainText();
    if(message.startsWith("[GrieferGames]") && GGCoinflip.flipping) {
      int plus = config.ggCoinflipSubSetting.getPlus();
      int save = config.ggCoinflipSubSetting.getSave();
      if(message.contains("Du hast nicht genug Geld.")) {
        loosCount=0;
        try {
          Thread.sleep(2100);
          addon.labyAPI().minecraft().chatExecutor().chat("/coinflip 1");
          lastFlip = plus;
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }else if(message.contains("Du hast leider verloren und 1$ verloren.")) {
        loosCount++;
        if(loosCount==save) {
          new Thread(() -> {
              try {
                  Thread.sleep(2100);
                  addon.labyAPI().minecraft().chatExecutor().chat("/coinflip " + plus);
                  lastFlip = plus;
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }).start();
        }else {
          new Thread(() -> {
            try {
              Thread.sleep(2100);
              addon.labyAPI().minecraft().chatExecutor().chat("/coinflip 1");
              lastFlip = 1;
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
          }).start();
        }
      }else if(message.contains("Du hast leider verloren und")) {
        loosCount++;
        if(loosCount>=save) {
          if(lastFlip*2>config.ggCoinflipSubSetting.getMax()) {
            if(config.ggCoinflipSubSetting.getResetAfterMax()) {
              try {
                Thread.sleep(2100);
                addon.labyAPI().minecraft().chatExecutor().chat("/coinflip 1");
                lastFlip = 1;
                loosCount=0;
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }else {
              addon.labyAPI().minecraft().chatExecutor().displayClientMessage(Component.translatable("rgbaddon.messages.ggcoinflip.reset"));
            }
          }else {
            new Thread(() -> {
              try {
                Thread.sleep(2100);
                addon.labyAPI().minecraft().chatExecutor().chat("/coinflip " + lastFlip*2);
                lastFlip = lastFlip*2;
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }).start();
          }
        }
      }else if(message.contains("Herzlichen Glückwunsch! Du hast die Summe von")) {
        loosCount=0;
        new Thread(() -> {
          try {
            Thread.sleep(2100);
            addon.labyAPI().minecraft().chatExecutor().chat("/coinflip 1");
            lastFlip = 1;
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }).start();
      }
    }

    if(config.stackSameMessageSubSetting.isEnabled()) {
      ArrayList<ChatMessage> duplicates = chat.getDuplicateMessages()
          .computeIfAbsent(message, key -> new ArrayList<>());
      int count = chat.getDuplicateMessageCount()
          .compute(message, (key, value) -> value == null ? 1 : value + 1);

      if (count >= config.stackSameMessageSubSetting.amount()) {
        duplicates.forEach(ChatMessage::delete);
        duplicates.clear();
        event.chatMessage().component().append(Component.text(chat.getDuplicateText(count)));
      }

      duplicates.add(event.chatMessage());
    }
  }
}