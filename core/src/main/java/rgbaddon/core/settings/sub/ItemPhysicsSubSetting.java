package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class ItemPhysicsSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SliderSetting(min = 0, max = 10)
  private final ConfigProperty<Float> rotationSpeed = new ConfigProperty<>(3.0F);

  public boolean isEnabled() {
    return this.enabled.get();
  }

  public ConfigProperty<Float> rotationSpeed() {
    return this.rotationSpeed;
  }
}
