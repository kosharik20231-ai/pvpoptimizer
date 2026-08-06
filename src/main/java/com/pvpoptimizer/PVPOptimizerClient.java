package com.pvpoptimizer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.lwjgl.glfw.GLFW;

public class PVPOptimizerClient implements ClientModInitializer {
    private static double scaleFactor = 1.0;
    private static boolean pPressedState = false;
    private static boolean oPressedState = false;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.getWindow() == null) return;
            long windowHandle = client.getWindow().getHandle();

            // Нажатие P -> увеличение хитбоксов
            boolean pDown = GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_P) == GLFW.GLFW_PRESS;
            if (pDown && !pPressedState) {
                scaleFactor = Math.min(scaleFactor + 0.25, 4.0);
            }
            pPressedState = pDown;

            // Нажатие O -> уменьшение хитбоксов
            boolean oDown = GLFW.glfwGetKey(windowHandle, GLFW.GLFW_KEY_O) == GLFW.GLFW_PRESS;
            if (oDown && !oPressedState) {
                scaleFactor = Math.max(scaleFactor - 0.25, 0.5);
            }
            oPressedState = oDown;
        });
    }

    public static double getScaleFactor() {
        return scaleFactor;
    }
}
