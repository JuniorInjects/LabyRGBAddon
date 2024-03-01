package de.rgbaddon.core.listener;

import de.rgbaddon.core.Addon;
import de.rgbaddon.core.Configuration;
import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;

public class ChatListener {

  private final Addon addon;
  private final Configuration config;

  public ChatListener(Addon addon) {
    this.addon = addon;
    this.config = addon.configuration();
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    if(config.copyChatSubSetting.isEnabled()) {
      ChatMessage chatMessage = event.chatMessage();
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

        textComponent = Component.text()
            .append(Component.text(" [COPY]", NamedTextColor.AQUA)
                .clickEvent(ClickEvent.copyToClipboard(copyText)))
            .build();

        event.message().append(textComponent);
      }
    }
  }
}