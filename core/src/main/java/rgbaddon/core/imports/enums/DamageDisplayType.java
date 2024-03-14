package rgbaddon.core.imports.enums;

import net.labymod.api.client.entity.player.tag.PositionType;

public enum DamageDisplayType {
  HEALTH_BAR("Health Bar [ <3<3<3 ]"),
  AMOUNT("Amount [ 10/20 ]"),
  PERCENT("Percent [ 100% ]");

  private final String display;

  DamageDisplayType(String display) {
    this.display = display;
  }

  @Override
  public String toString() {
    return this.display;
  }
}