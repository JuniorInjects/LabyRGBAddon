package rgbaddon.core;

import rgbaddon.core.settings.sub.AntiRageSubSetting;
import rgbaddon.core.settings.sub.ChatTimeSubSetting;
import rgbaddon.core.settings.sub.CopyChatSubSetting;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.annotation.SpriteSlot;
import net.labymod.api.configuration.loader.annotation.SpriteTexture;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.configuration.settings.annotation.SettingSection;
import rgbaddon.core.settings.sub.TntTimerSubSetting;

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
  @SpriteSlot(x = 6, y = 2)
  private final ConfigProperty<Boolean> antiChatClear = new ConfigProperty<>(true);
  @SpriteSlot(y = 2)
  private final ConfigProperty<Boolean> stackSameMessage = new ConfigProperty<>(true);
  @SpriteSlot(x = 1, y = 1)
  @SwitchSetting
  public AntiRageSubSetting antiRage = new AntiRageSubSetting();

  @SettingSection("render")
  @SpriteSlot(x = 4, y = 2)
  public TntTimerSubSetting tntTimerSubSetting = new TntTimerSubSetting();
  @SpriteSlot(x = 2, y = 2)
  @SwitchSetting
  private final ConfigProperty<Boolean> fullbright = new ConfigProperty<>(true);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public boolean getAntiChatClear() {
    return this.antiChatClear.get();
  }

  public boolean getStackSameMessage() {
    return this.stackSameMessage.get();
  }

  public boolean getFullbright() {
    return this.fullbright.get();
  }
}
