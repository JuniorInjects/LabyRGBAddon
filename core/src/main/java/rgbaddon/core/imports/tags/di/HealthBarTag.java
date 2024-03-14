package rgbaddon.core.imports.tags.di;

import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.LivingEntity;
import net.labymod.api.client.entity.player.tag.renderer.AbstractTagRenderer;
import net.labymod.api.client.render.RenderPipeline;
import net.labymod.api.client.render.draw.ResourceRenderer;
import net.labymod.api.client.render.matrix.Stack;
import rgbaddon.core.Configuration;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.enums.DamageDisplayType;

/**
 * The damage indicator health bar tag renderer.
 */
public final class HealthBarTag extends AbstractTagRenderer {

  private final RenderPipeline renderPipeline;
  private final ResourceRenderer resourceRenderer;
  private final Configuration configuration;

  public HealthBarTag(RgbAddon addon) {
    this.renderPipeline = addon.labyAPI().renderPipeline();
    this.resourceRenderer = this.renderPipeline.resourceRenderer();
    this.configuration = addon.configuration();
  }

  @Override
  public void render(Stack stack, Entity entity) {
    RenderPipeline renderPipeline = this.renderPipeline;
    renderPipeline.renderNoneStandardNameTag(entity, () ->
        this.resourceRenderer.entityHeartRenderer((LivingEntity) entity)
            .renderHealthBar(stack, 0, 0, 16)
    );
  }

  @Override
  public boolean isVisible() {
    return this.entity instanceof LivingEntity && !this.entity.isCrouching()
        && this.configuration.damageIndicatorSubSetting.isVisible(DamageDisplayType.HEALTH_BAR);
  }

  @Override
  public float getWidth() {
    return this.resourceRenderer.entityHeartRenderer((LivingEntity) this.entity).getWidth(16);
  }

  @Override
  public float getHeight() {
    return 16F;
  }

  @Override
  public float getScale() {
    return 0.4F;
  }
}