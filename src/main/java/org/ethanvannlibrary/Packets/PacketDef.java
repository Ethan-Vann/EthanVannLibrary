package org.ethanvannlibrary.Packets;

public class PacketDef {
    public final String name;
    public final String[] writeData;
    public final String[][] writeMethods;
    public final PacketType type;

    PacketDef(String var1, String[] writeData, String[][] writeMethods, PacketType type) {
        this.name = var1;
        this.writeData = writeData;
        this.writeMethods = writeMethods;
        this.type = type;
    }

    public static PacketDef getOpObj1(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPOBJ1_WRITE1, pr.getNames().OPOBJ1_WRITE2, pr.getNames().OPOBJ1_WRITE3, pr.getNames().OPOBJ1_WRITE4};
        String[][] writeMethods = pr.getNames().OPOBJ1_WRITES;
        return new PacketDef(pr.getNames().OPOBJ1_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPOBJ);
    }

    public static PacketDef getIfButton9(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON9_WRITE1, pr.getNames().IF_BUTTON9_WRITE2, pr.getNames().IF_BUTTON9_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON9_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON9_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getIfButton8(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON8_WRITE1, pr.getNames().IF_BUTTON8_WRITE2, pr.getNames().IF_BUTTON8_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON8_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON8_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getOpObj5(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPOBJ5_WRITE1, pr.getNames().OPOBJ5_WRITE2, pr.getNames().OPOBJ5_WRITE3, pr.getNames().OPOBJ5_WRITE4};
        String[][] writeMethods = pr.getNames().OPOBJ5_WRITES;
        return new PacketDef(pr.getNames().OPOBJ5_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPOBJ);
    }

    public static PacketDef getIfButton5(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON5_WRITE1, pr.getNames().IF_BUTTON5_WRITE2, pr.getNames().IF_BUTTON5_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON5_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON5_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getOpObj4(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPOBJ4_WRITE1, pr.getNames().OPOBJ4_WRITE2, pr.getNames().OPOBJ4_WRITE3, pr.getNames().OPOBJ4_WRITE4};
        String[][] writeMethods = pr.getNames().OPOBJ4_WRITES;
        return new PacketDef(pr.getNames().OPOBJ4_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPOBJ);
    }

    public static PacketDef getIfButton4(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON4_WRITE1, pr.getNames().IF_BUTTON4_WRITE2, pr.getNames().IF_BUTTON4_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON4_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON4_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getOpObj3(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPOBJ3_WRITE1, pr.getNames().OPOBJ3_WRITE2, pr.getNames().OPOBJ3_WRITE3, pr.getNames().OPOBJ3_WRITE4};
        String[][] writeMethods = pr.getNames().OPOBJ3_WRITES;
        return new PacketDef(pr.getNames().OPOBJ3_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPOBJ);
    }

    public static PacketDef getIfButton7(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON7_WRITE1, pr.getNames().IF_BUTTON7_WRITE2, pr.getNames().IF_BUTTON7_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON7_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON7_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getOpObj2(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPOBJ2_WRITE1, pr.getNames().OPOBJ2_WRITE2, pr.getNames().OPOBJ2_WRITE3, pr.getNames().OPOBJ2_WRITE4};
        String[][] writeMethods = pr.getNames().OPOBJ2_WRITES;
        return new PacketDef(pr.getNames().OPOBJ2_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPOBJ);
    }

    public static PacketDef getIfButton6(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON6_WRITE1, pr.getNames().IF_BUTTON6_WRITE2, pr.getNames().IF_BUTTON6_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON6_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON6_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getOpLocT(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPLOCT_WRITE1, pr.getNames().OPLOCT_WRITE2, pr.getNames().OPLOCT_WRITE3, pr.getNames().OPLOCT_WRITE4, pr.getNames().OPLOCT_WRITE5, pr.getNames().OPLOCT_WRITE6, pr.getNames().OPLOCT_WRITE7};
        String[][] writeMethods = pr.getNames().OPLOCT_WRITES;
        return new PacketDef(pr.getNames().OPLOCT_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPLOCT);
    }

    public static PacketDef getOpNpcT(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPNPCT_WRITE1, pr.getNames().OPNPCT_WRITE2, pr.getNames().OPNPCT_WRITE3,pr.getNames().OPNPCT_WRITE4,pr.getNames().OPNPCT_WRITE5};
        String[][] writeMethods = pr.getNames().OPNPCT_WRITES;
        return new PacketDef(pr.getNames().OPNPCT_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPNPC);
    }

    public static PacketDef getOpPlayerT(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYERT_WRITE1, pr.getNames().OPPLAYERT_WRITE2, pr.getNames().OPPLAYERT_WRITE3, pr.getNames().OPPLAYERT_WRITE4, pr.getNames().OPPLAYERT_WRITE5};
        String[][] writeMethods = pr.getNames().OPPLAYERT_WRITES;
        return new PacketDef(pr.getNames().OPPLAYERT_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYERT);
    }

    public static PacketDef getOpObjT(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPOBJT_WRITE1, pr.getNames().OPOBJT_WRITE2, pr.getNames().OPOBJT_WRITE3, pr.getNames().OPOBJT_WRITE4, pr.getNames().OPOBJT_WRITE5, pr.getNames().OPOBJT_WRITE6, pr.getNames().OPOBJT_WRITE7};
        String[][] writeMethods = pr.getNames().OPOBJT_WRITES;
        return new PacketDef(pr.getNames().OPOBJT_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPOBJT);
    }

    public static PacketDef getIfButtonT(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTONT_WRITE1, pr.getNames().IF_BUTTONT_WRITE2, pr.getNames().IF_BUTTONT_WRITE3, pr.getNames().IF_BUTTONT_WRITE4, pr.getNames().IF_BUTTONT_WRITE5, pr.getNames().IF_BUTTONT_WRITE6};
        String[][] writeMethods = pr.getNames().IF_BUTTONT_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTONT_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTONT);
    }

    public static PacketDef getOpNpc2(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPNPC2_WRITE1, pr.getNames().OPNPC2_WRITE2};
        String[][] writeMethods = pr.getNames().OPNPC2_WRITES;
        return new PacketDef(pr.getNames().OPNPC2_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPNPC);
    }

    public static PacketDef getOpPlayer6(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER6_WRITE1, pr.getNames().OPPLAYER6_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER6_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER6_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getOpNpc3(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPNPC3_WRITE1, pr.getNames().OPNPC3_WRITE2};
        String[][] writeMethods = pr.getNames().OPNPC3_WRITES;
        return new PacketDef(pr.getNames().OPNPC3_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPNPC);
    }

    public static PacketDef getOpPlayer7(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER7_WRITE1, pr.getNames().OPPLAYER7_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER7_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER7_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getOpLoc2(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPLOC2_WRITE1, pr.getNames().OPLOC2_WRITE2, pr.getNames().OPLOC2_WRITE3, pr.getNames().OPLOC2_WRITE4};
        String[][] writeMethods = pr.getNames().OPLOC2_WRITES;
        return new PacketDef(pr.getNames().OPLOC2_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPLOC);
    }

    public static PacketDef getOpPlayer8(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER8_WRITE1, pr.getNames().OPPLAYER8_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER8_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER8_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getOpLoc1(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPLOC1_WRITE1, pr.getNames().OPLOC1_WRITE2, pr.getNames().OPLOC1_WRITE3, pr.getNames().OPLOC1_WRITE4};
        String[][] writeMethods = pr.getNames().OPLOC1_WRITES;
        return new PacketDef(pr.getNames().OPLOC1_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPLOC);
    }

    public static PacketDef getOpNpc1(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPNPC1_WRITE1, pr.getNames().OPNPC1_WRITE2};
        String[][] writeMethods = pr.getNames().OPNPC1_WRITES;
        return new PacketDef(pr.getNames().OPNPC1_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPNPC);
    }

    public static PacketDef getOpLoc4(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPLOC4_WRITE1, pr.getNames().OPLOC4_WRITE2, pr.getNames().OPLOC4_WRITE3, pr.getNames().OPLOC4_WRITE4};
        String[][] writeMethods = pr.getNames().OPLOC4_WRITES;
        return new PacketDef(pr.getNames().OPLOC4_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPLOC);
    }

    public static PacketDef getOpPlayer2(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER2_WRITE1, pr.getNames().OPPLAYER2_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER2_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER2_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getOpLoc3(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPLOC3_WRITE1, pr.getNames().OPLOC3_WRITE2, pr.getNames().OPLOC3_WRITE3, pr.getNames().OPLOC3_WRITE4};
        String[][] writeMethods = pr.getNames().OPLOC3_WRITES;
        return new PacketDef(pr.getNames().OPLOC3_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPLOC);
    }

    public static PacketDef getOpPlayer3(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER3_WRITE1, pr.getNames().OPPLAYER3_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER3_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER3_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getOpNpc4(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPNPC4_WRITE1, pr.getNames().OPNPC4_WRITE2};
        String[][] writeMethods = pr.getNames().OPNPC4_WRITES;
        return new PacketDef(pr.getNames().OPNPC4_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPNPC);
    }

    public static PacketDef getOpPlayer4(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER4_WRITE1, pr.getNames().OPPLAYER4_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER4_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER4_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getOpNpc5(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPNPC5_WRITE1, pr.getNames().OPNPC5_WRITE2};
        String[][] writeMethods = pr.getNames().OPNPC5_WRITES;
        return new PacketDef(pr.getNames().OPNPC5_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPNPC);
    }

    public static PacketDef getOpPlayer5(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER5_WRITE1, pr.getNames().OPPLAYER5_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER5_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER5_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getOpLoc5(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPLOC5_WRITE1, pr.getNames().OPLOC5_WRITE2, pr.getNames().OPLOC5_WRITE3, pr.getNames().OPLOC5_WRITE4};
        String[][] writeMethods = pr.getNames().OPLOC5_WRITES;
        return new PacketDef(pr.getNames().OPLOC5_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPLOC);
    }

    public static PacketDef getOpPlayer1(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().OPPLAYER1_WRITE1, pr.getNames().OPPLAYER1_WRITE2};
        String[][] writeMethods = pr.getNames().OPPLAYER1_WRITES;
        return new PacketDef(pr.getNames().OPPLAYER1_OBFUSCATEDNAME, writeData, writeMethods, PacketType.OPPLAYER);
    }

    public static PacketDef getMoveGameClick(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().MOVE_GAMECLICK_WRITE1, pr.getNames().MOVE_GAMECLICK_WRITE2, pr.getNames().MOVE_GAMECLICK_WRITE3, pr.getNames().MOVE_GAMECLICK_WRITE4};
        String[][] writeMethods = pr.getNames().MOVE_GAMECLICK_WRITES;
        return new PacketDef(pr.getNames().MOVE_GAMECLICK_OBFUSCATEDNAME, writeData, writeMethods, PacketType.MOVE_GAMECLICK);
    }

    public static PacketDef getIfButton1(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON1_WRITE1, pr.getNames().IF_BUTTON1_WRITE2, pr.getNames().IF_BUTTON1_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON1_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON1_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getIfButton3(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON3_WRITE1, pr.getNames().IF_BUTTON3_WRITE2, pr.getNames().IF_BUTTON3_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON3_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON3_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getIfButton2(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON2_WRITE1, pr.getNames().IF_BUTTON2_WRITE2, pr.getNames().IF_BUTTON2_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON2_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON2_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getEventMouseClick(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().EVENT_MOUSE_CLICK_WRITE1, pr.getNames().EVENT_MOUSE_CLICK_WRITE2, pr.getNames().EVENT_MOUSE_CLICK_WRITE3};
        String[][] writeMethods = pr.getNames().EVENT_MOUSE_CLICK_WRITES;
        return new PacketDef(pr.getNames().EVENT_MOUSE_CLICK_OBFUSCATEDNAME, writeData, writeMethods, PacketType.EVENT_MOUSE_CLICK);
    }

    public static PacketDef getIfButton10(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().IF_BUTTON10_WRITE1, pr.getNames().IF_BUTTON10_WRITE2, pr.getNames().IF_BUTTON10_WRITE3};
        String[][] writeMethods = pr.getNames().IF_BUTTON10_WRITES;
        return new PacketDef(pr.getNames().IF_BUTTON10_OBFUSCATEDNAME, writeData, writeMethods, PacketType.IF_BUTTON);
    }

    public static PacketDef getResumePausebutton(PacketReflection pr) {
        String[] writeData = new String[]{pr.getNames().RESUME_PAUSEBUTTON_WRITE1, pr.getNames().RESUME_PAUSEBUTTON_WRITE2};
        String[][] writeMethods = pr.getNames().RESUME_PAUSEBUTTON_WRITES;
        return new PacketDef(pr.getNames().RESUME_PAUSEBUTTON_OBFUSCATEDNAME, writeData, writeMethods, PacketType.RESUME_PAUSEBUTTON);
    }

}
