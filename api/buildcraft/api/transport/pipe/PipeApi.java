package buildcraft.api.transport.pipe;

import buildcraft.api.mj.MjAPI;
import buildcraft.api.transport.IInjectable;
import buildcraft.api.transport.IStripesRegistry;
import buildcraft.api.transport.pluggable.IPluggableRegistry;
import buildcraft.api.transport.pluggable.PipePluggable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.IdentityHashMap;
import java.util.Map;

/** The central holding class for all pipe related registers and methods. */
public final class PipeApi {
    public static IPipeRegistry pipeRegistry;
    public static IPluggableRegistry pluggableRegistry;
    public static IStripesRegistry stripeRegistry;
    public static IPipeExtensionManager extensionManager;
    public static PipeFlowType flowStructure;
    public static PipeFlowType flowItems;
    public static PipeFlowType flowFluids;
    public static PipeFlowType flowPower;

    /** The default transfer information used if a pipe definition has not been registered. Note that this is replaced
     * by BuildCraft Transport to config-defined values. */
    public static FluidTransferInfo fluidInfoDefault = new FluidTransferInfo(20, 10);

    /** The default transfer information used if a pipe definition has not been registered. Note that this is replaced
     * by BuildCraft Transport to config-defined values. */
    public static PowerTransferInfo powerInfoDefault = PowerTransferInfo.createFromResistance(8 * MjAPI.MJ, MjAPI.MJ / 32, false);

    public static final Map<PipeDefinition, FluidTransferInfo> fluidTransferData = new IdentityHashMap<>();
    public static final Map<PipeDefinition, PowerTransferInfo> powerTransferData = new IdentityHashMap<>();

    @Nonnull
    public static final Capability<IPipeHolder> CAP_PIPE_HOLDER = CapabilityManager.get(new CapabilityToken<>() {
    });

    @Nonnull
    public static final Capability<IPipe> CAP_PIPE = CapabilityManager.get(new CapabilityToken<>() {
    });

    @Nonnull
    public static final Capability<PipePluggable> CAP_PLUG = CapabilityManager.get(new CapabilityToken<>() {
    });

    @Nonnull
    public static final Capability<IInjectable> CAP_INJECTABLE = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static FluidTransferInfo getFluidTransferInfo(PipeDefinition def) {
        FluidTransferInfo info = fluidTransferData.get(def);
        if (info == null) {
            return fluidInfoDefault;
        } else {
            return info;
        }
    }

    public static PowerTransferInfo getPowerTransferInfo(PipeDefinition def) {
        PowerTransferInfo info = powerTransferData.get(def);
        if (info == null) {
            return powerInfoDefault;
        } else {
            return info;
        }
    }

    public static class FluidTransferInfo {
        /** Controls the maximum amount of fluid that can be transferred around and out of a pipe per tick. Note that
         * this does not affect the flow rate coming into the pipe. */
        public final int transferPerTick;

        /** Controls how long the pipe should delay incoming fluids by. Minimum value is 1, because of the way that
         * fluids are handled internally. This value is multiplied by the fluids viscosity, and divided by 100 to give
         * the actual delay. */
        public final double transferDelayMultiplier;

        public FluidTransferInfo(int transferPerTick, int transferDelay) {
            this.transferPerTick = transferPerTick;
            if (transferDelay <= 0) {
                transferDelay = 1;
            }
            this.transferDelayMultiplier = transferDelay;
        }
    }

    public static class PowerTransferInfo {
        public final long transferPerTick;
        public final long lossPerTick;
        /** The percentage resistance per tick. Should be a value between 0 and {@link MjAPI#MJ} */
        public final long resistancePerTick;
        public final boolean isReceiver;

        /** Sets resistancePerTick to be equal to lossPerTick when full power is being transferred, scaling down to 0.
         *
         * @param transferPerTick
         * @param lossPerTick
         * @param isReceiver */
        public static PowerTransferInfo createFromLoss(long transferPerTick, long lossPerTick, boolean isReceiver) {
            return new PowerTransferInfo(transferPerTick, lossPerTick, lossPerTick * MjAPI.MJ / transferPerTick, isReceiver);
        }

        /** Sets lossPerTick to be equal to resistancePerTick when full power is being transferred.
         *
         * @param transferPerTick
         * @param resistancePerTick
         * @param isReceiver */
        public static PowerTransferInfo createFromResistance(long transferPerTick, long resistancePerTick, boolean isReceiver) {
            return new PowerTransferInfo(transferPerTick, resistancePerTick, resistancePerTick * transferPerTick / MjAPI.MJ, isReceiver);
        }

        public PowerTransferInfo(long transferPerTick, long lossPerTick, long resistancePerTick, boolean isReceiver) {
            if (transferPerTick < 10) {
                transferPerTick = 10;
            }
            this.transferPerTick = transferPerTick;
            this.lossPerTick = lossPerTick;
            this.resistancePerTick = resistancePerTick;
            this.isReceiver = isReceiver;
        }
    }

    // Internals

    @SubscribeEvent
    public static void registerCapability(RegisterCapabilitiesEvent event) {
        event.register(IPipe.class);
        event.register(PipePluggable.class);
        event.register(IPipeHolder.class);
        event.register(IInjectable.class);
    }
}
