package fox.spiteful.lostmagic.items;
import fox.spiteful.lostmagic.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class LostItems {

    public static Item shovelPurifier;
    public static Item axeSkulltaker;
    public static Item scribeBlood;
    public static Item ringNutrition;
    public static Item alarmMoon;
    public static Item scribeFlux;

    public static void findItems(){
        shovelPurifier = new ItemShovelPurifier();
        ForgeRegistries.ITEMS.register(shovelPurifier);
        ringNutrition = new ItemRingNutrition();
        ForgeRegistries.ITEMS.register(ringNutrition);
    }

}
