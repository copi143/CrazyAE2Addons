package net.oktawia.crazyae2addons.menus;

import appeng.api.upgrades.IUpgradeableObject;
import appeng.menu.AEBaseMenu;
import appeng.menu.guisync.GuiSync;
import appeng.menu.implementations.UpgradeableMenu;
import net.minecraft.world.entity.player.Inventory;
import net.oktawia.crazyae2addons.parts.DataExtractorPart;
import net.oktawia.crazyae2addons.defs.regs.CrazyMenuRegistrar;

public class DataExtractorMenu extends AEBaseMenu {

    private final DataExtractorPart host;
    @GuiSync(874)
    public String available;
    @GuiSync(875)
    public Integer selected;
    @GuiSync(876)
    public String valueName;
    @GuiSync(743)
    public Integer page = 0;
    @GuiSync(421)
    public Integer delay;
    @GuiSync(426)
    public Boolean updateGui = false;

    public String ACTION_SYNC_SELECTED = "actionSyncSelected";
    public String ACTION_GET_DATA = "actionGetData";
    public String ACTION_SAVE_NAME = "actionSaveName";
    public String ACTION_SAVE_DELAY = "actionSaveDelay";

    public DataExtractorMenu(int id, Inventory ip, DataExtractorPart host) {
        super(CrazyMenuRegistrar.DATA_EXTRACTOR_MENU.get(), id, ip, host);
        host.setMenu(this);
        this.host = host;
        registerClientAction(ACTION_SYNC_SELECTED, Integer.class, this::syncValue);
        registerClientAction(ACTION_GET_DATA, this::getData);
        registerClientAction(ACTION_SAVE_NAME, String.class, this::saveName);
        registerClientAction(ACTION_SAVE_DELAY, Integer.class, this::saveDelay);
        this.available = String.join("|", host.available);
        this.selected = host.selected;
        this.valueName = host.valueName;
        this.delay = host.delay;
        createPlayerInventorySlots(ip);
    }

    public void syncValue(Integer value) {
        host.selected = value;
        this.selected = value;
        if (isClientSide()){
            sendClientAction(ACTION_SYNC_SELECTED, value);
        }
    }

    public void getData(){
        host.extractPossibleData();
        this.available = String.join("|", host.available);
        this.selected = host.selected;
        this.updateGui = true;
        if (isClientSide()){
            sendClientAction(ACTION_GET_DATA);
        }
    }

    public void saveName(String name) {
        host.valueName = name;
        this.valueName = name;
        if (isClientSide()){
            sendClientAction(ACTION_SAVE_NAME, name);
        }
    }

    public void saveDelay(Integer delay) {
        host.delay = delay;
        this.delay = delay;
        if (isClientSide()){
            sendClientAction(ACTION_SAVE_DELAY, delay);
        }
    }
}
