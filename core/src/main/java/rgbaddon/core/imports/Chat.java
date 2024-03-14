package rgbaddon.core.imports;

import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.component.Component;
import rgbaddon.core.RgbAddon;
import java.util.ArrayList;
import java.util.HashMap;

public class Chat {

  private RgbAddon addon;

  public Chat(RgbAddon addon) {
    this.addon = addon;
  }

  private HashMap<String, ArrayList<ChatMessage>> duplicateMessages = new HashMap<>();
  private HashMap<String, Integer> duplicateMessageCount = new HashMap<>();

  public void clearDuplicateMessages() {
    duplicateMessages.clear();
  }

  public void clearDuplicateMessageCount() {
    duplicateMessageCount.clear();
  }

  public HashMap<String, ArrayList<ChatMessage>> getDuplicateMessages() {
    return duplicateMessages;
  }

  public HashMap<String, Integer> getDuplicateMessageCount() {
    return duplicateMessageCount;
  }

  public void setDuplicateMessages(HashMap<String, ArrayList<ChatMessage>> value) {
    this.duplicateMessages = value;
  }

  public void setDuplicateMessageCount(HashMap<String, Integer> value) {
    this.duplicateMessageCount = value;
  }

  public String getDuplicateText(int count) {
    return addon.configuration().stackSameMessageSubSetting.text().get()
        .replace("%amount%", Integer.toString(count))
        .replace("&", "§");
  }
}