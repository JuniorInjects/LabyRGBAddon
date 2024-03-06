package rgbaddon.core.listener;

import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.Type;
import net.labymod.api.notification.NotificationType;
import net.labymod.api.util.I18n;
import rgbaddon.core.Configuration;
import rgbaddon.core.RgbAddon;

import java.util.Objects;

public class ChatSendListener {

  private final RgbAddon addon;
  private final Configuration config;

  public ChatSendListener(RgbAddon addon) {
    this.addon = addon;
    this.config = addon.configuration();
  }

  @Subscribe
  public void onChatReceive(ChatMessageSendEvent event) {
    String[] word = config.antiRage.badWords().split("; ");
    for(String arg : word) {
      if(arg.toLowerCase().equals(event.getOriginalMessage())) { //  if Rage-word is equal cancel event
        Notification.Builder builder = Notification.builder()
            .title(Component.text(Objects.requireNonNull(I18n.getTranslation("notification.antiRage.title"))))
            .text(Component.text(Objects.requireNonNull(I18n.getTranslation("notification.antiRage.text"))))
            .icon(Icon.head(Laby.labyAPI().minecraft().getClientPlayer().getName()))
            .type(Type.SYSTEM);
        Laby.labyAPI().notificationController().push(builder.build());
        event.setCancelled(true);
        return;
      }
    }
  }
}
