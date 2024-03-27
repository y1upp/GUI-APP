/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icaguiapplication.FurnitureTest;

import com.mycompany.icaguiapplication.ChairClass;
import com.mycompany.icaguiapplication.DeskClass;
import com.mycompany.icaguiapplication.TableClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author joshu
 */
public class FurnitureTests {
    
    /*@BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }*/
    
    /*  @Test
    public void testChairClass() {
    ChairClass chair = new ChairClass(1,'A',2,50.0,true);
    assertTrue(chair.getArmRests());
    assertEquals("Chair 1 is made of A with armrests, price £50.0, quantity 2", chair.toString());
    assertEquals(2, chair.calcUnits());
    }
    
    @Test
    public void testDeskClass() {
    DeskClass desk = new DeskClass(2, 'B', 1, 100.0, 80, 60, 3);
    assertEquals(80, desk.getWidth());
    assertEquals(60, desk.getDepth());
    assertEquals(3, desk.getNumOfDrawers());
    assertEquals("Desk 2 made of B, width: 80cm, depth: 60 drawers, price: £100.0, quantity: 1", desk.toString());
    assertEquals(1, desk.calcUnits());
    }
    
    @Test
    public void testTableClass() {
    TableClass table = new TableClass(3, 'C', 3, 150.0, 120, "pedestal");
    assertEquals(120, table.getDiameter());
    assertEquals("pedestal", table.getBaseType());
    assertEquals("Table 3 made of C, diameter: 120cm, base type: pedestal, price: £150.0, quantity: 3", table.toString());
    assertEquals(3, table.calcUnits());
    }*/
}
