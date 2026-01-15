/*
 * This file is part of the Carpet GUGU Addition project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2023  Fallen_Breath and contributors
 *
 * Carpet GUGU Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Carpet GUGU Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Carpet GUGU Addition.  If not, see <https://www.gnu.org/licenses/>.
 */

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
