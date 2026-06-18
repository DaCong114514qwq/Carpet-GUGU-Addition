package gugu.cong.carpet_gugu_addition;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class GUGUServerMod implements ModInitializer
{
	public static final String MOD_ID = "carpet_gugu_addition";
	public static String MOD_VERSION;

	@Override
	public void onInitialize()
	{
		MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
		GUGUServer.init();
	}
}
