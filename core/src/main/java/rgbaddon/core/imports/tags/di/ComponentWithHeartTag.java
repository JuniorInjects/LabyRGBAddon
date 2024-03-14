package rgbaddon.core.imports.tags.di;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.entity.Entity;
import net.labymod.api.client.entity.LivingEntity;
import net.labymod.api.client.entity.player.tag.tags.NameTag;
import net.labymod.api.client.render.RenderPipeline;
import net.labymod.api.client.render.draw.ResourceRenderer;
import net.labymod.api.client.render.font.RenderableComponent;
import net.labymod.api.client.render.matrix.Stack;
import rgbaddon.core.Configuration;
import rgbaddon.core.RgbAddon;
import rgbaddon.core.imports.enums.DamageDisplayType;

public abstract class ComponentWithHeartTag extends NameTag {

  private final RenderPipeline renderPipeline;
  private final ResourceRenderer resourceRenderer;
  private final Configuration configuration;
  private final DamageDisplayType displayType;
  private float startX;

  protected ComponentWithHeartTag(RgbAddon addon, DamageDisplayType displayType) {
    this.renderPipeline = addon.labyAPI().renderPipeline();
    this.resourceRenderer = this.renderPipeline.resourceRenderer();
    this.configuration = addon.configuration();
    this.displayType = displayType;
  }

  @Override
  public void render(Stack stack, Entity entity) {
    super.render(stack, entity);
    RenderPipeline renderPipeline = this.renderPipeline;
    renderPipeline.renderNoneStandardNameTag(entity, () -> {
          LivingEntity livingEntity = (LivingEntity) entity;
          this.resourceRenderer.entityHeartRenderer(livingEntity).renderHealthBar(
              stack,
              this.startX,
              this.getHeight() / 2 - 4,
              8,
              2,
              2
          );
        }
    );
  }

  @Override
  protected RenderableComponent getRenderableComponent() {
    if (!(this.entity instanceof LivingEntity) || this.entity.isCrouching()
        || !this.configuration.damageIndicatorSubSetting.isVisible(this.displayType)) {
      return null;
    }

    RenderableComponent renderableComponent = RenderableComponent.of(
        this.component((LivingEntity) this.entity)
    );

    this.startX = renderableComponent.getWidth() + 2;
    return renderableComponent;
  }

  @Override
  public float getWidth() {
    return super.getWidth() + 9;
  }

  @Override
  public float getScale() {
    return .7F;
  }

  @Override
  protected boolean withDepthTest() {
    return false;
  }

  protected abstract Component component(LivingEntity livingEntity);
}