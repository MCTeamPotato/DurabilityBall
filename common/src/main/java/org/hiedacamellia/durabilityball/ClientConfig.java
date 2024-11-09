package org.hiedacamellia.durabilityball;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = DurabilityBall.MOD_ID)
public class ClientConfig implements ConfigData {

    @Comment("The position of the durability ball.(TOP_LEFT,TOP_RIGHT,BOTTOM_LEFT,BOTTOM_RIGHT)")
    public Position position = Position.TOP_LEFT;

    @Comment("Enable the enchantment light when the durability is low.")
    public boolean enableEnchantLightWhenLow = true;

    @Comment("The ball shake when the durability changes.")
    public boolean shakeWhenDurabilityChange = true;

    @Comment("The range of ball shake when the durability changes.")
    public float shakeRange = 0.4F;

    @Comment("The color shift of the ball.")
    public int colorShift = 0;

    public enum Position {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

}