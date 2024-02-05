package org.example.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import org.example.core.commands.ExamplePingCommand;
import org.example.core.listener.ExampleGameTickListener;

@AddonMain
public class Addon extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new ExampleGameTickListener(this));
    this.registerCommand(new ExamplePingCommand());

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
