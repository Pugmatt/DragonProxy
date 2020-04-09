/*
 * DragonProxy
 * Copyright (C) 2016-2020 Dragonet Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You can view the LICENSE file for more details.
 *
 * https://github.com/DragonetMC/DragonProxy
 */
package org.dragonet.proxy.network.translator.bedrock.world;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.Position;
import com.github.steveice10.mc.protocol.packet.ingame.client.window.ClientSetBeaconEffectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientUpdateSignPacket;
import com.nukkitx.nbt.tag.CompoundTag;
import com.nukkitx.protocol.bedrock.packet.BlockEntityDataPacket;
import lombok.extern.log4j.Log4j2;
import org.dragonet.proxy.network.session.ProxySession;
import org.dragonet.proxy.network.translator.PacketTranslator;
import org.dragonet.proxy.network.translator.annotations.PEPacketTranslator;

@Log4j2
@PEPacketTranslator(packetClass = BlockEntityDataPacket.class)
public class PEBlockEntityDataTranslator extends PacketTranslator<BlockEntityDataPacket> {

    @Override
    public void translate(ProxySession session, BlockEntityDataPacket packet) {
        if(!(packet.getData() instanceof CompoundTag)) {
            return;
        }
        CompoundTag tag = (CompoundTag) packet.getData();

        log.warn(tag);

        switch(tag.getString("id")) {
            case "Sign":
                //ClientUpdateSignPacket clientUpdateSignPacket = new ClientUpdateSignPacket(new Position());
                break;
            case "Beacon":
                session.sendRemotePacket(new ClientSetBeaconEffectPacket(tag.getInt("primary"), tag.getInt("secondary")));
                break;
        }
    }
}
