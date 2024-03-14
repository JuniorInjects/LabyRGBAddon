package rgbaddon.core.imports.tags.di;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.entity.LivingEntity;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.enums.DamageDisplayType;

/**
 * The damage indicator amount tag renderer.
 */
public class HealthAmountTag extends ComponentWithHeartTag {

  public HealthAmountTag(RgbAddon addon) {
    super(addon, DamageDisplayType.AMOUNT);
  }

  @Override
  protected Component component(LivingEntity entity) {
    double health = Math.ceil(entity.getHealth()) / 2;
    double maxHealth = Math.ceil(entity.getMaximalHealth()) / 2;
    return Component.text(this.formatDouble(health) + "/" + this.formatDouble(maxHealth));
  }

  private String formatDouble(double value) {
    if (value == (int) value) {
      return String.format("%d", (int) value);
    } else {
      return String.format("%s", value);
    }
  }
}
