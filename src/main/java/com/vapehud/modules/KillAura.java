package com.vapehud.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class KillAura extends Module {
    public KillAura() { super("Killaura", "Combat"); }
    private int tickDelay = 0;

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;
        if (tickDelay++ < 10) return;
        tickDelay = 0;

        mc.world.getEntities().forEach(e -> {
            if (!(e instanceof LivingEntity target)) return;
            if (e == mc.player) return;
            if (e instanceof PlayerEntity p && p.isCreative()) return;
            double dist = mc.player.distanceTo(e);
            if (dist > 4.0) return;
            mc.player.lookAt(net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.EYES,
                e.getPos());
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        });
    }
}
