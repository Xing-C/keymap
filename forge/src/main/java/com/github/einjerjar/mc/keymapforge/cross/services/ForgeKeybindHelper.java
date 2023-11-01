package com.github.einjerjar.mc.keymapforge.cross.services;

import com.github.einjerjar.mc.keymap.cross.services.IKeybindHelper;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class ForgeKeybindHelper implements IKeybindHelper {
    @Override public KeyMapping create(InputConstants.Type type, int code, String name, String category) {
        return new KeyMapping(
                name,
                type,
                code,
                category
        );
    }
}
