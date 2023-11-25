package org.ethanvannlibrary.Packets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public final class ObfuscatedNames {

    private static ObfuscatedNames instance = null;

    @SneakyThrows
    public static ObfuscatedNames loadNames() {
        if (instance == null) {
            Gson gson = new Gson();
            //network call in future
            InputStream is = ObfuscatedNames.class.getResourceAsStream("obfuscatedNames.json");
            InputStreamReader isr = new InputStreamReader(is);
            instance = gson.fromJson(isr, ObfuscatedNames.class);
        }
        System.out.println("Loaded obfuscated names");
        return instance;
    }

    public String offsetMultiplier;
    public String indexMultiplier;
    public String clientMillisField;
    public String MouseHandler_lastPressedTimeMillisClass;
    public String MouseHandler_lastPressedTimeMillisField;
    public String mouseHandlerMillisMultiplier;
    public String clientMillisMultiplier;
    public String packetBufferNodeClassName;
    public String addNodeGarbageValue;
    public String addNodeMethodName;
    public String bufferOffsetField;
    public String bufferArrayField;
    public String packetBufferFieldName;
    public String classContainingGetPacketBufferNodeName;
    public String clientPacketClassName;
    public String getPacketBufferNodeGarbageValue;
    public String isaacCipherFieldName;
    public String packetWriterFieldName;
    public String packetWriterClassName;

    public String RESUME_PAUSEBUTTON_OBFUSCATEDNAME;
    public String RESUME_PAUSEBUTTON_WRITE1;

    public String RESUME_PAUSEBUTTON_WRITE2;


    public String IF_BUTTON1_OBFUSCATEDNAME;
    public String IF_BUTTON1_WRITE1;

    public String IF_BUTTON1_WRITE2;

    public String IF_BUTTON1_WRITE3;


    public String IF_BUTTON2_OBFUSCATEDNAME;
    public String IF_BUTTON2_WRITE1;

    public String IF_BUTTON2_WRITE2;

    public String IF_BUTTON2_WRITE3;


    public String IF_BUTTON3_OBFUSCATEDNAME;
    public String IF_BUTTON3_WRITE1;

    public String IF_BUTTON3_WRITE2;

    public String IF_BUTTON3_WRITE3;


    public String IF_BUTTON4_OBFUSCATEDNAME;
    public String IF_BUTTON4_WRITE1;

    public String IF_BUTTON4_WRITE2;

    public String IF_BUTTON4_WRITE3;


    public String IF_BUTTON5_OBFUSCATEDNAME;
    public String IF_BUTTON5_WRITE1;

    public String IF_BUTTON5_WRITE2;

    public String IF_BUTTON5_WRITE3;


    public String IF_BUTTON6_OBFUSCATEDNAME;
    public String IF_BUTTON6_WRITE1;

    public String IF_BUTTON6_WRITE2;

    public String IF_BUTTON6_WRITE3;


    public String IF_BUTTON7_OBFUSCATEDNAME;
    public String IF_BUTTON7_WRITE1;

    public String IF_BUTTON7_WRITE2;

    public String IF_BUTTON7_WRITE3;


    public String IF_BUTTON8_OBFUSCATEDNAME;
    public String IF_BUTTON8_WRITE1;

    public String IF_BUTTON8_WRITE2;

    public String IF_BUTTON8_WRITE3;


    public String IF_BUTTON9_OBFUSCATEDNAME;
    public String IF_BUTTON9_WRITE1;

    public String IF_BUTTON9_WRITE2;

    public String IF_BUTTON9_WRITE3;


    public String IF_BUTTON10_OBFUSCATEDNAME;
    public String IF_BUTTON10_WRITE1;

    public String IF_BUTTON10_WRITE2;

    public String IF_BUTTON10_WRITE3;


    public String OPOBJ5_OBFUSCATEDNAME;
    public String OPOBJ5_WRITE1;

    public String OPOBJ5_WRITE2;

    public String OPOBJ5_WRITE3;

    public String OPOBJ5_WRITE4;


    public String OPLOC4_OBFUSCATEDNAME;
    public String OPLOC4_WRITE1;

    public String OPLOC4_WRITE2;

    public String OPLOC4_WRITE3;

    public String OPLOC4_WRITE4;


    public String OPNPC3_OBFUSCATEDNAME;
    public String OPNPC3_WRITE1;

    public String OPNPC3_WRITE2;


    public String OPNPC4_OBFUSCATEDNAME;
    public String OPNPC4_WRITE1;

    public String OPNPC4_WRITE2;


    public String OPPLAYER5_OBFUSCATEDNAME;
    public String OPPLAYER5_WRITE1;

    public String OPPLAYER5_WRITE2;


    public String OPLOC1_OBFUSCATEDNAME;
    public String OPLOC1_WRITE1;

    public String OPLOC1_WRITE2;

    public String OPLOC1_WRITE3;

    public String OPLOC1_WRITE4;


    public String OPOBJT_OBFUSCATEDNAME;
    public String OPOBJT_WRITE1;

    public String OPOBJT_WRITE2;

    public String OPOBJT_WRITE3;

    public String OPOBJT_WRITE4;

    public String OPOBJT_WRITE5;

    public String OPOBJT_WRITE6;

    public String OPOBJT_WRITE7;


    public String OPOBJ3_OBFUSCATEDNAME;
    public String OPOBJ3_WRITE1;

    public String OPOBJ3_WRITE2;

    public String OPOBJ3_WRITE3;

    public String OPOBJ3_WRITE4;


    public String OPPLAYER8_OBFUSCATEDNAME;
    public String OPPLAYER8_WRITE1;

    public String OPPLAYER8_WRITE2;


    public String OPOBJ4_OBFUSCATEDNAME;
    public String OPOBJ4_WRITE1;

    public String OPOBJ4_WRITE2;

    public String OPOBJ4_WRITE3;

    public String OPOBJ4_WRITE4;


    public String OPPLAYER7_OBFUSCATEDNAME;
    public String OPPLAYER7_WRITE1;

    public String OPPLAYER7_WRITE2;


    public String OPPLAYER4_OBFUSCATEDNAME;
    public String OPPLAYER4_WRITE1;

    public String OPPLAYER4_WRITE2;


    public String OPLOCT_OBFUSCATEDNAME;
    public String OPLOCT_WRITE1;

    public String OPLOCT_WRITE2;

    public String OPLOCT_WRITE3;

    public String OPLOCT_WRITE4;

    public String OPLOCT_WRITE5;

    public String OPLOCT_WRITE6;

    public String OPLOCT_WRITE7;


    public String OPNPCT_OBFUSCATEDNAME;
    public String OPNPCT_WRITE1;

    public String OPNPCT_WRITE2;

    public String OPNPCT_WRITE3;

    public String OPNPCT_WRITE4;

    public String OPNPCT_WRITE5;


    public String IF_BUTTONT_OBFUSCATEDNAME;
    public String IF_BUTTONT_WRITE1;

    public String IF_BUTTONT_WRITE2;

    public String IF_BUTTONT_WRITE3;

    public String IF_BUTTONT_WRITE4;

    public String IF_BUTTONT_WRITE5;

    public String IF_BUTTONT_WRITE6;


    public String OPLOC5_OBFUSCATEDNAME;
    public String OPLOC5_WRITE1;

    public String OPLOC5_WRITE2;

    public String OPLOC5_WRITE3;

    public String OPLOC5_WRITE4;


    public String OPNPC2_OBFUSCATEDNAME;
    public String OPNPC2_WRITE1;

    public String OPNPC2_WRITE2;


    public String OPNPC1_OBFUSCATEDNAME;
    public String OPNPC1_WRITE1;

    public String OPNPC1_WRITE2;


    public String OPPLAYERT_OBFUSCATEDNAME;
    public String OPPLAYERT_WRITE1;

    public String OPPLAYERT_WRITE2;

    public String OPPLAYERT_WRITE3;

    public String OPPLAYERT_WRITE4;

    public String OPPLAYERT_WRITE5;


    public String OPLOC2_OBFUSCATEDNAME;
    public String OPLOC2_WRITE1;

    public String OPLOC2_WRITE2;

    public String OPLOC2_WRITE3;

    public String OPLOC2_WRITE4;


    public String OPPLAYER6_OBFUSCATEDNAME;
    public String OPPLAYER6_WRITE1;

    public String OPPLAYER6_WRITE2;


    public String OPPLAYER1_OBFUSCATEDNAME;
    public String OPPLAYER1_WRITE1;

    public String OPPLAYER1_WRITE2;


    public String OPPLAYER2_OBFUSCATEDNAME;
    public String OPPLAYER2_WRITE1;

    public String OPPLAYER2_WRITE2;


    public String OPNPC5_OBFUSCATEDNAME;
    public String OPNPC5_WRITE1;

    public String OPNPC5_WRITE2;


    public String OPOBJ2_OBFUSCATEDNAME;
    public String OPOBJ2_WRITE1;

    public String OPOBJ2_WRITE2;

    public String OPOBJ2_WRITE3;

    public String OPOBJ2_WRITE4;


    public String OPLOC3_OBFUSCATEDNAME;
    public String OPLOC3_WRITE1;

    public String OPLOC3_WRITE2;

    public String OPLOC3_WRITE3;

    public String OPLOC3_WRITE4;


    public String OPOBJ1_OBFUSCATEDNAME;
    public String OPOBJ1_WRITE1;

    public String OPOBJ1_WRITE2;

    public String OPOBJ1_WRITE3;

    public String OPOBJ1_WRITE4;


    public String OPPLAYER3_OBFUSCATEDNAME;
    public String OPPLAYER3_WRITE1;

    public String OPPLAYER3_WRITE2;


    public String EVENT_MOUSE_CLICK_OBFUSCATEDNAME;
    public String EVENT_MOUSE_CLICK_WRITE1;

    public String EVENT_MOUSE_CLICK_WRITE2;

    public String EVENT_MOUSE_CLICK_WRITE3;


    public String MOVE_GAMECLICK_OBFUSCATEDNAME;
    public String MOVE_GAMECLICK_WRITE1;

    public String MOVE_GAMECLICK_WRITE2;

    public String MOVE_GAMECLICK_WRITE3;

    public String MOVE_GAMECLICK_WRITE4;

    public String[][] OPOBJ5_WRITES;
    public String[][] OPLOC4_WRITES;
    public String[][] OPNPC3_WRITES;
    public String[][] OPNPC4_WRITES;
    public String[][] OPPLAYER5_WRITES;
    public String[][] OPLOC1_WRITES;
    public String[][] OPOBJT_WRITES;
    public String[][] OPOBJ3_WRITES;
    public String[][] OPPLAYER8_WRITES;
    public String[][] OPOBJ4_WRITES;
    public String[][] OPPLAYER7_WRITES;
    public String[][] OPPLAYER4_WRITES;
    public String[][] OPLOCT_WRITES;
    public String[][] OPNPCT_WRITES;
    public String[][] IF_BUTTONT_WRITES;
    public String[][] OPLOC5_WRITES;
    public String[][] OPNPC2_WRITES;
    public String[][] OPNPC1_WRITES;
    public String[][] OPPLAYERT_WRITES;
    public String[][] OPLOC2_WRITES;
    public String[][] OPPLAYER6_WRITES;
    public String[][] OPPLAYER1_WRITES;
    public String[][] OPPLAYER2_WRITES;
    public String[][] OPNPC5_WRITES;
    public String[][] OPOBJ2_WRITES;
    public String[][] OPLOC3_WRITES;
    public String[][] OPOBJ1_WRITES;
    public String[][] OPPLAYER3_WRITES;
    public String[][] RESUME_PAUSEBUTTON_WRITES;
    public String[][] IF_BUTTON1_WRITES;
    public String[][] IF_BUTTON2_WRITES;
    public String[][] IF_BUTTON3_WRITES;
    public String[][] IF_BUTTON4_WRITES;
    public String[][] IF_BUTTON5_WRITES;
    public String[][] IF_BUTTON6_WRITES;
    public String[][] IF_BUTTON7_WRITES;
    public String[][] IF_BUTTON8_WRITES;
    public String[][] IF_BUTTON9_WRITES;
    public String[][] IF_BUTTON10_WRITES;
    public String[][] EVENT_MOUSE_CLICK_WRITES;
    public String[][] MOVE_GAMECLICK_WRITES;


    @SneakyThrows
    public void writeField(String name, String value) {
        Field f = this.getClass().getDeclaredField(name);
        f.set(this, value);
    }

    @SneakyThrows
    public void writeField(String name, String[][] value) {
        Field f = this.getClass().getDeclaredField(name);
        f.set(this, value);
    }

    @SneakyThrows
    @Override
    public boolean equals(Object b) {
        if (b == null) {
            return false;
        }
        if (!(b instanceof ObfuscatedNames)) {
            return false;
        }
        ObfuscatedNames other = (ObfuscatedNames) b;
        for (Field f : this.getClass().getDeclaredFields()) {
            if (!f.get(this).equals(f.get(other))) {
                System.out.println("Field " + f.getName() + " with value: " + f.get(this) + " does not match " + f.get(other));
                return false;
            }
        }
        return true;
    }

    public boolean containsNullOrEmpty() {
        for (Field f : this.getClass().getDeclaredFields()) {
            try {
                if (f.getName().equals("instance")) {
                    continue;
                }
                if (f.get(this) == null) {
                    System.out.println("Field " + f.getName() + " is null");
                    return true;
                }
                if (f.getType() == String.class) {
                    if (((String) f.get(this)).isEmpty()) {
                        System.out.println("Field " + f.getName() + " is empty");
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String toGson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}