package cd4017be.rs_ctr2;

import static cd4017be.lib.block.BlockTE.flags;
import static cd4017be.lib.property.PropertyOrientation.HOR_AXIS;
import static cd4017be.rs_ctr2.Main.*;
import static net.minecraftforge.client.model.ModelLoader.addSpecialModel;

import java.util.function.Supplier;

import cd4017be.api.grid.port.*;
import cd4017be.rs_ctr2.container.*;
import cd4017be.rs_ctr2.container.gui.GuiRAM;
import cd4017be.rs_ctr2.item.*;
import cd4017be.rs_ctr2.part.*;
import cd4017be.rs_ctr2.render.SignalProbeRenderer;
import cd4017be.rs_ctr2.tileentity.*;
import cd4017be.lib.block.BlockTE;
import cd4017be.lib.block.OrientedBlock;
import cd4017be.lib.item.DocumentedBlockItem;
import cd4017be.lib.item.DocumentedItem;
import cd4017be.lib.part.OrientedPart;
import cd4017be.lib.templates.BaseSound;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

/**Registers all added game content.
 * Elements that belong to the same feature typically use the same registry name
 * so they are differentiated by case to avoid variable name conflicts:<br>
 * Block -> ALL_UPPERCASE<br>
 * Item -> all_lowercase<br>
 * TileEntity -> stored in {@link BlockTE#tileType}<br>
 * ContainerType -> fIRST_LOWERCASE_REMAINING_UPPERCASE
 * @author CD4017BE */
@EventBusSubscriber(modid = Main.ID, bus = Bus.MOD)
@ObjectHolder(value = Main.ID)
public class Content {

	// blocks:
	public static final OrientedBlock<AutoCrafter> AUTOCRAFT = null;
	public static final OrientedBlock<BlockBreaker> BLOCK_BREAKER = null;
	public static final OrientedBlock<ItemPlacer> ITEM_PLACER = null;

	// items:
	public static final DocumentedBlockItem
	autocraft = null, block_breaker = null, item_placer = null;
	public static final DocumentedBlockItem frame = null;

	public static final SignalProbeItem probe = null;
	public static final CableItem
	data_cable = null, power_cable = null, item_cable = null, fluid_cable = null, block_cable = null;
	public static final OrientedPartItem
	analog_in = null, logic_in = null, analog_out = null, logic_out = null,
	comp_in = null, power_io = null, item_io = null, fluid_io = null, block_io = null,
	splitter = null, power_splitter = null, item_splitter = null, fluid_splitter = null, block_splitter = null,
	not_gate = null, clock = null, constant = null, and_filter = null,
	or_gate = null, and_gate = null, nor_gate = null, nand_gate = null,
	xor_gate = null, schmitt_trigger = null, delay = null, comparator = null,
	sr_latch = null, data_mux = null, clamp_gate = null, or_buffer = null,
	bit_shift = null, sum_gate = null, product_gate = null, division_gate = null,
	neg_gate = null, counter = null, mem_read = null, mem_write = null,
	transformer = null, item_mover = null, fluid_mover = null,
	switcH = null, led = null, switch_array = null, led_array = null,
	_7segment = null, bcd_converter = null;
	public static final MultiblockItem<Battery> battery = null;
	public static final MultiblockItem<SolarCell> solarcell = null;
	public static final MultiblockItem<Memory> memory = null;

	// containers:
	public static final ContainerType<ContainerConstant> cONSTANT = null;
	public static final ContainerType<ContainerAutoCraft> aUTOCRAFT = null;
	public static final ContainerType<ContainerMemory> mEMORY = null;
	public static final ContainerType<ContainerItemPlacer> iTEM_PLACER = null;

	//sounds:
	public static final BaseSound SWITCH_FLIp = null;

	@SubscribeEvent
	public static void registerBlocks(Register<Block> ev) {
		Properties p = Properties.of(Material.STONE).strength(1.5F);
		ev.getRegistry().registerAll(
			new OrientedBlock<>(p, flags(AutoCrafter.class), HOR_AXIS).setRegistryName(rl("autocraft")),
			new OrientedBlock<>(p, flags(BlockBreaker.class), HOR_AXIS).setRegistryName(rl("block_breaker")),
			new OrientedBlock<>(p, flags(ItemPlacer.class), HOR_AXIS).setRegistryName(rl("item_placer"))
		);
	}

	@SubscribeEvent
	public static void registerItems(Register<Item> ev) {
		// use redstone tab so recipes don't appear under miscellaneous
		Item.Properties rs = new Item.Properties().tab(ItemGroup.TAB_REDSTONE);
		Item.Properties probe = new Item.Properties().tab(ItemGroup.TAB_TOOLS)
		.stacksTo(1).setISTER(()-> SignalProbeRenderer::new);
		Item.Properties p = new Item.Properties().tab(CREATIVE_TAB);
		ev.getRegistry().registerAll(
			new DocumentedBlockItem(AUTOCRAFT, p),
			new DocumentedBlockItem(BLOCK_BREAKER, p),
			new DocumentedBlockItem(ITEM_PLACER, p),
			new SignalProbeItem(probe).tab(CREATIVE_TAB).setRegistryName(rl("probe")),
			new CableItem(rs, ISignalReceiver.TYPE_ID).tab(CREATIVE_TAB).setRegistryName(rl("data_cable")),
			new CableItem(rs, IEnergyAccess.TYPE_ID).tab(CREATIVE_TAB).setRegistryName(rl("power_cable")),
			new CableItem(rs, IInventoryAccess.TYPE_ID).tab(CREATIVE_TAB).setRegistryName(rl("item_cable")),
			new CableItem(rs, IFluidAccess.TYPE_ID).tab(CREATIVE_TAB).setRegistryName(rl("fluid_cable")),
			new CableItem(rs, IBlockSupplier.TYPE_ID).tab(CREATIVE_TAB).setRegistryName(rl("block_cable")),
			orientedPart("analog_in", rs, AnalogIn::new),
			orientedPart("logic_in", rs, LogicIn::new),
			orientedPart("comp_in", rs, ComparatorIn::new),
			orientedPart("analog_out", rs, AnalogOut::new),
			orientedPart("logic_out", rs, LogicOut::new),
			orientedPart("power_io", rs, PowerIO::new),
			orientedPart("item_io", rs, ItemIO::new),
			orientedPart("fluid_io", rs, FluidIO::new),
			orientedPart("block_io", rs, BlockIO::new),
			orientedPart("splitter", rs, Splitter::new),
			orientedPart("power_splitter", rs, SplitterP::new),
			orientedPart("item_splitter", rs, SplitterI::new),
			orientedPart("fluid_splitter", rs, SplitterF::new),
			orientedPart("block_splitter", rs, SplitterB::new),
			orientedPart("not_gate", rs, NotGate::new),
			orientedPart("clock", rs, Clock::new),
			orientedPart("constant", rs, Constant::new),
			orientedPart("and_filter", rs, AndFilter::new),
			orientedPart("or_gate", rs, OrGate::new),
			orientedPart("and_gate", rs, AndGate::new),
			orientedPart("nor_gate", rs, NorGate::new),
			orientedPart("nand_gate", rs, NandGate::new),
			orientedPart("xor_gate", rs, XorGate::new),
			orientedPart("schmitt_trigger", rs, SchmittTrigger::new),
			orientedPart("delay", rs, Delay::new),
			orientedPart("comparator", rs, Comparator::new),
			orientedPart("sr_latch", rs, SRLatch::new),
			orientedPart("data_mux", rs, DataMux::new),
			orientedPart("clamp_gate", rs, ClampGate::new),
			orientedPart("or_buffer", rs, OrBuffer::new),
			orientedPart("bit_shift", rs, BitShift::new),
			orientedPart("sum_gate", rs, SumGate::new),
			orientedPart("product_gate", rs, MultiplyGate::new),
			orientedPart("division_gate", rs, DivisionGate::new),
			orientedPart("neg_gate", rs, Negate::new),
			orientedPart("counter", rs, Counter::new),
			orientedPart("mem_read", rs, MemRead::new),
			orientedPart("mem_write", rs, MemWrite::new),
			orientedPart("transformer", rs, Transformer::new),
			orientedPart("item_mover", rs, ItemMover::new).tooltipArgs(SERVER_CFG.move_item),
			orientedPart("fluid_mover", rs, FluidMover::new).tooltipArgs(SERVER_CFG.move_fluid),
			orientedPart("switch", rs, Switch::new),
			orientedPart("led", rs, LED::new),
			orientedPart("switch_array", rs, SwitchArray::new),
			orientedPart("led_array", rs, LEDArray::new),
			orientedPart("_7segment", rs, _7Segment::new),
			orientedPart("bcd_converter", rs, BCDConverter::new),
			new MultiblockItem<>(p, Battery::new).tooltipArgs(SERVER_CFG.battery_cap).setRegistryName(rl("battery")),
			new MultiblockItem<>(p, SolarCell::new).tooltipArgs(SERVER_CFG.solar_power, SERVER_CFG.daytime).setRegistryName(rl("solarcell")),
			new MultiblockItem<>(p, Memory::new).tooltipArgs(SERVER_CFG.memory_size).setRegistryName(rl("memory")),
			item(p, "corerope1"), item(p, "corerope2")
		);
	}

	private static OrientedPartItem orientedPart(
		String id, Item.Properties p, Supplier<OrientedPart> factory
	) {
		OrientedPartItem item = new OrientedPartItem(p, factory);
		item.setRegistryName(rl(id));
		return item;
	}

	private static Item item(Item.Properties p, String id) {
		return new DocumentedItem(p).setRegistryName(rl(id));
	}

	@SubscribeEvent
	public static void registerTileEntities(Register<TileEntityType<?>> ev) {
		ev.getRegistry().registerAll(
			AUTOCRAFT.makeTEType(AutoCrafter::new),
			BLOCK_BREAKER.makeTEType(BlockBreaker::new),
			ITEM_PLACER.makeTEType(ItemPlacer::new)
		);
	}

	@SubscribeEvent
	public static void registerContainers(Register<ContainerType<?>> ev) {
		ev.getRegistry().registerAll(
			IForgeContainerType.create(ContainerConstant::new).setRegistryName(rl("constant")),
			IForgeContainerType.create(ContainerAutoCraft::new).setRegistryName(rl("autocraft")),
			IForgeContainerType.create(ContainerMemory::new).setRegistryName(rl("memory")),
			IForgeContainerType.create(ContainerItemPlacer::new).setRegistryName(rl("item_placer"))
		);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void setupClient(FMLClientSetupEvent ev) {
		ScreenManager.register(cONSTANT, ContainerConstant::setupGui);
		ScreenManager.register(aUTOCRAFT, ContainerAutoCraft::setupGui);
		ScreenManager.register(mEMORY, GuiRAM::new);
		ScreenManager.register(iTEM_PLACER, ContainerItemPlacer::setupGui);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void registerModels(ModelRegistryEvent ev) {
		addSpecialModel(SignalProbeRenderer.baseModel(probe));
		for (ResourceLocation loc : Cable.MODELS)
			if (loc != null) addSpecialModel(loc);
		addSpecialModel(Battery.MODEL);
		addSpecialModel(SolarCell.MODEL);
		addSpecialModel(Memory.MODEL);
		addSpecialModel(Switch.BASE);
		addSpecialModel(Switch.OFF);
		addSpecialModel(Switch.ON);
		addSpecialModel(LED.LED);
		ExtendablePart.registerModels(switch_array);
		ExtendablePart.registerModels(_7segment);
		for (int i = 0; i < 16; i++)
			addSpecialModel(_7Segment.MODELS[i] = rl("part/7seg" + Integer.toHexString(i)));
	}

	@SubscribeEvent
	public static void registerSounds(Register<SoundEvent> ev) {
		ev.getRegistry().registerAll(
			new BaseSound(rl("switch_flip"))
		);
	}

}
