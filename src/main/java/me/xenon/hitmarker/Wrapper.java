package me.xenon.hitmarker;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.EventBus;

public interface Wrapper {
    Minecraft mc = Minecraft.getInstance();
    EventBus EVENT_BUS = (EventBus) MinecraftForge.EVENT_BUS;
}
