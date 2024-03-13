package rgbaddon.core;

import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.client.entity.player.tag.TagRegistry;
import net.labymod.api.event.Subscribe;
import net.labymod.api.labyconnect.LabyConnect;
import net.labymod.api.labyconnect.protocol.model.friend.Friend;
import rgbaddon.core.imports.FriendTag;
import rgbaddon.core.imports.NameTagLocation;
import rgbaddon.core.imports.RgbAddonTag;
import rgbaddon.core.imports.TNTTimeTag;
import rgbaddon.core.listener.ChatListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import rgbaddon.core.listener.ChatSendListener;
import rgbaddon.core.listener.UpdateLightmapTextureEvent;
import rgbaddon.core.widgets.NearbyWidget;
import java.util.UUID;

@AddonMain
public class RgbAddon extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new ChatListener(this));
    this.registerListener(new ChatSendListener(this));

    TagRegistry tagRegistry = this.labyAPI().tagRegistry();

    tagRegistry.register("tnttag", PositionType.ABOVE_NAME, new TNTTimeTag(this));
    tagRegistry.register("rgbaddontag", PositionType.ABOVE_NAME, new RgbAddonTag(this));
    for (PositionType positionType : PositionType.values()) {
      tagRegistry.registerBefore("badge", "friendtag", positionType,
          FriendTag.create(this, NameTagLocation.getNameTagLocation(positionType)));
    }


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

  public boolean isVisible(Entity entity) {
    if (entity.isInvisible()) {
      return false;
    }

    if (entity.isCrouching()) {
      return false;
    }

    if (!(entity instanceof Player))
      return false;

    if (!isFriend(entity.getUniqueId()))
      return false;

    if (entity.getUniqueId().equals(labyAPI().minecraft().clientPlayer().getUniqueId()))
      return false;

    double distance = labyAPI().minecraft().clientPlayer().getDistanceSquared(entity);

    if (distance > (double) (64.0F * 64.0F)) {
      return false;
    }

    return true;
  }

  public boolean isFriend(UUID uuid) {
    LabyConnect labyConnect = labyAPI().labyConnect();

    if (!labyConnect.isConnected()) {
      return false;
    }

    Friend friend = labyConnect.getSession().getFriend(uuid);

    if (friend == null) {
      return false;
    }

    return true;
  }
}
