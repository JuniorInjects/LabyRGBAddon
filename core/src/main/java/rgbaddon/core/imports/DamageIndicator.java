package rgbaddon.core.imports;

import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.client.entity.player.tag.TagRegistry;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.tags.di.HealthAmountTag;
import rgbaddon.core.imports.tags.di.HealthBarTag;
import rgbaddon.core.imports.tags.di.HealthPercentageTag;

public class DamageIndicator {

  public void registerTags(RgbAddon addon, TagRegistry tagRegistry) {
    tagRegistry.register(
        "di_healthbar",
        PositionType.ABOVE_NAME,
        new HealthBarTag(addon)
    );

    tagRegistry.register(
        "di_percentage",
        PositionType.ABOVE_NAME,
        new HealthPercentageTag(addon)
    );

    tagRegistry.register(
        "di_amount",
        PositionType.ABOVE_NAME,
        new HealthAmountTag(addon)
    );
  }
}
