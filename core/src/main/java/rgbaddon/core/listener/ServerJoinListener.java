package rgbaddon.core.listener;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import rgbaddon.core.Configuration;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.Chat;

public class ServerJoinListener {

  private final RgbAddon addon;
  private final Configuration config;
  private Chat chat;

  public ServerJoinListener(RgbAddon addon) {
    this.addon = addon;
    this.config = addon.configuration();
    this.chat = addon.chat;
  }

  @Subscribe
  public void onServerJoin(ServerJoinEvent event) {
    chat.clearDuplicateMessages();
    chat.clearDuplicateMessageCount();
  }
}