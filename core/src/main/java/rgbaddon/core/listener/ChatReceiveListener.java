package rgbaddon.core.listener;

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

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    String message = event.chatMessage().getFormattedText();
    ArrayList<ChatMessage> duplicates = chat.getDuplicateMessages().computeIfAbsent(message, key -> new ArrayList<>());
    int count = chat.getDuplicateMessageCount().compute(message, (key, value) -> value == null ? 1 : value + 1);

    if (count >= config.stackSameMessageSubSetting.amount()) {
      duplicates.forEach(ChatMessage::delete);
      duplicates.clear();
      event.chatMessage().component().append(Component.text(chat.getDuplicateText(count)));
    }

    duplicates.add(event.chatMessage());
  }
}