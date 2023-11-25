package org.ethanvannlibrary.Packets;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.RuneLite;
import org.ethanvannlibrary.Packets.pendingName.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
public class PacketReflection {
    BufferMethods bufferMethods = new BufferMethods(this);
    public MousePackets mousePackets = new MousePackets(this);
    public NPCPackets npcPackets = new NPCPackets(this);
    public MovementPackets movementPackets = new MovementPackets(this);
    public ObjectPackets objectPackets = new ObjectPackets(this);
    public PlayerPackets playerPackets = new PlayerPackets(this);
    public TileItemPackets tileItemPackets = new TileItemPackets(this);
    public WidgetPackets widgetPackets = new WidgetPackets(this);


    private static final Client client = RuneLite.getInjector().getInstance(Client.class);
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    @Getter
    private final ObfuscatedNames names;
    static Future<PacketReflection> instance = null;


    @SneakyThrows
    public static Future<PacketReflection> getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = executor.submit(PacketReflection::new);
        return instance;
    }

    private PacketReflection() {
        names = ObfuscatedNames.loadNames();
    }


    @SneakyThrows
    public void sendPacket(PacketDef def, Object... objects) {
        System.out.println("sending packet");
        Object node = callGetPacketBufferNode(def.name);
        Object buffer = fetchBufferFromPacketBufferNode(node);
        List<String> params = null;
        if (def.type == PacketType.RESUME_PAUSEBUTTON) {
            params = List.of("var0", "var1");
        }
        if (def.type == PacketType.IF_BUTTON) {
            params = List.of("widgetId", "slot", "itemId");
        }
        if (def.type == PacketType.OPLOC) {
            params = List.of("objectId", "worldPointX", "worldPointY", "ctrlDown");
        }
        if (def.type == PacketType.OPNPC) {
            params = List.of("npcIndex", "ctrlDown");
        }
        if (def.type == PacketType.OPPLAYER) {
            params = List.of("playerIndex", "ctrlDown");
        }
        if (def.type == PacketType.OPOBJ) {
            params = List.of("objectId", "worldPointX", "worldPointY", "ctrlDown");
        }
        if (def.type == PacketType.OPOBJT) {
            params = List.of("objectId", "worldPointX", "worldPointY", "slot", "itemId", "widgetId",
                    "ctrlDown");
        }
        if (def.type == PacketType.EVENT_MOUSE_CLICK) {
            params = List.of("mouseInfo", "mouseX", "mouseY");
        }
        if (def.type == PacketType.MOVE_GAMECLICK) {
            params = List.of("worldPointX", "worldPointY", "ctrlDown", "5");
        }
        if (def.type == PacketType.IF_BUTTONT) {
            params = List.of("sourceWidgetId", "sourceSlot", "sourceItemId", "destinationWidgetId",
                    "destinationSlot", "destinationItemId");
        }
        if (def.type == PacketType.OPLOCT) {
            params = List.of("objectId", "worldPointX", "worldPointY", "slot", "itemId", "widgetId",
                    "ctrlDown");
        }
        if (def.type == PacketType.OPPLAYERT) {
            params = List.of("playerIndex", "itemId", "slot", "widgetId", "ctrlDown");
        }
        if (def.type == PacketType.OPNPCT) {
            params = List.of("npcIndex", "itemId", "slot", "widgetId", "ctrlDown");
        }
        if (params != null) {
            for (int i = 0; i < def.writeData.length; i++) {
                int index = params.indexOf(def.writeData[i]);
                Object writeValue = objects[index];
                for (String s : def.writeMethods[i]) {
                    bufferMethods.writeValue(s, (Integer) writeValue, buffer);
                }
            }
        }
        addNode(node);
    }

    Class<?> getGarbageValueClass(String garbageValue) {
        int garbageValueInt = Math.abs(Integer.parseInt(garbageValue));
        if (garbageValueInt <= 256) {
            return byte.class;
        } else if (garbageValueInt <= 32768) {
            return short.class;
        }
        return int.class;
    }

    @SneakyThrows
    public void addNode(Object packetBufferNode) {
        System.out.println("adding node");
        Method addNode = fetchPacketWriter().getClass().getDeclaredMethod(names.addNodeMethodName, packetBufferNode.getClass(), getGarbageValueClass(names.addNodeGarbageValue));
        addNode.setAccessible(true);
        Class<?> clazz = addNode.getParameterTypes()[1];
        if (clazz.equals(byte.class)) {
            addNode.invoke(fetchPacketWriter(), packetBufferNode, Byte.parseByte(names.addNodeGarbageValue));
        } else if (clazz.equals(short.class)) {
            addNode.invoke(fetchPacketWriter(), packetBufferNode, Short.parseShort(names.addNodeGarbageValue));
        } else if (clazz.equals(int.class)) {
            addNode.invoke(fetchPacketWriter(), packetBufferNode, Integer.parseInt(names.addNodeGarbageValue));
        }
        addNode.setAccessible(false);
    }

    @SneakyThrows
    Class<?> fetchClassFromClientClassLoader(String className) {
        return client.getClass().getClassLoader().loadClass(className);
    }

    @SneakyThrows
    Object fetchClientPacket(String name) {
        Class<?> ClientPacket = fetchClassFromClientClassLoader(names.clientPacketClassName);
        Field packetField = ClientPacket.getDeclaredField(name);
        packetField.setAccessible(true);
        Object retVal = packetField.get(ClientPacket);
        packetField.setAccessible(false);
        return retVal;
    }

    @SneakyThrows
    Object fetchStaticClientFieldObject(String name) {
        Field clientField = client.getClass().getDeclaredField(name);
        clientField.setAccessible(true);
        Object retVal = clientField.get(null);
        clientField.setAccessible(false);
        return retVal;
    }

    @SneakyThrows
    Object fetchPacketWriter() {
        return fetchStaticClientFieldObject(names.packetWriterFieldName);
    }

    @SneakyThrows
    Object fetchIsaacCipher() {
        Object packetWriter = fetchPacketWriter();
        Field f = packetWriter.getClass().getDeclaredField(names.isaacCipherFieldName);
        f.setAccessible(true);
        Object retVal = f.get(packetWriter);
        f.setAccessible(false);
        return retVal;
    }

    @SneakyThrows
    Object callGetPacketBufferNode(String clientPacketName) {
        Class<?> ClassWithGetPacketBufferNode = fetchClassFromClientClassLoader(names.classContainingGetPacketBufferNodeName);
        assert ClassWithGetPacketBufferNode != null;
        Class<?> PacketBufferNode = fetchClassFromClientClassLoader(names.packetBufferNodeClassName);
        assert PacketBufferNode != null;
        Method getPacketBufferNode = Arrays.stream(ClassWithGetPacketBufferNode.getDeclaredMethods()).filter(m -> m.getReturnType().equals(PacketBufferNode)).collect(Collectors.toList()).get(0);
        assert getPacketBufferNode != null;
        getPacketBufferNode.setAccessible(true);
        Object retVal = null;
        int garbageValue = Math.abs(Integer.parseInt(names.getPacketBufferNodeGarbageValue));
        if (garbageValue <= 256) {
            try {
                retVal = getPacketBufferNode.invoke(null, fetchClientPacket(clientPacketName), fetchIsaacCipher(), (byte) Integer.parseInt(names.getPacketBufferNodeGarbageValue));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            }
        } else if (garbageValue <= 32768) {
            try {
                retVal = getPacketBufferNode.invoke(null, fetchClientPacket(clientPacketName), fetchIsaacCipher(), (short) Integer.parseInt(names.getPacketBufferNodeGarbageValue));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            }
        } else {
            try {
                retVal = getPacketBufferNode.invoke(null, fetchClientPacket(clientPacketName), fetchIsaacCipher(), Integer.parseInt(names.getPacketBufferNodeGarbageValue));
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                e.getCause().printStackTrace();
            }
        }
        getPacketBufferNode.setAccessible(false);
        return retVal;
    }

    @SneakyThrows
    Object fetchBufferFromPacketBufferNode(Object packetBufferNode) {
        Field f = packetBufferNode.getClass().getDeclaredField(names.packetBufferFieldName);
        f.setAccessible(true);
        Object retVal = f.get(packetBufferNode);
        f.setAccessible(false);
        return retVal;
    }
}
