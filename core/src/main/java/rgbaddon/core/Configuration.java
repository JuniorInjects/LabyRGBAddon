package rgbaddon.core;

import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.models.OperatingSystem;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.Builder;
import net.labymod.api.notification.Notification.Type;
import net.labymod.api.util.I18n;
import net.labymod.api.util.MethodOrder;
import rgbaddon.core.settings.sub.AddonIconSubSetting;
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
import rgbaddon.core.settings.sub.DamageIndicatorSubSetting;
import rgbaddon.core.settings.sub.FriendTagSubSetting;
import rgbaddon.core.settings.sub.GGCoinflipSubSetting;
import rgbaddon.core.settings.sub.ItemPhysicsSubSetting;
import rgbaddon.core.settings.sub.StackSameMessageSubSetting;
import rgbaddon.core.settings.sub.TntTimerSubSetting;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@ConfigName("settings")
@SpriteTexture("sprite")
public class Configuration extends AddonConfig {

  @SpriteSlot(x = 5)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SpriteSlot(x = 3)
  @MethodOrder(after = "enabled")
  @ButtonSetting
  public void openDiscord() {
    OperatingSystem.getPlatform().openUrl("https://discord.com/invite/J44t7xQFEX");
  }

  @SpriteSlot(x = 2, y = 3)
  @MethodOrder(after = "openDiscord")
  @ButtonSetting
  public void downloadLatest() {
    try {
      final URL url = new URL("https://pastebin.com/raw/PMgJWKTk");
      final Scanner s = new Scanner(url.openStream());
      String finalUrl = s.nextLine();
      System.out.println(finalUrl);
      OperatingSystem.getPlatform().openUrl(finalUrl);
    } catch (IOException ignored) {
    }
  }

  @SettingSection("chat")
  @SpriteSlot(x = 2)
  public CopyChatSubSetting copyChatSubSetting = new CopyChatSubSetting();
  @SpriteSlot(x = 1, y = 2)
  @SwitchSetting
  public ChatTimeSubSetting chatTime = new ChatTimeSubSetting();
  //@SpriteSlot(x = 6, y = 2)
  //private final ConfigProperty<Boolean> antiChatClear = new ConfigProperty<>(true);
  @SpriteSlot(y = 2)
  public StackSameMessageSubSetting stackSameMessageSubSetting = new StackSameMessageSubSetting();
  @SpriteSlot(x = 1, y = 1)
  @SwitchSetting
  public AntiRageSubSetting antiRage = new AntiRageSubSetting();

  @SettingSection("render")
  @SpriteSlot(x = 4, y = 2)
  public TntTimerSubSetting tntTimerSubSetting = new TntTimerSubSetting();
  //@SpriteSlot(x = 2, y = 2)
  //@SwitchSetting
  //private final ConfigProperty<Boolean> fullbright = new ConfigProperty<>(true);
  @SpriteSlot(x = 7, y = 2)
  public FriendTagSubSetting friendTagSubSetting = new FriendTagSubSetting();
  //@SpriteSlot(y = 3)
  //public AddonIconSubSetting addonIconSubSetting = new AddonIconSubSetting();
  @SpriteSlot(x = 3, y = 2)
  public DamageIndicatorSubSetting damageIndicatorSubSetting = new DamageIndicatorSubSetting();

  @SettingSection("servers")
  @SpriteSlot(x = 1, y = 3)
  public GGCoinflipSubSetting ggCoinflipSubSetting = new GGCoinflipSubSetting();

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  //public boolean getAntiChatClear() {
  //  return this.antiChatClear.get();
  //}

  //public boolean getFullbright() {
  //  return this.fullbright.get();
  //}
}
