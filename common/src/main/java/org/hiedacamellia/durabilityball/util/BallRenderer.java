package org.hiedacamellia.durabilityball.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.hiedacamellia.durabilityball.ClientConfig;
import org.hiedacamellia.durabilityball.DurabilityBall;

public class BallRenderer {

    private static final ResourceLocation T = ResourceLocation.fromNamespaceAndPath(DurabilityBall.MOD_ID, "textures/gui/0.png");

    private static int lastDamageValue = -1;

    public static void renderBall(ItemStack itemStack, GuiGraphics guiGraphics, int i, int j) {
        int maxDamage = itemStack.getMaxDamage();
        int damageValue = itemStack.getDamageValue();
        float percent = (float)damageValue/(float)maxDamage;
        long time = Minecraft.getInstance().level.getGameTime()%1200;

        ClientConfig config = AutoConfig.getConfigHolder(ClientConfig.class).getConfig();

        float t=0F;
        if(lastDamageValue != damageValue){
            lastDamageValue = damageValue;
            if(config.shakeWhenDurabilityChange)
                t=config.shakeRange;
        }

        PoseStack pose = guiGraphics.pose();
        pose.pushPose();
        pose.translate(i,j,300);
        pose.translate(1.2,1.2+t,0);

        switch (config.position){
            case TOP_LEFT:
                break;
            case TOP_RIGHT:
                pose.translate(10.6,0,0);
                break;
            case BOTTOM_LEFT:
                pose.translate(0,10.6,0);
                break;
            case BOTTOM_RIGHT:
                pose.translate(10.6,10.6,0);
                break;
        }
        int color = itemStack.getBarColor() + config.colorShift;
        float red = ((color >> 16) & 0xFF) / 255f;
        float green = ((color >> 8) & 0xFF) / 255f;
        float blue = ((color) & 0xFF) / 255f;
        if(percent>0.95 && time%20<10){
            red +=0.4f;
            green +=0.4f;
            blue +=0.4f;
        }

        RenderSystem.setShaderColor(red, green, blue, 1);
        guiGraphics.blit(T,  0,  0, 0, 0, 3, 3, 3, 3);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if(itemStack.isEnchanted()&&config.enableEnchantLightWhenLow){
            RenderSystem.enableBlend();
            RenderSystem.enableDepthTest();
            guiGraphics.blit(TextureUtil.getTexture((int) ((time/3)%7)),  0,  0, 0, 0, 3, 3, 3, 3);
        }
        pose.popPose();

    }
}
