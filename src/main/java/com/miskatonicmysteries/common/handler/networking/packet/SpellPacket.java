package com.miskatonicmysteries.common.handler.networking.packet;

import com.miskatonicmysteries.common.feature.spell.Spell;
import com.miskatonicmysteries.common.util.Constants;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class SpellPacket {
    public static final Identifier ID = new Identifier(Constants.MOD_ID, "spell");

    public static void send(LivingEntity caster, CompoundTag spellTag, boolean backfires) {
        PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
        data.writeCompoundTag(spellTag);
        data.writeInt(caster.getEntityId());
        data.writeBoolean(backfires);
        PlayerLookup.tracking(caster).forEach(p -> ServerPlayNetworking.send(p, ID, data));
    }

    @Environment(EnvType.CLIENT)
    public static void sendFromClientPlayer(ClientPlayerEntity caster, CompoundTag spellTag) {
        PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
        data.writeCompoundTag(spellTag);
        data.writeInt(caster.getEntityId());
        ClientPlayNetworking.send(ID, data);
    }

    @Environment(EnvType.CLIENT)
    public static void handle(MinecraftClient client, ClientPlayNetworkHandler networkHandler, PacketByteBuf packetByteBuf, PacketSender sender) {
        CompoundTag spellTag = packetByteBuf.readCompoundTag();
        Entity entity = client.world.getEntityById(packetByteBuf.readInt());
        boolean backfires = packetByteBuf.readBoolean();
        if (entity instanceof LivingEntity) {
            client.execute(() -> Spell.fromTag(spellTag).cast((LivingEntity) entity, backfires));
        }
    }

    public static void handleFromClient(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf packetByteBuf, PacketSender sender) {
        Spell spell = Spell.fromTag(packetByteBuf.readCompoundTag());
        if (spell != null) {
            boolean backfires = spell.effect.backfires(player);
            server.execute(() -> spell.cast(player, backfires));
        }
    }
}
