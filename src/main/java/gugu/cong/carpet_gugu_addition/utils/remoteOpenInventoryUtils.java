package gugu.cong.carpet_gugu_addition.utils;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

public class remoteOpenInventoryUtils {
    public static Identifier of(String string) {
        return Identifier.parse(string);
    }

    public static Identifier of(String namespace, String path) {
        return Identifier.fromNamespaceAndPath(namespace, path);
    }
    public static boolean loadPrinter = isLoadMod("litematica-printer");
    public static boolean loadCarpetWuhu = isLoadMod("carpet-wuhu-addition");
    public static boolean isLoadMod(String modId){
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}