package rgbaddon.core.imports;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.item.PrimedTnt;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.entity.player.tag.tags.NameTag;
import net.labymod.api.client.gfx.GFXBridge;
import net.labymod.api.client.options.MinecraftOptions;
import net.labymod.api.client.render.font.RenderableComponent;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.loader.platform.PlatformEnvironment;
import net.labymod.api.util.math.MathHelper;
import rgbaddon.core.RgbAddon;

public class RgbAddonTag extends NameTag {

  private final RgbAddon addon;

  private final MinecraftOptions options;

  public RgbAddonTag(RgbAddon addon) {
    this.addon = addon;
    this.options = addon.labyAPI().minecraft().options();
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
    if (!(this.entity instanceof Player)) {
      return null;
    }

    try {
      URL url = new URL("https://pastebin.com/raw/f5UHbv7K");
      Scanner s = null;

      s = new Scanner(url.openStream());
      List<String> lines = new ArrayList<>();
      while(s.hasNext()) {
        lines.add(s.nextLine());
      }
      for(String value : lines) {
        String[] lineSplit = value.split(" ");
        StringBuilder tag = new StringBuilder();
        for(String i : lineSplit) {
          if(i.equals(lineSplit[0])) {
            continue;
          }
          tag.append(" ").append(i.replace('&', '§'));
        }
        if(entity.getUniqueId().equals(UUID.fromString(lineSplit[0]))) {
          return RenderableComponent.of(
              Component
                  .text(tag));
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}