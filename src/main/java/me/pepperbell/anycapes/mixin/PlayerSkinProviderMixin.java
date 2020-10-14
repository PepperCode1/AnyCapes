package me.pepperbell.anycapes.mixin;

import java.io.File;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.blaze3d.systems.RenderSystem;

import me.pepperbell.anycapes.cape.AbstractCapeProvider;
import me.pepperbell.anycapes.mixinduck.PlayerSkinProviderAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.PlayerSkinProvider;
import net.minecraft.client.texture.TextureManager;

@Mixin(PlayerSkinProvider.class)
public abstract class PlayerSkinProviderMixin implements PlayerSkinProviderAccess {
	@Unique
	private AbstractCapeProvider capeProvider;
	
	@Override
	public AbstractCapeProvider getCapeProvider() {
		return capeProvider;
	}

	@Override
	public void setCapeProvider(AbstractCapeProvider capeProvider) {
		this.capeProvider = capeProvider;
	}

	@Override
	@Accessor("textureManager")
	public abstract TextureManager getTextureManager();

	@Override
	@Accessor("skinCacheDir")
	public abstract File getSkinCacheDir();
	
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/MinecraftClient;execute(Ljava/lang/Runnable;)V"
		),
		method = "method_4653(Lcom/mojang/authlib/GameProfile;ZLnet/minecraft/client/texture/PlayerSkinProvider$SkinTextureAvailableCallback;)V",
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	public void onLoadSkinRunnable(GameProfile profile, boolean requireSecure, PlayerSkinProvider.SkinTextureAvailableCallback callback, CallbackInfo ci, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map) {
		MinecraftClient.getInstance().execute(() -> {
			RenderSystem.recordRenderCall(() -> {
				if (capeProvider != null) {
					capeProvider.loadCape(profile, map.remove(Type.CAPE), callback);
				}
			});
		});
	}
}
