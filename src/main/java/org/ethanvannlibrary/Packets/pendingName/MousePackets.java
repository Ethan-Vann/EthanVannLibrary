package org.ethanvannlibrary.Packets.pendingName;

import lombok.SneakyThrows;
import org.ethanvannlibrary.Packets.PacketDef;
import org.ethanvannlibrary.Packets.PacketReflection;

import java.lang.reflect.Field;
import java.math.BigInteger;

public class MousePackets extends PacketReflectionDependent {

    public MousePackets(PacketReflection packetReflection) {
        super(packetReflection);
    }

    public static BigInteger modInverse(BigInteger val, int bits) {
        try {
            BigInteger shift = BigInteger.ONE.shiftLeft(bits);
            return val.modInverse(shift);
        } catch (ArithmeticException e) {
            return val;
        }
    }

    public static long modInverse(long val) {
        return modInverse(BigInteger.valueOf(val), 64).longValue();
    }

    @SneakyThrows
    public void queueClickPacket(int x, int y) {
        long mouseHandlerMS = System.currentTimeMillis();
        setMouseHandlerLastMillis(mouseHandlerMS);
        long clientMS = getClientLastMillis();
        long deltaMs = mouseHandlerMS - clientMS;
        setClientLastMillis(mouseHandlerMS);
        System.out.println("deltaMs: " + deltaMs);
        if (deltaMs < 0) {
            deltaMs = 0L;
        }
        if (deltaMs > 32767) {
            deltaMs = 32767L;
        }
        int mouseInfo = ((int) deltaMs << 1);
        packetReflection.sendPacket(PacketDef.getEventMouseClick(packetReflection), mouseInfo, x, y);
    }

    public void queueClickPacket() {
        queueClickPacket(0, 0);
    }

    public void queueRandomClickPacket(int x, int y) {

    }

    @SneakyThrows
    public long getMouseHandlerLastMillis() {
        Class<?> mouseHandler = client.getClass().getClassLoader().loadClass(packetReflection.getNames().MouseHandler_lastPressedTimeMillisClass);
        Field mouseHandlerLastPressedTime = mouseHandler.getDeclaredField(packetReflection.getNames().MouseHandler_lastPressedTimeMillisField);
        mouseHandlerLastPressedTime.setAccessible(true);
        long retValue = mouseHandlerLastPressedTime.getLong(null) * Long.parseLong(packetReflection.getNames().mouseHandlerMillisMultiplier);
        mouseHandlerLastPressedTime.setAccessible(false);
        return retValue;
    }

    @SneakyThrows
    public long getClientLastMillis() {
        Field clientLastPressedTimeMillis = client.getClass().getDeclaredField(packetReflection.getNames().clientMillisField);
        clientLastPressedTimeMillis.setAccessible(true);
        long retValue = clientLastPressedTimeMillis.getLong(client) * Long.parseLong(packetReflection.getNames().clientMillisMultiplier);
        clientLastPressedTimeMillis.setAccessible(false);
        return retValue;
    }

    @SneakyThrows
    public void setMouseHandlerLastMillis(long time) {
        Class<?> mouseHandler = client.getClass().getClassLoader().loadClass(packetReflection.getNames().MouseHandler_lastPressedTimeMillisClass);
        Field mouseHandlerLastPressedTime = mouseHandler.getDeclaredField(packetReflection.getNames().MouseHandler_lastPressedTimeMillisField);
        mouseHandlerLastPressedTime.setAccessible(true);
        mouseHandlerLastPressedTime.setLong(null, time * modInverse(Long.parseLong(packetReflection.getNames().mouseHandlerMillisMultiplier)));
        mouseHandlerLastPressedTime.setAccessible(false);
    }

    @SneakyThrows
    public void setClientLastMillis(long time) {
        Field clientLastPressedTimeMillis = client.getClass().getDeclaredField(packetReflection.getNames().clientMillisField);
        clientLastPressedTimeMillis.setAccessible(true);
        clientLastPressedTimeMillis.setLong(client, time * modInverse(Long.parseLong(packetReflection.getNames().clientMillisMultiplier)));
        clientLastPressedTimeMillis.setAccessible(false);
    }
}