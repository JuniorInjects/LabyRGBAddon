package rgbaddon.core.imports;

public class ItemPhysics {

  private long lastRenderTime;

  public long getLastRenderTime() {
    return lastRenderTime;
  }

  public float getRotation() {
    return (System.nanoTime() - lastRenderTime) / 100_000_000F;
  }

  public void updateLastRenderTime() {
    lastRenderTime = System.nanoTime();
  }
}
