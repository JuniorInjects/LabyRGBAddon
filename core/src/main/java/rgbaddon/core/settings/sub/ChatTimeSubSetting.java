package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;

public class ChatTimeSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> useBrackets = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> displaySecounds = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> displayMinutes = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> displayHours = new ConfigProperty<>(true);
  @SwitchSetting
  private final ConfigProperty<Boolean> displayDay = new ConfigProperty<>(false);
  @SwitchSetting
  private final ConfigProperty<Boolean> displayMonth = new ConfigProperty<>(false);
  @SwitchSetting
  private final ConfigProperty<Boolean> displayYear = new ConfigProperty<>(false);

  public boolean getBrackets() {
    return this.useBrackets.get();
  }
  public boolean getDisplayDay() {
    return this.displayDay.get();
  }

  public boolean getDisplayHours() {
    return this.displayHours.get();
  }

  public boolean getDisplayMinutes() {
    return this.displayMinutes.get();
  }

  public boolean getDisplayMonth() {
    return this.displayMonth.get();
  }

  public boolean getDisplaySecounds() {
    return this.displaySecounds.get();
  }

  public boolean getDisplayYear() {
    return this.displayYear.get();
  }

  public boolean isEnabled() {
    return this.enabled.get();
  }
}