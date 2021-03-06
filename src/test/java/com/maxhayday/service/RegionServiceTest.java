package com.maxhayday.service;

import com.maxhayday.Connection;
import com.maxhayday.model.Region;
import com.maxhayday.repository.RegionRepository;
import com.maxhayday.repository.hbn.HBRegionRepositoryImpl;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.maxhayday.data.RegionTestData.*;
import static org.junit.jupiter.api.Assertions.*;


class RegionServiceTest {
    private RegionRepository regionRepository;
    private RegionService regionService;
    private Region region;
    private Transaction transaction = null;

    public RegionServiceTest() throws SQLException, IOException, ClassNotFoundException {
        regionRepository = new HBRegionRepositoryImpl();
        regionService = new RegionService();
    }

    @BeforeAll
    static void setUp() {
        Connection.buildSession();
    }


    @Test
    void getById() throws ClassNotFoundException, SQLException, ParseException, IOException {
        region = regionService.getById(1L);
        assertEquals(expectedDataOfRegionGetById(), region);
    }

    @Test
    void save() throws SQLException, IOException, ClassNotFoundException {
            region = regionService.save(testDataOfRegionForCreating());
            assertEquals(expectedCreatedDataOfRegion(), region);
    }

    @Test
    void update() throws SQLException, IOException, ClassNotFoundException {
            region = regionService.update(testDataOfRegionForUpdating());
            assertEquals(testDataOfRegionForUpdating(), region);
    }

    @Test
    void getAll() throws ClassNotFoundException, SQLException, ParseException, IOException {
            List<Region> regions = regionService.getAll();
            assertEquals(expectedDataOfAllRegions(), regions);
    }

    @Test
    void deleteById() throws SQLException, IOException, ClassNotFoundException, ParseException {
            regionService.deleteById(1L);
            assertNull(regionService.getById(1l));
    }

    @AfterAll
    public static void tearDown() {
        if (Connection.sessionFactory != null) Connection.sessionFactory.close();
    }
}