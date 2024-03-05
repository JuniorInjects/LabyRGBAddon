package rgbaddon.core;

import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.event.Subscribe;
import rgbaddon.core.imports.TNTTimeTag;
import rgbaddon.core.listener.ChatListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import rgbaddon.core.listener.UpdateLightmapTextureEvent;
import rgbaddon.core.widgets.NearbyWidget;

@AddonMain
public class RgbAddon extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new ChatListener(this));
    this.labyAPI().tagRegistry().register("tnttag", PositionType.ABOVE_NAME, new TNTTimeTag(this));

    this.labyAPI().hudWidgetRegistry().register(new NearbyWidget());

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

  @Subscribe
  public void onUpdateLighmapTexture(UpdateLightmapTextureEvent event) {
    if (this.configuration().getFullbright()) {
      event.setCancelled(true);
    }
  }
}
