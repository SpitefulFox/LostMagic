package fox.spiteful.lostmagic.items;

import fox.spiteful.lostmagic.LostMagic;
import net.minecraft.item.Item;
import thaumcraft.api.items.IScribeTools;
import WayofTime.bloodmagic.iface.IBindable;


public class ItemScribeBlood extends Item implements IScribeTools, IBindable {

    public ItemScribeBlood(){
        this.setCreativeTab(LostMagic.tab);
        this.setRegistryName("scribe_blood");
        this.setUnlocalizedName("lostmagic.scribe_blood");
    }

}
