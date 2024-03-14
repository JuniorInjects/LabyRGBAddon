package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import rgbaddon.core.imports.enums.DamageDisplayType;

public class DamageIndicatorSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @DropdownSetting
  private final ConfigProperty<DamageDisplayType> displayType = ConfigProperty.createEnum(
      DamageDisplayType.HEALTH_BAR);

  public ConfigProperty<DamageDisplayType> displayType() {
    return this.displayType;
  }

  public boolean isVisible(DamageDisplayType value) {
    return this.isEnabled() && this.displayType.get() == value;
  }

  public boolean isEnabled() {
    return this.enabled.get();
  }
}