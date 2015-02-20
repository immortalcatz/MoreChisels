package tehnut.morechisels.compat;

import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;
import WayofTime.alchemicalWizardry.api.bindingRegistry.BindingRegistry;
import com.cricketcraft.chisel.init.ChiselItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tehnut.morechisels.ConfigHandler;
import tehnut.morechisels.items.ItemRegistry;
import tehnut.morechisels.items.chisel.ItemChiselBloody;
import tehnut.morechisels.items.chisel.ItemChiselBound;
import tehnut.morechisels.util.EnviroChecks;
import tehnut.morechisels.util.LogHelper;

import static tehnut.morechisels.ConfigHandler.chiselBloodyEnabled;
import static tehnut.morechisels.ConfigHandler.chiselBoundEnabled;
import static tehnut.morechisels.ConfigHandler.gemChiselWhitelist;

public class BloodMagicCompat {

    public static Item chiselBound;
    public static Item chiselBloody;

    public static void load() {
        LogHelper.info("BloodMagic compatibility is enabled and running");
        registerItems();
        registerRecipes();
    }

    private static void registerItems() {
        chiselBound = new ItemChiselBound();
        ItemRegistry.registerCompatItem(chiselBound, "ItemChiselBound", chiselBoundEnabled, EnviroChecks.isBloodMagicLoaded());

        chiselBloody = new ItemChiselBloody();
        ItemRegistry.registerCompatItem(chiselBloody, "ItemChiselBloody", chiselBloodyEnabled, EnviroChecks.isBloodMagicLoaded());
    }

    private static void registerRecipes() {
        addBindingRecipe(ChiselItems.chisel, "ingotIron", chiselBoundEnabled);
        addBindingRecipe(ChiselItems.diamondChisel, "gemDiamond", chiselBoundEnabled);

        for (int i = 0; i < ItemRegistry.chiselGem.length; i++)
            addBindingRecipe(ItemRegistry.chiselGem[i], chiselBoundEnabled);

        addAltarRecipe(ChiselItems.chisel, "ingotIron", chiselBloodyEnabled);
        addAltarRecipe(ChiselItems.diamondChisel, "gemDiamond", chiselBloodyEnabled);

        for (int i = 0; i < ItemRegistry.chiselGem.length; i++)
            addAltarRecipe(ItemRegistry.chiselGem[i], chiselBloodyEnabled);
    }

    private static void addAltarRecipe(Item chisel, boolean chiselEnabled) {
        if (chiselEnabled)
            AltarRecipeRegistry.registerAltarRecipe(new ItemStack(chiselBloody), new ItemStack(chisel), 2, 1000, 10, 10, false);
    }

    private static void addAltarRecipe(Item chisel, String type, boolean chiselEnabled) {
        if (!OreDictionary.getOres(type).isEmpty() && chiselEnabled)
            AltarRecipeRegistry.registerAltarRecipe(new ItemStack(chiselBloody), new ItemStack(chisel), 2, 1000, 10, 10, false);
    }

    private static void addBindingRecipe(Item chisel,boolean chiselEnabled) {
        if (chiselEnabled)
            BindingRegistry.registerRecipe(new ItemStack(chiselBound), new ItemStack(chisel));
    }

    private static void addBindingRecipe(Item chisel, String type, boolean chiselEnabled) {
        if (!OreDictionary.getOres(type).isEmpty() && chiselEnabled)
            BindingRegistry.registerRecipe(new ItemStack(chiselBound), new ItemStack(chisel));
    }
}
