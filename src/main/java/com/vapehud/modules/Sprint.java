package com.vapehud.modules;

import net.minecraft.client.MinecraftClient;

public class Sprint extends Module {
    public Sprint() { super("Sprint", "Combat"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        if (mc.player.forwardSpeed > 0) {
            mc.player.setSprinting(true);
        }
    }
}
