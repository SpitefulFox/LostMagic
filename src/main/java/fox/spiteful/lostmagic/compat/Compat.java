package fox.spiteful.lostmagic.compat;

import fox.spiteful.lostmagic.Config;
import fox.spiteful.lostmagic.Lumberjack;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;

public class Compat {

    public static boolean bloodMagic = false;

    public static void check(){
        bloodMagic = Config.bloodMagic && Loader.isModLoaded("bloodmagic");
    }

    public static void preCompatify(){
        if(bloodMagic){
            try {
                BloodMagic.preRitual();
            }
            catch(Throwable e){
                bloodMagic = false;
                Lumberjack.log(Level.INFO, e, "Lost Magic bled out.");
            }
        }
    }

    public static void compatify(){
        if(bloodMagic){
            try {
                BloodMagic.ritual();
            }
            catch(Throwable e){
                bloodMagic = false;
                Lumberjack.log(Level.INFO, e, "Lost Magic bled out.");
            }
        }
    }

    public static Item getItem(String mod, String item) throws ItemNotFoundException {
        Item target = ForgeRegistries.ITEMS.getValue(new ResourceLocation(mod + ":" + item));
        if(target == null)
            throw new ItemNotFoundException(mod, item);
        return target;
    }

    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String mod, String item){
            super("Unable to find item " + item + " in mod " + mod + "! Are you using the correct version of the mod?");
        }
    }

}
