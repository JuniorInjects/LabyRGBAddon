package rgbaddon.core;

import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget.ButtonSetting;
import net.labymod.api.configuration.settings.Setting;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.Builder;
import net.labymod.api.notification.Notification.Type;
import net.labymod.api.util.I18n;
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
import rgbaddon.core.settings.sub.FriendTagSubSetting;
import rgbaddon.core.settings.sub.TntTimerSubSetting;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@ConfigName("settings")
@SpriteTexture("sprite")
public class Configuration extends AddonConfig {

  @SpriteSlot(x = 5)
  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SpriteSlot(x = 2, y = 6)
  @ButtonSetting
  public void discord(Setting setting) throws URISyntaxException, IOException {
    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
      Desktop.getDesktop().browse(new URI("https://discord.com/invite/J44t7xQFEX"));
    }
    Notification.Builder builder = Notification.builder()
        .title(net.labymod.api.client.component.Component.text(Objects.requireNonNull(I18n.getTranslation("notification.antiRage.title"))))
        .text(Component.text(Objects.requireNonNull(I18n.getTranslation("notification.antiRage.text"))))
        .icon(Icon.head(Laby.labyAPI().minecraft().getClientPlayer().getName()))
        .type(Type.SYSTEM);
    Laby.labyAPI().notificationController().push(builder.build());
  }

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
  @SpriteSlot(x = 7, y = 2)
  public FriendTagSubSetting friendTagSubSetting = new FriendTagSubSetting();
  @SpriteSlot(y = 3)
  public AddonIconSubSetting addonIconSubSetting = new AddonIconSubSetting();


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
