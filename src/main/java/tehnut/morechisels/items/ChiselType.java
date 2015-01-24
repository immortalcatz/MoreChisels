package tehnut.morechisels.items;

import static tehnut.morechisels.ConfigHandler.*;

import java.util.Locale;

public enum ChiselType {

    RUBY(durabilityRuby),
    SAPPHIRE(durabilitySapphire),
    EMERALD(durabilityEmerald),
    FLUXED(),
    BLOODY(durabilityBloody),
    BOUND();

    /**
     * Used for all types of durability (RF, EU, etc)
     */
    public final int durability;

    private ChiselType(int dura) {
        this.durability = dura;
    }

    private ChiselType() {
        this(100);
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
