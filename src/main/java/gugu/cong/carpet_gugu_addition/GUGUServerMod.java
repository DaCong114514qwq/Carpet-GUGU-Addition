package gugu.cong.carpet_gugu_addition;

import gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static gugu.cong.carpet_gugu_addition.utils.remoteOpenInventoryUtils.loadCarpetWuhu;
import static gugu.cong.carpet_gugu_addition.utils.remoteOpenInventoryUtils.loadPrinter;

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
		if (!loadPrinter && !loadCarpetWuhu) {  // 添加对 Carpet Wuhu 的判断
			OpenInventoryPacket.init();
			OpenInventoryPacket.registerReceivePacket();
		}
	}

	public static String getModId() {
		return MOD_ID;
	}

	public static String getVersion() {
		return MOD_VERSION;
	}
}
