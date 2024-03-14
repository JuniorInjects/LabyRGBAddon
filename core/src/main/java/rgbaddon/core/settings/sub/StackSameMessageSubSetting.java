package rgbaddon.core.settings.sub;

import java.awt.*;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import rgbaddon.core.imports.enums.AddonIconLocation;

public class StackSameMessageSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @TextFieldSetting
  private final ConfigProperty<String> text = new ConfigProperty<>(" &r[x%amount%]");

  @SliderSetting(min = 2, max = 25)
  private final ConfigProperty<Integer> amount = new ConfigProperty<>(3);

  @SwitchSetting
  private final ConfigProperty<Boolean> serverSwitchReset = new ConfigProperty<>(true);

  public ConfigProperty<String> text() { return this.text; }

  public int amount() {
    return this.amount.get();
  }

  public ConfigProperty<Boolean> serverSwitchReset() { return this.serverSwitchReset; }

  public boolean isEnabled() {
    return this.enabled.get();
  }
}