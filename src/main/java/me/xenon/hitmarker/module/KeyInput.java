package me.xenon.hitmarker.module;

import me.xenon.hitmarker.Wrapper;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class KeyInput implements Wrapper {
    private HitMarker hitMarker;

    public KeyInput() {
        hitMarker = new HitMarker();
        EVENT_BUS.register(hitMarker);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (event.getKey() == GLFW.GLFW_KEY_F7 && event.getAction() == GLFW.GLFW_PRESS) {
            if (hitMarker == null) {
                hitMarker = new HitMarker();
                EVENT_BUS.register(hitMarker);
            }
        }

        if (event.getKey() == GLFW.GLFW_KEY_F8 && event.getAction() == GLFW.GLFW_PRESS) {
            if (hitMarker != null) {
                EVENT_BUS.unregister(hitMarker);
                hitMarker = null;
            }
        }
    }
}
