package com.github.einjerjar.mc.widgets2;

import com.github.einjerjar.mc.widgets.utils.Point;
import com.github.einjerjar.mc.widgets.utils.Rect;
import com.github.einjerjar.mc.widgets.utils.Tooltipped;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
public abstract class EWidget2Utils implements Renderable, GuiEventListener, NarratableEntry, Tooltipped {
    protected final Font font = Minecraft.getInstance().font;

    @Getter
    @Setter
    protected Rect rect;

    @Getter
    @Setter
    protected boolean visible = true;

    @Getter
    @Setter
    protected boolean enabled = true;

    @Getter
    @Setter
    protected boolean focused = false;

    @Getter
    @Setter
    protected boolean active = false;

    @Getter
    @Setter
    protected boolean hovered = false;

    @Getter
    @Setter
    protected boolean focusable = false;

    @Getter
    @Setter
    protected int color = 0xFFFFFF;

    @Getter
    @Setter
    protected int transparency = 0xFF_000000;

    @Getter
    @Setter
    protected List<Component> tooltips = new ArrayList<>();

    @Getter
    @Setter
    protected Point<Integer> padding = new Point<>(0);

    protected EWidget2Utils(int x, int y, int w, int h) {
        this.rect = new Rect(x, y, w, h);
    }

    protected EWidget2Utils(@NotNull Rect rect) {
        this.rect = rect;
    }

    protected int tColor(int t) {
        return t | color;
    }

    protected int tColor() {
        return tColor(transparency);
    }

    protected void drawOutline(@NotNull GuiGraphics guiGraphics, int l, int t, int r, int b, int c) {
        U.outline(guiGraphics, l, t, r, b, c);
    }

    protected void drawOutline(@NotNull GuiGraphics guiGraphics, Rect r, int c) {
        drawOutline(guiGraphics, r.left(), r.top(), r.right(), r.bottom(), c);
    }

    protected void drawOutline(@NotNull GuiGraphics guiGraphics, int c) {
        drawOutline(guiGraphics, rect, c);
    }

    protected Point<Integer> center() {
        return new Point<>((rect.left() + rect.right()) / 2, (rect.top() + rect.bottom()) / 2);
    }

    @Override
    public void setFocused(boolean focused) {
        focused(focused);
    }

    @Override
    public boolean isFocused() {
        return focused();
    }

    public WidgetState state() {
        if (!enabled()) return WidgetState.DISABLED;
        if (active()) return WidgetState.ACTIVE;
        if (hovered()) return WidgetState.HOVER;
        if (focused()) return WidgetState.FOCUS;
        return WidgetState.BASE;
    }

    protected void playSound(SoundEvent sound, float pitch, float volume) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(sound, pitch, volume));
    }

    protected void playSound(SoundEvent sound, float pitch) {
        playSound(sound, pitch, 0.25f);
    }

    protected void playSound(SoundEvent sound) {
        playSound(sound, 1f);
    }

    @Override
    public List<Component> getTooltips() {
        return tooltips;
    }

    public void setTooltip(@NotNull Component tip) {
        tooltips.clear();
        tooltips.add(tip);
    }

    public void setTooltips(@NotNull List<Component> tips) {
        tooltips = tips;
    }

    @Override
    public @NotNull NarrationPriority narrationPriority() {
        return NarrationPriority.NONE;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput narrationElementOutput) {}
}
