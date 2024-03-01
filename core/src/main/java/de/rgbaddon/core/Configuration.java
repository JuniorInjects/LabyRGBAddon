package de.rgbaddon.core;

import de.rgbaddon.core.settings.sub.CopyChatSubSetting;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;

@ConfigName("settings")
@SpriteTexture("sprite.png")
public class Configuration extends AddonConfig {

  @SpriteSlot(x = 2, y = 1)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SettingSection("chat")
  @SpriteSlot(x = 1, y = 3)
  public CopyChatSubSetting copyChatSubSetting = new CopyChatSubSetting();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }
}
