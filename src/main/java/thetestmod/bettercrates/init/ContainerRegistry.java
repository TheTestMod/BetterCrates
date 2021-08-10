package thetestmod.bettercrates.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.container.ContainerBase9Rows;

public class ContainerRegistry {

    public static MenuType<ContainerBase9Rows> CONTAINER_9_ROWS = IForgeContainerType.create(ContainerBase9Rows::new);
    public static MenuType<ContainerBase13Rows> CONTAINER_13_ROWS = IForgeContainerType.create(ContainerBase13Rows::new);
}
