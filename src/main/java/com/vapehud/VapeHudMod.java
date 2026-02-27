package com.vapehud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class VapeHudMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBindings.register();
        ModuleManager.init();
        HudRenderer.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindings.toggleHud.wasPressed()) {
                HudRenderer.toggle();
            }
            if (HudRenderer.isVisible()) {
                for (var m : ModuleManager.getEnabled()) {
                    m.onTick();
                }
            }
        });
    }
}
