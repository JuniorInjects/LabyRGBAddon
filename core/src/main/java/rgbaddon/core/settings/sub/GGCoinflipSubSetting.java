package rgbaddon.core.settings.sub;

import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.dropdown.DropdownWidget.DropdownSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ParentSwitch;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import rgbaddon.core.imports.enums.NameTagLocation;

public class GGCoinflipSubSetting extends Config {

  @ParentSwitch
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @KeyBindSetting
  private ConfigProperty<Key> keyBind = new ConfigProperty<>(Key.F);

  @TextFieldSetting
  private final ConfigProperty<String> plus = new ConfigProperty<>("1.000$");

  @SliderSetting(min = 1, max = 25, steps = 1)
  private final ConfigProperty<Integer> save = new ConfigProperty<>(5);

  @TextFieldSetting
  private final ConfigProperty<String> max = new ConfigProperty<>("1.000.000$");

  @SwitchSetting
  private final ConfigProperty<Boolean> resetAfterMax = new ConfigProperty<>(true);

  public boolean isEnabled() {
    return enabled.get();
  }

  public int getPlus() {
    try {
      int p = Integer.parseInt(plus.get().replace(".","").replace("$",""));
      return p;
    } catch(NumberFormatException nex) {
      nex.printStackTrace();
    }
    return 1000;
  }

  public Key getKeyBind() {
    return keyBind.get();
  }

  public int getSave() {
    return save.get();
  }

  public int getMax() {
    try {
      int m = Integer.parseInt(max.get().replace(".","").replace("$",""));
      return m;
    } catch(NumberFormatException nex) {
      nex.printStackTrace();
    }
    return 1000000;
  }

  public boolean getResetAfterMax() {
    return resetAfterMax.get();
  }
}
