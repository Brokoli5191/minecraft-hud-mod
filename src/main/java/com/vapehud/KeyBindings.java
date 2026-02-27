package com.vapehud;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyBinding toggleHud;

    public static void register() {
        toggleHud = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.vapehud.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            KeyBinding.Category.MISC
        ));
    }
}
