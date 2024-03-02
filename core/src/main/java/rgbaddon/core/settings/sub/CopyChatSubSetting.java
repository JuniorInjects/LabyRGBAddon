package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.color.ColorPickerWidget.ColorPickerSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import java.awt.*;
import java.security.Key;

public class CopyChatSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> copyPlayerName = new ConfigProperty<>(true);

  public boolean copyPlayerName() {
    return this.copyPlayerName.get();
  }

  public boolean isEnabled() {
    return this.enabled.get();
  }
}
