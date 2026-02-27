package com.vapehud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import com.vapehud.modules.ModuleBase;
import java.util.*;

public class HudRenderer {
    private static boolean visible = false;

    private static final int BG_COLOR      = 0xCC0D0D0D;
    private static final int ACCENT_COLOR  = 0xFFE83333;
    private static final int HEADER_COLOR  = 0xFFE83333;
    private static final int TEXT_COLOR    = 0xFFFFFFFF;
    private static final int ENABLED_BG    = 0xAAE83333;
    private static final int DISABLED_TEXT = 0xFF888888;

    private static final int COL_W  = 140;
    private static final int COL_H  = 18;
    private static final int HEADER = 20;
    private static final int PAD    = 6;

    public static void toggle() { visible = !visible; }
    public static boolean isVisible() { return visible; }

    public static void register() {
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            if (!visible) return;
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.options.hudHidden) return;

            Map<String, List<ModuleBase>> byCategory = new LinkedHashMap<>();
            for (ModuleBase m : ModuleManager.getModules()) {
                byCategory.computeIfAbsent(m.getCategory(), k -> new ArrayList<>()).add(m);
            }

            int startX = 10;
            int startY = 10;
            int col = 0;

            for (Map.Entry<String, List<ModuleBase>> entry : byCategory.entrySet()) {
                String cat = entry.getKey();
                List<ModuleBase> mods = entry.getValue();
                int x = startX + col * (COL_W + PAD);
                int y = startY;

                int panelH = HEADER + mods.size() * COL_H + 4;
                context.fill(x, y, x + COL_W, y + panelH, BG_COLOR);
                context.fill(x, y, x + 3, y + panelH, ACCENT_COLOR);
                context.fill(x + 3, y, x + COL_W, y + HEADER, 0xCC1A1A1A);
                drawCenteredText(context, client, cat.toUpperCase(),
                    x + 3 + (COL_W - 3) / 2, y + 5, HEADER_COLOR);
                context.fill(x + 3, y + HEADER, x + COL_W, y + HEADER + 1, ACCENT_COLOR);

                int mY = y + HEADER + 2;
                for (ModuleBase m : mods) {
                    if (m.isEnabled()) {
                        context.fill(x + 3, mY, x + COL_W, mY + COL_H - 1, ENABLED_BG);
                        drawText(context, client, m.getName(), x + 8, mY + 4, TEXT_COLOR);
                    } else {
                        drawText(context, client, m.getName(), x + 8, mY + 4, DISABLED_TEXT);
                    }
                    mY += COL_H;
                }
                col++;
            }
        });
    }

    private static void drawText(DrawContext ctx, MinecraftClient mc,
                                  String text, int x, int y, int color) {
        ctx.drawText(mc.textRenderer, text, x, y, color, false);
    }

    private static void drawCenteredText(DrawContext ctx, MinecraftClient mc,
                                          String text, int cx, int y, int color) {
        int w = mc.textRenderer.getWidth(text);
        ctx.drawText(mc.textRenderer, text, cx - w / 2, y, color, false);
    }
}
