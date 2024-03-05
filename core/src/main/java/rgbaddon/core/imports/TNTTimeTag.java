package rgbaddon.core.imports;

import java.awt.*;
import java.text.DecimalFormat;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.item.PrimedTnt;
import net.labymod.api.client.entity.player.tag.event.NameTagBackgroundRenderEvent;
import net.labymod.api.client.entity.player.tag.tags.NameTag;
import net.labymod.api.client.gfx.GFXBridge;
import net.labymod.api.client.options.MinecraftOptions;
import net.labymod.api.client.render.font.RenderableComponent;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.loader.platform.PlatformEnvironment;
import net.labymod.api.util.ColorUtil;
import net.labymod.api.util.math.MathHelper;
import rgbaddon.core.RgbAddon;

public class TNTTimeTag extends NameTag {

  private final RgbAddon addon;

  private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

  private final MinecraftOptions options;

  public TNTTimeTag(RgbAddon addon) {
    this.addon = addon;
    this.options = addon.labyAPI().minecraft().options();
  }

  private TextColor getColor() {
    if (!this.addon.configuration().tntTimerSubSetting.colored()) {
      return NamedTextColor.WHITE;
    }

    int green = MathHelper.clamp(255 * ((PrimedTnt) this.entity).getFuse() / 80, 0, 255);
    return TextColor.color(255 - green, green, 0);
  }

  private String getTag() {
    float number = ((PrimedTnt) this.entity).getFuse()  / 20F;
    if (number < 0) {
      return null;
    }
    return this.decimalFormat.format(number);
  }

  @Override
  public void render(Stack stack, Entity entity) {
    GFXBridge gfx = this.addon.labyAPI().gfxRenderPipeline().gfx();

    gfx.storeBlaze3DStates();
    gfx.depthMask(false);

    if (!PlatformEnvironment.isAncientOpenGL()) {
      gfx.enableDepth();
    }

    gfx.enableBlend();
    gfx.defaultBlend();

    //this.renderBackground(stack);
    gfx.depthMask(true);

    if (PlatformEnvironment.isAncientOpenGL()) {
      gfx.enableDepth();
    }

    this.renderText(stack, this.getRenderableComponent(), false, 0x20FFFFFF, 0x3B3B3B, 1, 0.5F);
    this.renderText(stack, this.getRenderableComponent(), false, -1, 0x3B3B3B, 1, 0.5F);

    gfx.restoreBlaze3DStates();
  }

  @Override
  protected RenderableComponent getRenderableComponent() {
    if (!(this.entity instanceof PrimedTnt)) {
      return null;
    }
    String tag = this.getTag();
    if (tag == null) {
      return null;
    }

    return RenderableComponent.of(
        Component
            .text(tag)
            .color(this.getColor()));
  }
}