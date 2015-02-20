package tehnut.morechisels.compat;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import tehnut.morechisels.items.ChiselType;
import tehnut.morechisels.items.ItemChiselBase;
import tehnut.morechisels.items.ItemRecipeRegistry;
import tehnut.morechisels.items.ItemRegistry;
import tehnut.morechisels.util.EnviroChecks;
import tehnut.morechisels.util.LogHelper;

import static tehnut.morechisels.ConfigHandler.chiselBedrockiumEnabled;
import static tehnut.morechisels.ConfigHandler.chiselUnstableEnabled;

public class ExtraUtilitiesCompat {

    public static Item chiselBedrockium;
    public static Item chiselUnstable;

    public static void load() {
        LogHelper.info("ExtraUtilities compatibility is enabled and running");
        registerItems();
        registerRecipes();
    }

    private static void registerItems() {
        chiselBedrockium = new ItemChiselBase(ChiselType.BEDROCKIUM);
        ItemRegistry.registerCompatItem(chiselBedrockium, "ItemChiselBedrockium", chiselBedrockiumEnabled, EnviroChecks.isExtraUtilitiesLoaded());

        chiselUnstable = new ItemChiselBase(ChiselType.UNSTABLE);
        ItemRegistry.registerCompatItem(chiselUnstable, "ItemChiselUnstable", chiselUnstableEnabled, EnviroChecks.isExtraUtilitiesLoaded());
    }

    private static void registerRecipes() {
        Item bedrockiumIngot = GameRegistry.findItem("ExtraUtilities", "bedrockiumIngot");

        ItemRecipeRegistry.addConfiguredChiselRecipe(chiselBedrockium, bedrockiumIngot, chiselBedrockiumEnabled);
        ItemRecipeRegistry.addConfiguredChiselRecipe(chiselUnstable, "ingotUnstable", chiselUnstableEnabled);
    }
}
