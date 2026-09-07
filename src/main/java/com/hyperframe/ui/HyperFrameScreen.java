package com.hyperframe.ui;

import com.hyperframe.HyperFrame;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class HyperFrameScreen extends Screen {

    public HyperFrameScreen() {
        super(Text.literal("⚡ HyperFrame"));
    }

    @Override
    protected void init() {
        int centerX = width / 2;

        addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("⚡ HyperBoost"),
                        button -> {
                            HyperFrame.BOOST.enable(
                                    client
                            );
                        }
                ).dimensions(
                        centerX - 100,
                        height / 2 - 25,
                        200,
                        20
                ).build()
        );

        addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("Close"),
                        button -> close()
                ).dimensions(
                        centerX - 100,
                        height / 2 + 5,
                        200,
                        20
                ).build()
        );
    }

    @Override
    public void render(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {
        renderBackground(context);

        int centerX = width / 2;

        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.literal("⚡ HYPERFRAME"),
                centerX,
                30,
                0xFFFFFF
        );

        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.literal(
                        "FPS: " +
                        HyperFrame.PERFORMANCE.getFps()
                ),
                centerX,
                60,
                0xFFFFFF
        );

        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.literal(
                        String.format(
                                "Frame: %.2f ms",
                                HyperFrame.PERFORMANCE
                                        .getFrameTime()
                        )
                ),
                centerX,
                75,
                0xFFFFFF
        );

        super.render(
                context,
                mouseX,
                mouseY,
                delta
        );
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
