package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class TntTimerSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> colored = new ConfigProperty<>(true);

  public boolean colored() {
    return this.colored.get();
  }

  public boolean isEnabled() {
    return this.enabled.get();
  }
}
