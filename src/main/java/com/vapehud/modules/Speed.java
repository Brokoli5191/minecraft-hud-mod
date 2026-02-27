package com.vapehud.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Speed extends ModuleBase {
    public Speed() { super("Speed", "Movement"); }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        mc.player.addStatusEffect(
            new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE, 4, false, false)
        );
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        mc.player.removeStatusEffect(StatusEffects.SPEED);
    }
}
