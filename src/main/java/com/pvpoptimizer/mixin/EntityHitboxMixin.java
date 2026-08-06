package com.pvpoptimizer.mixin;

import com.pvpoptimizer.PVPOptimizerClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityHitboxMixin {

    @Inject(method = "getBoundingBox", at = @At("RETURN"), cancellable = true)
    private void onGetBoundingBox(CallbackInfoReturnable<Box> cir) {
        double factor = PVPOptimizerClient.getScaleFactor();
        if (factor != 1.0) {
            Box originalBox = cir.getReturnValue();
            if (originalBox != null) {
                double expandAmount = (factor - 1.0) * 0.5;
                Box modifiedBox = originalBox.expand(expandAmount);
                cir.setReturnValue(modifiedBox);
            }
        }
    }
}
