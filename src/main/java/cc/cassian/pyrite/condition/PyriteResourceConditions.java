package cc.cassian.pyrite.condition;

import cc.cassian.pyrite.Pyrite;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

import java.util.List;

public class PyriteResourceConditions {
    public static final ResourceConditionType<ConfigEnabledResourceCondition> CONFIG = createResourceConditionType("config", ConfigEnabledResourceCondition.CODEC);

    private static <T extends ResourceCondition> ResourceConditionType<T> createResourceConditionType(String name, MapCodec<T> codec) {
        return ResourceConditionType.create(Pyrite.of(name), codec);
    }

    /// Enable when config is set.
    public static ResourceCondition config(String... options) {
        return new ConfigEnabledResourceCondition(options);
    }

    public static ResourceCondition config(List<String> options) {
        return new ConfigEnabledResourceCondition(options);
    }

    public static void register() {
        ResourceConditions.register(CONFIG);
    }
}
