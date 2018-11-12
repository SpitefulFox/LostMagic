package fox.spiteful.lostmagic.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import fox.spiteful.lostmagic.LostMagic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRingNutrition extends Item implements IBauble {

    public ItemRingNutrition(){
        this.setCreativeTab(LostMagic.tab);
        this.setRegistryName("ring_nutrition");
        this.setUnlocalizedName("lostmagic.ring_nutrition");
        this.setMaxStackSize(1);
    }

    @Override
    public BaubleType getBaubleType(ItemStack var1){
        return BaubleType.RING;
    }
}
