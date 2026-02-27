package com.vapehud.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module {
    public NoFall() { super("NoFall", "Combat"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        if (mc.player.fallDistance > 2.0f) {
            mc.getNetworkHandler().sendPacket(
                new PlayerMoveC2SPacket.OnGroundOnly(true, mc.player.horizontalCollision)
            );
        }
    }
}
