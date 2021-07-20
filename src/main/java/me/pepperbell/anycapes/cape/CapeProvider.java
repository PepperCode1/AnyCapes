package me.pepperbell.anycapes.cape;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;

import net.minecraft.client.texture.PlayerSkinProvider.SkinTextureAvailableCallback;

public interface CapeProvider {
	void loadCape(GameProfile gameProfile, MinecraftProfileTexture mojangCape, SkinTextureAvailableCallback callback);
}
