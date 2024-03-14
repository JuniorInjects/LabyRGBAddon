package rgbaddon.core.listener;

import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.render.GameRenderEvent;
import rgbaddon.core.RgbAddon;

public class GameRenderListener {

  private final RgbAddon addon;

  public GameRenderListener(RgbAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameRender(GameRenderEvent event) {
    if (event.phase() != Phase.PRE) {
      return;
    }

    this.addon.itemPhysics.updateLastRenderTime();
  }
}