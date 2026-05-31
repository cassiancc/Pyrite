package cc.cassian.pyrite.condition;

import java.util.List;

import cc.cassian.pyrite.functions.ModHelpers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import org.jspecify.annotations.Nullable;

import net.minecraft.resources.RegistryOps;

import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;

public record ConfigEnabledResourceCondition(List<String> options) implements ResourceCondition {
    public static final MapCodec<ConfigEnabledResourceCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ExtraCodecs.NON_EMPTY_STRING.listOf().fieldOf("options").forGetter(ConfigEnabledResourceCondition::options)
    ).apply(instance, ConfigEnabledResourceCondition::new));

    public ConfigEnabledResourceCondition(String... configOptions) {
        this(List.of(configOptions));
    }

    @Override
    public ResourceConditionType<?> getType() {
        return PyriteResourceConditions.CONFIG;
    }

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoLookup registryInfo) {
        return ModHelpers.enabled(options);
    }
}
