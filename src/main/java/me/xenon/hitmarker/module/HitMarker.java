package me.xenon.hitmarker.module;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import me.xenon.hitmarker.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HitMarker implements Wrapper {
    private static final List<HitMarkerText> hitMarker = new ArrayList<>();

    @SubscribeEvent
    public void onEntityHit(LivingAttackEvent event) {
        if (event.getEntity() instanceof LivingEntity && event.getSource().getTrueSource() instanceof PlayerEntity) {
            LivingEntity target = (LivingEntity) event.getEntity();

            double x = target.getPosX();
            double y = target.getPosY() + target.getHeight() / 1.5;
            double z = target.getPosZ();

            int damage = (int) event.getAmount();
            if (damage > 0) hitMarker.add(new HitMarkerText(x, y, z, "-" + damage + ".0"));
        }
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        MatrixStack matrixStack = event.getMatrixStack();
        matrixStack.push();

        double cameraX = mc.gameRenderer.getActiveRenderInfo().getProjectedView().x;
        double cameraY = mc.gameRenderer.getActiveRenderInfo().getProjectedView().y;
        double cameraZ = mc.gameRenderer.getActiveRenderInfo().getProjectedView().z;

        matrixStack.translate(-cameraX, -cameraY, -cameraZ);

        RenderSystem.depthMask(false);

        Iterator<HitMarkerText> iterator = hitMarker.iterator();
        while (iterator.hasNext()) {
            HitMarkerText hitMarkerText = iterator.next();
            if (hitMarkerText.isExpired()) {
                iterator.remove();
            } else {
                render(matrixStack, hitMarkerText);
            }
        }

        RenderSystem.depthMask(true);

        matrixStack.pop();
    }

    private void render(MatrixStack matrixStack, HitMarkerText hitMarkerText) {

        float alpha = 1.0f;

        matrixStack.push();
        matrixStack.translate(hitMarkerText.x, hitMarkerText.y, hitMarkerText.z);
        EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();
        matrixStack.rotate(renderManager.getCameraOrientation());
        matrixStack.scale(-0.05F, -0.05F, 0.05F);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderSystem.alphaFunc(GL11.GL_GREATER, 0.01f);

        mc.fontRenderer.drawString(matrixStack, hitMarkerText.text, 10.0f, 1.0f, new Color(0xE24141).hashCode() & 0x00FFFFFF | ((int)(alpha * 255) << 24));

        RenderSystem.disableBlend();

        matrixStack.pop();
    }

    private static class HitMarkerText {
        final double x, y, z;
        final String text;
        final long creationTime;

        HitMarkerText(double x, double y, double z, String text) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.text = text;
            this.creationTime = System.currentTimeMillis();
        }

        boolean isExpired() {
            return System.currentTimeMillis() - creationTime > 3000;
        }

        long getAge() {
            return System.currentTimeMillis() - creationTime;
        }
    }
}
