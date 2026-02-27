package com.vapehud.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class KillAura extends ModuleBase {
    public KillAura() { super("Killaura", "Combat"); }
    private int tickDelay = 0;

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;
        if (tickDelay++ < 10) return;
        tickDelay = 0;

        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof LivingEntity target)) continue;
            if (e == mc.player) continue;
            if (e instanceof PlayerEntity p && p.isCreative()) continue;
            if (mc.player.distanceTo(e) > 4.0) continue;

            Vec3d targetPos = new Vec3d(e.getX(), e.getY() + e.getEyeHeight(e.getPose()) / 2, e.getZ());
            mc.player.lookAt(
                net.minecraft.command.argument.EntityAnchorArgumentType.EntityAnchor.EYES,
                targetPos
            );
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
            break;
        }
    }
}
