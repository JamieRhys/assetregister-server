package com.jre.assetregister.database.repositories;

import com.jre.assetregister.database.entities.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AssetRepository extends CrudRepository<Asset, Long> {
    Asset findByAssetTag(@Param("asset_tag") long assetTag);
}
