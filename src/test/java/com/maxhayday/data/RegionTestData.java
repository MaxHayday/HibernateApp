package com.maxhayday.data;

import com.maxhayday.model.Region;

import java.util.ArrayList;
import java.util.List;

public class RegionTestData {
    public static Region testDataOfRegionForCreating() {
        Region region = new Region(3L, "POL");
        return region;
    }

    public static Region testDataOfRegionForUpdating() {
        Region region = new Region(2L, "IRLL");
        return region;
    }

    public static Region expectedCreatedDataOfRegion() {
        Region region = new Region(3L, "POL");
        return region;
    }

    public static Region expectedDataOfRegionGetById() {

        Region region = new Region(1L, "UKR");
        return region;
    }

    public static List<Region> expectedDataOfAllRegions() {
        ArrayList<Region> regions = new ArrayList<>();
        Region region = new Region(1L, "UKR");
        regions.add(region);
        region = new Region(2L, "IRL");
        regions.add(region);
        return regions;
    }
}
