package thetestmod.bettercrates.enums;

import net.minecraft.util.ResourceLocation;
import thetestmod.bettercrates.BetterCrates;
import thetestmod.bettercrates.tile.*;
import thetestmod.bettercrates.tile.TileEntityBase;

import java.util.function.Supplier;

public enum EnumCrate {

    WOODEN(TileEntityWoodenCrate.class, TileEntityWoodenCrate::new, "wooden", 5F, new int[] {4, 9, 12, 84, 12, 142}, new int[]{184, 166, 178}),
    IRON(TileEntityIronCrate.class, TileEntityIronCrate::new, "iron", 5F, new int[] {7, 9, 12, 138, 12, 196}, new int[]{184, 220, 232}),
    COPPER(TileEntityCopperCrate.class, TileEntityCopperCrate::new, "copper", 5F, new int[] {5, 9, 12, 102, 12, 160}, new int[]{184, 184, 196}),
    TIN(TileEntityTinCrate.class, TileEntityTinCrate::new, "tin", 5F, new int[] {5, 9, 12, 102, 12, 160}, new int[]{184, 184, 196}),
    GOLDEN(TileEntityGoldenCrate.class, TileEntityGoldenCrate::new, "golden", 5F, new int[] {9, 9, 12, 174, 12, 232}, new int[]{184, 256, 268}),
    DIAMOND(TileEntityDiamondCrate.class, TileEntityDiamondCrate::new, "diamond", 5F, new int[] {9, 13, 48, 174, 48, 232}, new int[]{256, 256, 268}),
    OBSIDIAN(TileEntityObsidianCrate.class, TileEntityObsidianCrate::new, "obsidian", 50F, new int[] {9, 13, 48, 174, 48, 232}, new int[]{256, 256, 268});

    public static final EnumCrate[] VALUES = values();
    private Class<? extends TileEntityBase> tile;
    private Supplier<? extends TileEntityBase> supplier;
    private String name;
    private String transKey;
    private ResourceLocation background;
    private float resistance;
    private int[] slots;
    private int[] size;

    private EnumCrate(Class<? extends TileEntityBase> tile, Supplier<? extends TileEntityBase> supplier, String name, float resistance, int[] slots, int[] size) {
        this.tile = tile;
        this.supplier = supplier;
        this.name = name + "_crate";
        this.transKey = "tile." + this.name + ".name";
        this.background = new ResourceLocation(BetterCrates.MODID, "textures/gui/" + name + "_crate.png");
        this.resistance = resistance;
        this.slots = slots;
        this.size = size;
    }

    public Class<? extends TileEntityBase> getTile() {
        return tile;
    }

    public Supplier<? extends TileEntityBase> getSupplier() {
        return supplier;
    }

    public String getName() {
        return name;
    }

    public String getTransKey() {
        return transKey;
    }

    public ResourceLocation getBackground() {
        return background;
    }

    public float getResistance() {
        return resistance;
    }

    public int[] getSlots() { // columns, rows, slotPlayerInventoryX, slotPlayerInventoryY, slotPlayerHotbarX, slotPlayerHotbarY
        return slots;
    }

    public int[] getSize() { // xSize, ySize, yText
        return size;
    }
}
