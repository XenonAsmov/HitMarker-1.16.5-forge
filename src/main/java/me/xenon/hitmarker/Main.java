package me.xenon.hitmarker;

import me.xenon.hitmarker.module.KeyInput;
import net.minecraftforge.fml.common.Mod;

@Mod("example")
public class Main implements Wrapper {

    public Main() {

        EVENT_BUS.register(new KeyInput());
        EVENT_BUS.register(this);

    }
}