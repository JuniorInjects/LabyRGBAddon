package rgbaddon.core.imports;

import net.labymod.api.client.entity.player.tag.PositionType;

public enum AddonIconLocation {
  LEFT_OF_NAME("Left side", PositionType.LEFT_TO_NAME),
  RIGHT_OF_NAME("Right side", PositionType.RIGHT_TO_NAME);

  private final String display;
  private final PositionType positionType;

  AddonIconLocation(String display, PositionType positionType) {
    this.display = display;
    this.positionType = positionType;
  }


  @Override
  public String toString() {
    return this.display;
  }

  public static AddonIconLocation getIconTagLocation(PositionType type) {
    for (AddonIconLocation nameTagLocation : values()) {
      if (nameTagLocation.positionType.equals(type)) {
        return nameTagLocation;
      }
    }
    return null;
  }
}