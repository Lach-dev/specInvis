package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class PlayerRenderer implements ModInitializer
{
	public static final Logger LOGGER = LoggerFactory.getLogger("specInvis");


	@Override
	public void onInitialize()
	{
		MixinBootstrap.init();
		Mixins.addConfiguration("modid.mixins.json");

		KeyBinding toggle = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle specInvis", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "specInvis"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (toggle.wasPressed()) {
				mixinToggleHelper.setMixinEnabled(!mixinToggleHelper.isMixinEnabled());
				//LOGGER.info("SpecInvis mixin toggled: " + mixinToggleHelper.isMixinEnabled());
				// tell player that specInvis is enabled/disabled
				if (mixinToggleHelper.isMixinEnabled()) {
					client.player.sendMessage(net.minecraft.text.Text.of("[SpecInvis] enabled"), false);
				} else {
					client.player.sendMessage(net.minecraft.text.Text.of("[SpecInvis] disabled"), false);
				}
			}
		});


		LOGGER.info("[specInvis] loaded!");

	}

}
