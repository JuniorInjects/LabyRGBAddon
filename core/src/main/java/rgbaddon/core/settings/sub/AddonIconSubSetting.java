package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import rgbaddon.core.imports.AddonIconLocation;
import rgbaddon.core.imports.NameTagLocation;
import java.awt.*;

public class AddonIconSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @DropdownSetting
  private final ConfigProperty<AddonIconLocation> location = new ConfigProperty<>(AddonIconLocation.RIGHT_OF_NAME);

  @SliderSetting(steps = 1, min = 5, max = 10)
  private final ConfigProperty<Integer> size = new ConfigProperty<>(8);

  @SwitchSetting
  private final ConfigProperty<Boolean> customColor = new ConfigProperty<>(false);

  @ColorPickerSetting
  private final ConfigProperty<Integer> iconColor = new ConfigProperty<>(Color.YELLOW.getRGB());

  public AddonIconLocation location() {
    return this.location.get();
  }
  public Integer size() {
    return this.size.get();
  }
  public boolean rgbEnabled() {
    return customColor.get();
  }

  public int getColor() {
    return iconColor.get();
  }

  public boolean isEnabled() {
    return this.enabled.get();
  }
}