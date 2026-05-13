//? fabric {
package cc.cassian.pyrite.fabric.datagen;

import cc.cassian.pyrite.fabric.datagen.providers.NeoForgeDataMapProvider;
import cc.cassian.pyrite.fabric.datagen.providers.PyriteBlockTagProvider;
import cc.cassian.pyrite.fabric.datagen.providers.PyriteItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PyriteDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		PyriteBlockTagProvider pyriteBlockTagProvider = pack.addProvider(PyriteBlockTagProvider::new);
		pack.addProvider((output, registryLookupFuture) -> new PyriteItemTagProvider(output, registryLookupFuture, pyriteBlockTagProvider));
		pack.addProvider((output, registryLookupFuture) -> new NeoForgeDataMapProvider(output));
	}
}
//?}