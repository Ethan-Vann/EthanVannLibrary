package org.ethanvannlibrary.Utility;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import lombok.SneakyThrows;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginInstantiationException;
import net.runelite.client.plugins.PluginManager;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class HelperClass {
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    static PluginManager pluginManager = RuneLite.getInjector().getInstance(PluginManager.class);
    static ItemManager itemManager = RuneLite.getInjector().getInstance(ItemManager.class);
    static Method doAction = null;
    @Inject
    EventBus eventBus;
    public static LoadingCache<Integer, ItemComposition> itemDefs = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(20, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<>() {
                        @Override
                        public ItemComposition load(Integer itemId) {
                            return itemManager.getItemComposition(itemId);
                        }
                    });

    @SneakyThrows
    public static void invoke(int var0, int var1, int var2, int var3, int var4, String var5, String var6, int var7,
                              int var8) {
        if (doAction == null) {
            Field classes = ClassLoader.class.getDeclaredField("classes");
            classes.setAccessible(true);
            ClassLoader classLoader = client.getClass().getClassLoader();
            Vector<Class<?>> classesVector = (Vector<Class<?>>) classes.get(classLoader);
            Class<?>[] params = new Class[]{int.class, int.class, int.class, int.class, int.class, String.class, String.class, int.class, int.class};
            for (Class<?> aClass : classesVector) {
                if (doAction != null) {
                    break;
                }
                for (Method declaredMethod : aClass.getDeclaredMethods()) {
                    if (declaredMethod.getParameterCount() != 10) {
                        continue;
                    }
                    if (declaredMethod.getReturnType() != void.class) {
                        continue;
                    }
                    if (!Arrays.equals(Arrays.copyOfRange(declaredMethod.getParameterTypes(), 0, 9), params)) {
                        continue;
                    }
                    doAction = declaredMethod;
                    System.out.println(doAction);
                    break;
                }
            }
        }
        doAction.setAccessible(true);
        doAction.invoke(null, var0, var1, var2, var3, var4, var5, var6, var7, var8, (byte) 88);
        doAction.setAccessible(false);
    }

    @SneakyThrows
    public static void stopPlugin(Plugin plugin) {

        SwingUtilities.invokeAndWait(() ->
        {
            try {
                pluginManager.stopPlugin(plugin);
                pluginManager.setPluginEnabled(plugin, false);
            } catch (PluginInstantiationException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
