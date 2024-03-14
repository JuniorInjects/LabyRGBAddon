package rgbaddon.core.imports.tags.di;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.entity.LivingEntity;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.enums.DamageDisplayType;

/**
 * The damage indicator percentage tag renderer.
 */
public class HealthPercentageTag extends ComponentWithHeartTag {

  public HealthPercentageTag(RgbAddon addon) {
    super(addon, DamageDisplayType.PERCENT);
  }

  @Override
  protected Component component(LivingEntity entity) {
    int health = (int) Math.ceil(entity.getHealth());
    int maxHealth = (int) Math.ceil(entity.getMaximalHealth());
    return Component.text(health * 100 / maxHealth + "%");
  }
}
