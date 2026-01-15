/*
 * This file is part of the TemplateMod project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2023  Fallen_Breath and contributors
 *
 * TemplateMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TemplateMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TemplateMod.  If not, see <https://www.gnu.org/licenses/>.
 */

package gugu.cong.carpet_gugu_addition;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUGUServerMod implements ModInitializer
{
	public static final String MOD_ID = "carpet_gugu_addition";
	public static String MOD_NAME = "Carpet GUGU Addition";
	public static String MOD_VERSION;
	public static final String COMPACT_NAME = MOD_ID.replace("-","");
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	@Override
	public void onInitialize()
	{
		MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
		GUGUServer.init();
	}

	public static String getModId() {
		return MOD_ID;
	}

	public static String getVersion() {
		return MOD_VERSION;
	}
}
