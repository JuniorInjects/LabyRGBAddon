package rgbaddon.core.imports.tags;

import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.tags.IconTag;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.render.matrix.Stack;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.enums.AddonIconLocation;
import java.awt.*;

public class AddonTag extends IconTag {

  private RgbAddon addon;
  private AddonIconLocation location;

  private AddonTag(RgbAddon addon, AddonIconLocation location, Integer size) {
    super(Icon.url("https://cs-jako.github.io/images/addons/assets/white_star.png"),
        size);
    this.addon = addon;
    this.location = location;
  }

  public static AddonTag create(RgbAddon addon, AddonIconLocation location) {
    //return new AddonTag(addon, location, addon.configuration().addonIconSubSetting.size());
    return null;
  }

  @Override
  public void render(Stack stack, Entity livingEntity) {
    if (!addon.configuration().enabled().get())
      return;

    //if (!addon.configuration().addonIconSubSetting.isEnabled())
      //return;

    if (!(livingEntity instanceof Player))
      return;

    //if (!addon.configuration().addonIconSubSetting.location().equals(location))
      //return;

    this.labyAPI.renderPipeline().renderSeeThrough(entity, () -> {
      getIcon().render(stack, 0, 0, 8, 8, false, getColor());
    });
  }

  @Override
  public boolean isVisible() {
    //if (!addon.configuration().addonIconSubSetting.isEnabled())
    //  return false;

    //if (!addon.configuration().addonIconSubSetting.location().equals(location))
    //  return false;

    if (this.entity == null)
      return false;

    return addon.isVisible(this.entity);
  }

  private int r = 255, g = 0, b = 0;

  @Override
  public int getColor() {
    //if (addon.configuration().addonIconSubSetting.rgbEnabled()) {
    //  return rgbEffect().getRGB();
    //}

    //return addon.configuration().addonIconSubSetting.getColor();
    return 0;
  }

  private Color rgbEffect() {
    if (r > 0 && b == 0) {
      r--;
      g++;
    }

    if (g > 0 && r == 0) {
      g--;
      b++;
    }

    if (b > 0 && g == 0) {
      r++;
      b--;
    }

    return new Color(r, g, b);
  }
}