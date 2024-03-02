package rgbaddon.core;

import rgbaddon.core.settings.sub.ChatTimeSubSetting;
import rgbaddon.core.settings.sub.CopyChatSubSetting;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
@SpriteTexture("sprite")
public class Configuration extends AddonConfig {

  @SpriteSlot(x = 5)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("chat")
  @SpriteSlot(x = 2)
  public CopyChatSubSetting copyChatSubSetting = new CopyChatSubSetting();
  @SpriteSlot(x = 1, y = 2)
  @SwitchSetting
  public ChatTimeSubSetting chatTime = new ChatTimeSubSetting();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }
}
