package rgbaddon.core.widgets;

import net.labymod.api.Laby;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.client.gui.hud.binding.category.HudWidgetCategory;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.util.I18n;
import java.util.Arrays;
import java.util.List;

public class NearbyWidget extends TextHudWidget<TextHudWidgetConfig> {

  private String name = "";
  private TextLine line;

  public NearbyWidget() {
    super("nearby");
    this.name = Laby.labyAPI().getName();
    this.bindCategory(HudWidgetCategory.INGAME);
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    this.line = super.createLine("rgbaddon.nearby.nearby", name);
  }

  @Override
  public void onTick(boolean isEditorContext) {
    name = getPlayersList();
    this.line.updateAndFlush(name);
  }

  private String getPlayersList() {
    StringBuilder list = new StringBuilder();
    outerLoop: for (Player player : Laby.labyAPI().minecraft().clientWorld().getPlayers()) {

      if(Laby.labyAPI().minecraft().getClientPlayer() != null) {
        if(player.getName().equals(Laby.labyAPI().minecraft().getClientPlayer().getName())) {
          continue;
        }
      }

      int dist = (int)Math.sqrt(player.getDistanceSquared(Laby.labyAPI().minecraft().getClientPlayer()));

      if(dist > 1000) {
        continue;
      }

      list.append("\n").append(player.getName()).append(" (").append(dist).append("m)");
    }

    return list.toString();
  }
}