package rgbaddon.core.listener;

import rgbaddon.core.RgbAddon;
import rgbaddon.core.Configuration;
import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import java.text.SimpleDateFormat;

public class ChatListener {

  private final RgbAddon addon;
  private final Configuration config;

  public ChatListener(RgbAddon addon) {
    this.addon = addon;
    this.config = addon.configuration();
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    ChatMessage chatMessage = event.chatMessage();
    if(config.copyChatSubSetting.isEnabled()) {
      if (chatMessage.getSenderProfile() != null) {
        String playerName = chatMessage.getSenderProfile().getUsername();
        String message = chatMessage.getOriginalPlainText();

        Component textComponent;
        String copyText;
        if (config.copyChatSubSetting.copyPlayerName()) {
          copyText = message;
        } else {
          copyText = message.substring(playerName.length() + 2);
        }

        if(config.copyChatSubSetting.copyChatTime()) {
          copyText = getTime() + " " + copyText;
        }

        textComponent = Component.text()
            .append(Component.text(" [COPY]", NamedTextColor.AQUA)
                .clickEvent(ClickEvent.copyToClipboard(copyText)))
            .build();

        event.message().append(textComponent);

        if(config.chatTime.isEnabled()) {
          Component textCom;
          textCom = Component.text().append(Component.text(getTime() + " ", NamedTextColor.GREEN)).build();
          textCom.append(event.message());
          event.setMessage(textCom);
        }
      }
    }else {
      if(config.chatTime.isEnabled()) {
        Component textComponent;
        textComponent = Component.text().append(Component.text(getTime() + " ", NamedTextColor.GREEN)).build();
        textComponent.append(event.message());
        event.setMessage(textComponent);
      }
    }
  }

  private String getTime() {
    return formatedTime(System.currentTimeMillis());
  }

  private String formatedTime(final Long millis) {
    //dd.MM.yyyy HH:mm:ss
    String format = "";
    if(config.chatTime.getDisplayDay())
      format = "dd";
    if(config.chatTime.getDisplayMonth()) {
      if(format.isEmpty()) {
        format = "MM";
      }else
        format = format + ".MM";
    }
    if(config.chatTime.getDisplayYear()) {
      if(format.isEmpty()) {
        format = "yyyy";
      }else
        format = format + ".yyyy";
    }

    if(!format.isEmpty())
      format = format + " ";

    if(config.chatTime.getDisplayHours())
      if(format.isEmpty()) {
        format = "HH";
      }else
        format = format + ".HH";
    if(config.chatTime.getDisplayMinutes()) {
      if(format.isEmpty()) {
        format = "mm";
      }else
        format = format + ".mm";
    }
    if(config.chatTime.getDisplaySecounds()) {
      if(format.isEmpty()) {
        format = "ss";
      }else
        format = format + ".ss";
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    if(!config.chatTime.getBrackets()) {
      return simpleDateFormat.format(millis);
    }else
      return "[" + simpleDateFormat.format(millis) + "]";
  }
}