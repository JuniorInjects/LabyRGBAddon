package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import rgbaddon.core.imports.enums.NameTagLocation;

public class FriendTagSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @TextFieldSetting
  private final ConfigProperty<String> format = new ConfigProperty<>("&lFriend");

  @DropdownSetting
  private final ConfigProperty<NameTagLocation> location = new ConfigProperty<>(NameTagLocation.ABOVE_NAME);

  @SwitchSetting
  private final ConfigProperty<Boolean> renderBackground = new ConfigProperty<>(Boolean.TRUE);

  public boolean isEnabled() {
    return enabled.get();
  }

  public String getFormat() {
    return format.get();
  }

  public NameTagLocation getLocation() {
    return location.get();
  }

  public boolean background() {
    return this.renderBackground.get();
  }
}
