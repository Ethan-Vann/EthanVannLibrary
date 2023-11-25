package org.ethanvannlibrary.Utility;

import lombok.SneakyThrows;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.api.SkullIcon;
import net.runelite.client.RuneLite;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ActorUtility {
    static Client client = RuneLite.getInjector().getInstance(Client.class);

    public static SkullIcon getSkullIcon(Player player) {
        Field skullField = null;
        try {
            skullField = player.getClass().getDeclaredField("ar");
            skullField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
        int var1 = -1;
        try {
            var1 = skullField.getInt(player) * -110809851;
            skullField.setAccessible(false);
        } catch (IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
        switch (var1) {
            case 0:
                return SkullIcon.SKULL;
            case 1:
                return SkullIcon.SKULL_FIGHT_PIT;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            default:
                return null;
            case 8:
                return SkullIcon.DEAD_MAN_FIVE;
            case 9:
                return SkullIcon.DEAD_MAN_FOUR;
            case 10:
                return SkullIcon.DEAD_MAN_THREE;
            case 11:
                return SkullIcon.DEAD_MAN_TWO;
            case 12:
                return SkullIcon.DEAD_MAN_ONE;
        }
    }

    @SneakyThrows
    public static int getAnimation(NPC npc) {
        if (npc == null) {
            return -1;
        }
        String animationField = null;
        for (Field declaredField : npc.getClass().getSuperclass().getDeclaredFields()) {
            if (declaredField == null) {
                continue;
            }
            declaredField.setAccessible(true);
            if (declaredField.getType() != int.class) {
                continue;
            }
            if (Modifier.isFinal(declaredField.getModifiers())) {
                continue;
            }
            if (Modifier.isStatic(declaredField.getModifiers())) {
                continue;
            }
            int value = declaredField.getInt(npc);
            declaredField.setInt(npc, 4795789);
            if (npc.getAnimation() == -760216869 * 4795789) {
                animationField = declaredField.getName();
                declaredField.setInt(npc, value);
                declaredField.setAccessible(false);
                break;
            }
            declaredField.setInt(npc, value);
            declaredField.setAccessible(false);
        }
        if (animationField == null) {
            return -1;
        }
        Field animation = npc.getClass().getSuperclass().getDeclaredField(animationField);
        animation.setAccessible(true);
        int anim = animation.getInt(npc) * -760216869;
        animation.setAccessible(false);
        return anim;
    }

    public static boolean isPlayerMoving() {
        return client.getLocalPlayer().getPoseAnimation()
                != client.getLocalPlayer().getIdlePoseAnimation();
    }
}
