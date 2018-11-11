package fox.spiteful.lostmagic;

import fox.spiteful.lostmagic.compat.Compat;
import fox.spiteful.lostmagic.items.LostItems;
import fox.spiteful.lostmagic.research.LostResearch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.resources.ItemCrystalEssence;

@Mod(modid = "lostmagic", name = "Lost Magic", version = "1.0", dependencies = "required-after:thaumcraft;after:bloodmagic")
public class LostMagic
{


    @Mod.Instance("ridiculousworld")
    public LostMagic instance;
    @SidedProxy(
        clientSide = "fox.spiteful.lostmagic.client.ClientProxy",
        serverSide = "fox.spiteful.lostmagic.CommonProxy"
    )
    public static CommonProxy proxy;

    public static CreativeTabs tab = new CreativeTabs("lostmagic") {
        @Override
        public ItemStack getTabIconItem() {
            return getCrystal(Aspect.FLUX, 1);
        }
    };

    @EventHandler
    public void emotion(FMLPreInitializationEvent event)
    {
        instance = this;
        Config.configurate(event.getSuggestedConfigurationFile());
        LostItems.findItems();
        Compat.check();
        Compat.preCompatify();
        proxy.doTheRenderThing();
    }

    @EventHandler
    public void intellect(FMLInitializationEvent event)
    {
        LostResearch.findResearch();
        LostResearch.findRecipes();
        Compat.compatify();
    }

    @EventHandler
    public void will(FMLPostInitializationEvent event)
    {

    }

    public static ItemStack getCrystal(Aspect asp, int quantity){
        ItemStack is = new ItemStack(ItemsTC.crystalEssence, quantity);
        ((ItemCrystalEssence) ItemsTC.crystalEssence).setAspects(is, new AspectList().add(asp, 1));
        return is;
    }
}
