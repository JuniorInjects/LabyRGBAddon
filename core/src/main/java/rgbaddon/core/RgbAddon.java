package rgbaddon.core;

import rgbaddon.core.listener.ChatListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class RgbAddon extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new ChatListener(this));

    this.logger().info("\n__________  __________________    _____       .___  .___             \n"
        + "\\______   \\/  _____/\\______   \\  /  _  \\    __| _/__| _/____   ____  \n"
        + " |       _/   \\  ___ |    |  _/ /  /_\\  \\  / __ |/ __ |/  _ \\ /    \\ \n"
        + " |    |   \\    \\_\\  \\|    |   \\/    |    \\/ /_/ / /_/ (  <_> )   |  \\\n"
        + " |____|_  /\\______  /|______  /\\____|__  /\\____ \\____ |\\____/|___|  /\n"
        + "        \\/        \\/        \\/         \\/      \\/    \\/           \\/ \n\nenabled RGBAddon v... by Subgift and Team\n\n");
  }

  @Override
  protected Class<Configuration> configurationClass() {
    return Configuration.class;
  }
}
