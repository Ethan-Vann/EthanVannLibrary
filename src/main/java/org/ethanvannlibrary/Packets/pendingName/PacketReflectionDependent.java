package org.ethanvannlibrary.Packets.pendingName;

import net.runelite.api.Client;
import net.runelite.client.RuneLite;
import org.ethanvannlibrary.Packets.PacketReflection;

public class PacketReflectionDependent {
    protected PacketReflection packetReflection;
    static Client client = RuneLite.getInjector().getInstance(Client.class);


    protected PacketReflectionDependent(PacketReflection packetReflection) {
        this.packetReflection = packetReflection;
    }
}
