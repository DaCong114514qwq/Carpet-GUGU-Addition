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
