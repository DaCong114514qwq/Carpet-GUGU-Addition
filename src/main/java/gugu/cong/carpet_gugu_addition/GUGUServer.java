package gugu.cong.carpet_gugu_addition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import gugu.cong.carpet_gugu_addition.utils.ComponentTranslate;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class GUGUServer implements CarpetExtension {
    public static long serverStartTimeMillis;
    public static final String MOD_ID = GUGUServerMod.MOD_ID;
    public static final Logger LOGGER = (Logger) GUGUServerMod.LOGGER;
    public static final String compact_name = GUGUServerMod.COMPACT_NAME;
    private static final GUGUServer INSTANCE = new GUGUServer();
    public static MinecraftServer minecraft_server;
    public static SettingsManager settingsManager;

    @Override
    public String version()
    {
        return GUGUServerMod.MOD_ID;
    }

    public static GUGUServer getInstance()
    {
        return INSTANCE;
    }

    public static void init() {
        CarpetServer.manageExtension(INSTANCE);
    }

    @Override
    public void onGameStarted() {
        settingsManager = new SettingsManager(GUGUServer.getInstance().version(), MOD_ID, "GUGU");
        CarpetServer.settingsManager.parseSettingsClass(GUGUSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        minecraft_server = server;
        serverStartTimeMillis = System.currentTimeMillis();
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return ComponentTranslate.getTranslationFromResourcePath(lang);
    }
}
