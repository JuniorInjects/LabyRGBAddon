package rgbaddon.core.listener;

import java.text.SimpleDateFormat;
import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.event.client.input.KeyEvent.State;
import rgbaddon.core.Configuration;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.GGCoinflip;

public class KeyListener {

  private final RgbAddon addon;
  private final Configuration config;

  public KeyListener(RgbAddon addon) {
    this.addon = addon;
    this.config = addon.configuration();
  }

  @Subscribe
  public void onKeyPress(KeyEvent event) {
    if(event.state().equals(State.UNPRESSED)) {
      if(event.key().equals(config.ggCoinflipSubSetting.getKeyBind())) {
        if(!GGCoinflip.flipping) {
          GGCoinflip.flipping=true;
          addon.labyAPI().minecraft().chatExecutor().chat("/coinflip 1");
        }else {
          GGCoinflip.flipping=false;
        }
      }
    }
  }
}