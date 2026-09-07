package com.hyperframe.command;

import com.hyperframe.HyperFrame;
import com.hyperframe.ui.HyperFrameScreen;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public final class HyperFrameCommand {

    private HyperFrameCommand() {
    }

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register(
                HyperFrameCommand::registerCommand
        );
    }

    private static void registerCommand(
            CommandDispatcher<net.minecraft.client.network.ClientCommandSource> dispatcher,
            CommandRegistryAccess registryAccess
    ) {
        dispatcher.register(
                literal("hyperframe")
                        .executes(context -> {
                            MinecraftClient client =
                                    MinecraftClient.getInstance();

                            client.setScreen(
                                    new HyperFrameScreen()
                            );

                            return 1;
                        })
                        .then(
                                literal("boost")
                                        .executes(context -> {
                                            MinecraftClient client =
                                                    MinecraftClient.getInstance();

                                            HyperFrame.BOOST.enable(client);

                                            if (client.player != null) {
                                                client.player.sendMessage(
                                                        Text.literal(
                                                                "⚡ HyperBoost activated!"
                                                        ),
                                                        true
                                                );
                                            }

                                            return 1;
                                        }
                        )
        );
    }
}
