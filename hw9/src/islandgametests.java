import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javalib.impworld.WorldScene;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;
import tester.Tester;

// examples and tests 
class ExamplesIslands {

    Cell ce1;
    Cell ce2;
    Cell ce3;
    Cell ce4;
    Cell ce5;
    Cell ce6;
    Cell ce7;
    Cell ce8;
    Cell ce9;
    Cell ce10;
    Cell ce11;
    Cell ce12;
    Cell ce13;

    ForbiddenIslandWorld fiw; 
    ForbiddenIslandWorld fiw2; 
    ForbiddenIslandWorld fiw3;
    ForbiddenIslandWorld fiw4; 
    ForbiddenIslandWorld fiw5; 

    IList<Cell> mt;
    IList<Cell> i1;
    IList<Cell> i2;
    IList<Cell> i3;
    IList<Cell> i4;
    IList<Cell> i5;

    Iterator<Cell> ilmt; 
    Iterator<Cell> il1;
    Iterator<Cell> il2;

    Target heli; 
    Target heli1;
    Target heli2;
    Pilot pilot1;
    Pilot pilot2;

    Target helicopter1; 
    Target helicopter2; 

    Target t1;
    Target t2;
    Target t3;
    ArrayList<Target> alt1; 

    ArrayList<Cell> a17; 
    ArrayList<Cell> a18; 
    ArrayList<Cell> a19; 
    ArrayList<Cell> a20;
    ArrayList<ArrayList<Cell>> aa5; 

    void initIslands() {
        ce1 = new OceanCell(-1.0, 0, 0);
        ce2 = new OceanCell(0.0, 1, 0);
        ce3 = new OceanCell(-1.0, 2, 0);
        ce4 = new OceanCell(0.0, 0, 1);
        ce5 = new Cell(1.0, 1, 1);
        ce6 = new OceanCell(0.0, 2, 1);
        ce7 = new OceanCell(-1.0, 0, 2);
        ce8 = new OceanCell(0.0, 1, 2);
        ce9 = new OceanCell(-1.0, 2, 2); 
        ce10 = new OceanCell(0.0, 0, 0);
        ce11 = new OceanCell(0.0, 1, 0);
        ce12 = new OceanCell(0.0, 0, 1);
        ce13 = new OceanCell(0.0, 1, 1); 

        mt = new MtList<Cell>(); 
        i1 = new ConsList<Cell>(ce9, new ConsList<Cell>(ce8, 
                new ConsList<Cell>(ce7, new ConsList<Cell>(ce6, 
                        new ConsList<Cell>(ce5, new ConsList<Cell>(ce4, 
                                new ConsList<Cell>(ce3, new ConsList<Cell>(ce2, 
                                        new ConsList<Cell>(ce1, mt)))))))));
        i2 =  new ConsList<Cell>(ce13, new ConsList<Cell>(ce12, 
                new ConsList<Cell>(ce11, new ConsList<Cell>(ce10, mt)))); 
        i3 = new ConsList<Cell>(ce9, new ConsList<Cell>(ce8, 
                new ConsList<Cell>(ce7, new ConsList<Cell>(ce6, 
                        new ConsList<Cell>(ce5, new ConsList<Cell>(ce4, 
                                new ConsList<Cell>(ce3, new ConsList<Cell>(ce2, 
                                        new ConsList<Cell>(ce1, mt)))))))));
        i4 =  new ConsList<Cell>(ce13, new ConsList<Cell>(ce12, 
                new ConsList<Cell>(ce11, new ConsList<Cell>(ce10, mt)))); 

        ilmt = new IListIterator<Cell>(mt); 
        il1 = new IListIterator<Cell>(i1); 
        il2 = new IListIterator<Cell>(i2);

        helicopter1 = new HelicopterTarget(ce1); 
        helicopter2 = new HelicopterTarget(ce2); 

        heli = new HelicopterTarget(ce1); 
        pilot1 = new Pilot(ce1fix);
        pilot2 = new Pilot(ce2fix); 

        t1 = new HelicopterPieces(ce5fix);
        t2 = new HelicopterPieces(ce3);
        alt1 = new ArrayList<Target>(Arrays.asList(t1, t2, t3));

        fiw2 = new ForbiddenIslandWorld(1, 2); 
        fiw = new ForbiddenIslandWorld(ForbiddenIslandWorld.ISLAND_SIZE, 2); 
        fiw3 = new ForbiddenIslandWorld(2, 2); 
        fiw4 = new ForbiddenIslandWorld(3, 2);
        fiw5 = new ForbiddenIslandWorld(5, 0);

        a17 = new ArrayList<Cell>(Arrays.asList(
                ce1, ce2, ce3)); 
        a18 = new ArrayList<Cell>(Arrays.asList(
                ce4, ce5, ce6)); 
        a19 = new ArrayList<Cell>(Arrays.asList(
                ce7, ce8, ce9)); 
        aa5 = new ArrayList<ArrayList<Cell>>(); 
        {
            aa5.add(a17);
            aa5.add(a18);
            aa5.add(a19);
        }

        i5 = new ConsList<Cell>(ce9, new ConsList<Cell>(ce8, 
                new ConsList<Cell>(ce7, new ConsList<Cell>(ce6, 
                        new ConsList<Cell>(ce5, new ConsList<Cell>(ce4, 
                                new ConsList<Cell>(ce3, new ConsList<Cell>(ce2, 
                                        new ConsList<Cell>(ce1, mt)))))))));
    }

    ArrayList<Double> a1 = new ArrayList<Double>(Arrays.asList(
            -2.0, -1.0, 0.0, -1.0, -2.0)); 
    ArrayList<Double> a2 = new ArrayList<Double>(Arrays.asList(
            -1.0, 0.0, 1.0, 0.0, -1.0)); 
    ArrayList<Double> a3 = new ArrayList<Double>(Arrays.asList(
            0.0, 1.0, 2.0, 1.0, 0.0));
    ArrayList<Double> a4 = new ArrayList<Double>(Arrays.asList(
            -1.0, 0.0, 1.0, 0.0, -1.0)); 
    ArrayList<Double> a5 = new ArrayList<Double>(Arrays.asList(
            -2.0, -1.0, 0.0, -1.0, -2.0)); 
    ArrayList<ArrayList<Double>> aa1 = new ArrayList<ArrayList<Double>>();

    ArrayList<Double> a11 = new ArrayList<Double>(Arrays.asList(
            -1.0, 0.0, -1.0)); 
    ArrayList<Double> a12 = new ArrayList<Double>(Arrays.asList(
            0.0, 1.0, 0.0)); 
    ArrayList<Double> a13 = new ArrayList<Double>(Arrays.asList(
            -1.0, 0.0, -1.0));
    ArrayList<ArrayList<Double>> aa3 = new ArrayList<ArrayList<Double>>(); 

    Cell c1 = new OceanCell(-2.0, 0, 0); 
    Cell c2 = new OceanCell(-1.0, 1, 0); 
    Cell c3 = new OceanCell(0.0, 2, 0); 
    Cell c4 = new OceanCell(-1.0, 3, 0); 
    Cell c5 = new OceanCell(-2.0, 4, 0); 
    Cell c6 = new OceanCell(-1.0, 0, 1);
    Cell c7 = new OceanCell(0.0, 1, 1); 
    Cell c8 = new Cell(1.0, 2, 1);
    Cell c9 = new OceanCell(0.0, 3, 1); 
    Cell c10 = new OceanCell(-1.0, 4, 1); 
    Cell c11 = new OceanCell(0.0, 0, 2); 
    Cell c12 = new Cell(1.0, 1, 2); 
    Cell c13 = new Cell(2.0, 2, 2); 
    Cell c14 = new Cell(1.0, 3, 2); 
    Cell c15 = new OceanCell(0.0, 4, 2); 
    Cell c16 = new OceanCell(-1.0, 0, 3);
    Cell c17 = new OceanCell(0.0, 1, 3); 
    Cell c18 = new Cell(1.0, 2, 3);
    Cell c19 = new OceanCell(0.0, 3, 3); 
    Cell c20 = new OceanCell(-1.0, 4, 3); 
    Cell c21 = new OceanCell(-2.0, 0, 4);
    Cell c22 = new OceanCell(-1.0, 1, 4); 
    Cell c23 = new OceanCell(0.0, 2, 4);
    Cell c24 = new OceanCell(-1.0, 3, 4); 
    Cell c25 = new OceanCell(-2.0, 4, 4); 

    Cell c26 = new OceanCell(-1.0, 0, 0);
    Cell c27 = new OceanCell(0.0, 1, 0);
    Cell c28 = new OceanCell(-1.0, 2, 0);
    Cell c29 = new OceanCell(0.0, 0, 1);
    Cell c30 = new Cell(1.0, 1, 1);
    Cell c31 = new OceanCell(0.0, 2, 1);
    Cell c32 = new OceanCell(-1.0, 0, 2);
    Cell c33 = new OceanCell(0.0, 1, 2);
    Cell c34 = new OceanCell(-1.0, 2, 2);

    Cell c35 = new OceanCell(0.0, 1, 1);
    Cell c36 = new OceanCell(0.0, 0, 1);
    Cell c37 = new OceanCell(0.0, 1, 0);
    Cell c38 = new OceanCell(0.0, 0, 0);

    Cell ce1fix = new OceanCell(-1.0, 0, 0, ce1, ce1, ce2, ce4);
    Cell ce2fix = new OceanCell(0.0, 1, 0, ce1, ce2, ce3, ce5);
    Cell ce3fix = new OceanCell(-1.0, 2, 0, ce2, ce3, ce3, ce6);
    Cell ce4fix = new OceanCell(0.0, 0, 1, ce4, ce1, ce5, ce7);
    Cell ce5fix = new Cell(1.0, 1, 1, ce4, ce2, ce6, ce8);
    Cell ce6fix = new OceanCell(0.0, 2, 1, ce5, ce3, ce6, ce9);
    Cell ce7fix = new OceanCell(-1.0, 0, 2, ce7, ce5, ce8, ce7);
    Cell ce8fix = new OceanCell(0.0, 1, 2, ce7, ce6, ce9, ce8);
    Cell ce9fix = new OceanCell(-1.0, 2, 2, ce8, ce7, ce9, ce9);
    Cell ce10fix = new OceanCell(0.0, 0, 0, ce1, ce1, ce2, ce3);
    Cell ce11fix = new OceanCell(0.0, 1, 0, ce2, ce2, ce2, ce3);
    Cell ce12fix = new OceanCell(0.0, 0, 1, ce3, ce1, ce4, ce3);
    Cell ce13fix = new OceanCell(0.0, 1, 1, ce3, ce2, ce4, ce4);

    ArrayList<Cell> a6 = new ArrayList<Cell>(Arrays.asList(
            c1, c2, c3, c4, c5));
    ArrayList<Cell> a7 = new ArrayList<Cell>(Arrays.asList(
            c6, c7, c8, c9, c10));
    ArrayList<Cell> a8 = new ArrayList<Cell>(Arrays.asList(
            c11, c12, c13, c14, c15));
    ArrayList<Cell> a9 = new ArrayList<Cell>(Arrays.asList(
            c16, c17, c18, c19, c20));
    ArrayList<Cell> a10 = new ArrayList<Cell>(Arrays.asList(
            c21, c22, c23, c24, c25));
    ArrayList<ArrayList<Cell>> aa2 = new ArrayList<ArrayList<Cell>>();

    ArrayList<Cell> a14 = new ArrayList<Cell>(Arrays.asList(
            c26, c27, c28)); 
    ArrayList<Cell> a15 = new ArrayList<Cell>(Arrays.asList(
            c29, c30, c31)); 
    ArrayList<Cell> a16 = new ArrayList<Cell>(Arrays.asList(
            c32, c33, c34)); 
    ArrayList<ArrayList<Cell>> aa4 = new ArrayList<ArrayList<Cell>>();

    {
        aa1.add(a1);
        aa1.add(a2);
        aa1.add(a3);
        aa1.add(a4);
        aa1.add(a5);
        aa2.add(a6);
        aa2.add(a7);
        aa2.add(a8);
        aa2.add(a9);
        aa2.add(a10);
        aa3.add(a11);
        aa3.add(a12);
        aa3.add(a13);
        aa4.add(a14);
        aa4.add(a15);
        aa4.add(a16); 
    }

    // to test exceptions thrown
    boolean testExceptions(Tester t) {
        this.initIslands(); 
        return t.checkException(new IllegalArgumentException(
                "CANNOT MAKE MT INTO CONS"), mt, "asCons") &&
                t.checkException(new IndexOutOfBoundsException(
                        "CANNOT GET FROM AN EMPTY LIST"), mt, "get", 6) &&
                t.checkException(new IndexOutOfBoundsException(
                        "INDEX TOO SMALL: -10"), i1, "get", -10) &&
                t.checkException(new IndexOutOfBoundsException(
                        "INDEX TOO BIG: 50"), i2, "get", 50);
    }

    // to test makeCell
    void testMakeCelll(Tester t) {
        this.initIslands();
        t.checkExpect(c1.makeCell(0), new RectangleImage(10, 10, 
                OutlineMode.SOLID, new Color(0, 0, 240))); 
        t.checkExpect(c8.makeCell(0), new RectangleImage(10, 10, 
                OutlineMode.SOLID, new Color(7, 131, 7))); 
        t.checkExpect(c18.makeCell(4), new RectangleImage(10, 10, 
                OutlineMode.SOLID, new Color(15, 113, 0))); 
        t.checkExpect(ce5fix.makeCell(10), new RectangleImage(10, 10, 
                OutlineMode.SOLID, new Color(45, 83, 0)));
        t.checkExpect(new Cell(100, 1, 1).makeCell(10), new RectangleImage(10, 10, 
                OutlineMode.SOLID, new Color(255, 255, 255)));
    }

    // to test sameCell
    void testSameCell(Tester t) {
        this.initIslands();
        t.checkExpect(c1.sameCell(c2), false);
        t.checkExpect(c3.sameCell(c8), false);
        t.checkExpect(c13.sameCell(c23), false);
        t.checkExpect(c30.sameCell(ce5fix), true);
        t.checkExpect(ce10fix.sameCell(c38), true);
    }

    // to test hasNext
    void testHasNext(Tester t) {
        this.initIslands();
        t.checkExpect(ilmt.hasNext(), false);
        t.checkExpect(il1.hasNext(), true); 
        t.checkExpect(il2.hasNext(), true);
    }

    //to test next
    void testNext(Tester t) {
        this.initIslands();
        t.checkExpect(il1.next(), ce9); 
        t.checkExpect(il1.next(), ce8); 
        t.checkExpect(il1.next(), ce7); 
        t.checkExpect(il2.next(), ce13);
        t.checkExpect(il2.next(), ce12);
        t.checkExpect(il2.next(), ce11); 
    }

    // to test iterator 
    void testIterator(Tester t) {
        this.initIslands();
        t.checkExpect(mt.iterator(), new IListIterator<Cell>(mt));
        t.checkExpect(i1.iterator(), new IListIterator<Cell>(i1));
        t.checkExpect(i2.iterator(), new IListIterator<Cell>(i2));
    }

    // to test isCons
    void testIsCons(Tester t) {
        this.initIslands();
        t.checkExpect(mt.isCons(), false);
        t.checkExpect(i1.isCons(), true);
        t.checkExpect(i2.isCons(), true);
    }

    // to test asCons
    void testAsCons(Tester t) {
        this.initIslands();
        t.checkExpect(i1.asCons(), i1);
        t.checkExpect(i2.asCons(), i2);
        t.checkExpect(i3.asCons(), i3);
        t.checkExpect(i4.asCons(), i4);
    }

    // to test add
    void testAdd(Tester t) {
        this.initIslands();
        mt.add(c1);
        t.checkExpect(mt, mt); 
        i1.add(c2);
        t.checkExpect(i1, i3); 
        i2.add(c3);
        t.checkExpect(i2, i4); 
    }

    // to test get
    void testGet(Tester t) {
        this.initIslands(); 
        t.checkExpect(aa1.get(3), a2);
        t.checkExpect(aa2.get(0), a6);
        t.checkExpect(a6.get(2), c3);
        t.checkExpect(a7.get(3), c9);
        t.checkExpect(a8.get(4), c15);
    }

    // moveCaptain and moveMr tested in testOnKey below as  
    // onKey serves as a  functional tester in our game 

    // to test onCell
    void testOnCell(Tester t) {
        this.initIslands(); 
        t.checkExpect(this.pilot1.onCell(ce1fix), true); 
        t.checkExpect(this.pilot1.onCell(ce5fix), false); 
        t.checkExpect(this.pilot2.onCell(ce2fix), true); 
        t.checkExpect(this.pilot2.onCell(ce6fix), false); 
    }

    // to test makeTarget
    void testMakeTarget(Tester t) {
        this.initIslands();
        t1.makeTarget(fiw2.getEmptyScene()); 
        t.checkExpect(this.t1.c.x, 1); 
        t.checkExpect(this.t1.c.y, 1); 
        t.checkExpect(this.t1.c.height, 1.0); 
        t2.makeTarget(fiw2.getEmptyScene()); 
        t.checkExpect(this.t2.c.x, 2); 
        t.checkExpect(this.t2.c.y, 0); 
        t.checkExpect(this.t2.c.height, 0.0); 
    }

    // to test createMountainHeights  
    void testCreateMountainHeights(Tester t) {
        this.initIslands();
        t.checkExpect(fiw.createMountainHeights(4), aa1); 
        t.checkExpect(fiw.createMountainHeights(2), aa3); 
        t.checkExpect(fiw.createMountainHeights(64).get(1).get(1), -30.0);
        t.checkExpect(fiw.createMountainHeights(64).get(34).get(56), 6.0);
        t.checkExpect(fiw.createMountainHeights(64).get(22).get(36), 18.0);
        t.checkExpect(fiw.createMountainHeights(64).get(63).get(63), -30.0);
    }

    // to test createRandHeights
    void testCreateRandHeights(Tester t) {
        this.initIslands(); 
        t.checkNumRange(fiw.createRandHeights(3).get(1).get(1), -2, 2);
        t.checkNumRange(fiw.createRandHeights(3).get(1).get(2), -2, 2);
        t.checkNumRange(fiw.createRandHeights(5).get(3).get(4), -2, 2);
        t.checkNumRange(fiw.createRandHeights(5).get(2).get(4), -2, 2);
        t.checkNumRange(fiw.createRandHeights(64).get(50).get(2), -30, 30);
        t.checkNumRange(fiw.createRandHeights(64).get(5).get(63), -30, 30);
    }

    // to test randTerrainHelper
    void testRandTerrainHelper(Tester t) {
        this.initIslands();
        fiw4.randTerrainHelper(aa3, 0, 0, 0, 0); 
        t.checkExpect(aa3.get(0).get(0), -1.0); 
        t.checkExpect(aa3.get(0).get(1), 0.0); 
        t.checkExpect(aa3.get(0).get(2), -1.0); 
        t.checkExpect(aa3.get(1).get(0), 0.0); 
        t.checkExpect(aa3.get(1).get(1), 1.0); 
        t.checkExpect(aa3.get(1).get(2), 0.0); 
        t.checkExpect(aa3.get(2).get(0), -1.0); 
        t.checkExpect(aa3.get(2).get(1), 0.0); 
        t.checkExpect(aa3.get(2).get(2), -1.0); 
    }

    // to test createRandTerrainHeights
    void testCreateRandTerrainHeights(Tester t) {
        this.initIslands();
        t.checkExpect(fiw.createRandTerrainHeights(8).get(0).get(0), 0.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(0).get(8), 0.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(8).get(0), 0.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(8).get(8), 0.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(4).get(4), 4.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(0).get(0), 0.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(4).get(0), 1.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(8).get(4), 1.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(0).get(4), 1.0);
        t.checkExpect(fiw.createRandTerrainHeights(8).get(4).get(8), 0.0);
        t.checkNumRange(fiw.createRandTerrainHeights(8).get(7).get(3), -4.0, 4.0);
        t.checkNumRange(fiw.createRandTerrainHeights(8).get(8).get(4), -4.0, 4.0);
        t.checkNumRange(fiw.createRandTerrainHeights(8).get(0).get(4), -4.0, 4.0);
        t.checkNumRange(fiw.createRandTerrainHeights(8).get(0).get(0), -4.0, 4.0);
    }

    // to test underWater
    void testUnderWater(Tester t) {
        this.initIslands(); 
        t.checkExpect(fiw.underWater(c25), true); 
        t.checkExpect(fiw.underWater(c24), true); 
        t.checkExpect(fiw.underWater(c23), true); 
        t.checkExpect(fiw.underWater(c12), false); 
        t.checkExpect(fiw.underWater(c13), false); 
        t.checkExpect(fiw.underWater(c14), false); 
    }

    // to test createCells
    void testCreateCells(Tester t) {
        this.initIslands();
        t.checkExpect(fiw.createCells(4, fiw.createMountainHeights(4)), aa2); 
        t.checkExpect(fiw.createCells(2, fiw.createMountainHeights(2)), aa4);
        t.checkRange(fiw.createCells(10, 
                fiw.createRandHeights(10)).get(2).get(6).x, -11, 11); 
        t.checkRange(fiw.createCells(10, 
                fiw.createRandHeights(10)).get(9).get(7).y, -11, 11); 
        t.checkRange(fiw.createCells(16, 
                fiw.createRandHeights(16)).get(14).get(2).x, -17, 17); 
        t.checkRange(fiw.createCells(16, 
                fiw.createRandHeights(16)).get(16).get(16).y, -17, 17); 
    }

    // to test fixCells 
    void testfixCells(Tester t) {
        this.initIslands();
        fiw.fixCells(3, fiw.createCells(3, fiw.createMountainHeights(3)));        
        t.checkExpect(this.ce1, ce1fix);
        t.checkExpect(this.ce2, ce2fix);
        t.checkExpect(this.ce3, ce3fix);
        t.checkExpect(this.ce4, ce4fix);
        t.checkExpect(this.ce5, ce5fix);
        t.checkExpect(this.ce6, ce6fix);
        t.checkExpect(this.ce7, ce7fix);
        t.checkExpect(this.ce8, ce8fix);
        t.checkExpect(this.ce9, ce9fix);  
        t.checkExpect(fiw.fixCells(4, fiw.createCells(4, 
                fiw.createRandHeights(4))).get(1).get(2) == null, false); 
        t.checkExpect(fiw.fixCells(4, fiw.createCells(4, 
                fiw.createRandHeights(4))).get(4).get(4) == null, false); 
        t.checkExpect(fiw.fixCells(8, fiw.createCells(8, 
                fiw.createRandHeights(8))).get(7).get(5) == null, false); 
        t.checkExpect(fiw.fixCells(8, fiw.createCells(8, 
                fiw.createRandHeights(8))).get(6).get(2) == null, false); 
    }

    // to test flatten (method and effect)
    void testFlatten(Tester t) {
        this.initIslands();
        t.checkExpect(fiw.flatten(8, fiw.createCells(8, 
                fiw.createRandHeights(8))).get(2) == null, false); 
        t.checkExpect(fiw.flatten(32, fiw.createCells(32, 
                fiw.createRandHeights(32))).get(20) == null, false); 
        t.checkExpect(fiw.flatten(2, aa5), i5); 
    }

    // to test placeHeli
    void testPlaceHeli(Tester t) {
        this.initIslands();
        fiw2.placeHeli();
        t.checkExpect(this.helicopter1.c, this.ce1);
        this.initIslands();
        fiw3.placeHeli();
        t.checkExpect(this.helicopter2.c, this.ce2);
    }

    // to test makeSceneHelper
    // placeHeli method inside of this method is tested separately 
    void testMakeSceneHelper(Tester t) {
        this.initIslands();
        WorldScene result1 = fiw2.getEmptyScene();
        for (Cell c : i2) {
            WorldImage img = c.makeCell(fiw2.waterHeight); 
            fiw2.getEmptyScene().placeImageXY(img, c.x * 10, c.y * 10); 
        }
        t.checkExpect(result1 == fiw2.getEmptyScene(), false); 
        WorldScene result2 = fiw3.getEmptyScene();
        for (Cell c : i2) {
            WorldImage img = c.makeCell(fiw3.waterHeight); 
            fiw3.getEmptyScene().placeImageXY(img, c.x * 10, c.y * 10); 
        }
        t.checkExpect(result2 == fiw3.getEmptyScene(), false);
    }

    // to test getHighestCell
    void testGetHighestCell(Tester t) {
        this.initIslands();
        t.checkInexact(fiw.getHighestCell().height, 32.0, 8);
        t.checkInexact(fiw2.getHighestCell().height, 32.0, 8);
        t.checkInexact(fiw3.getHighestCell().height, 32.0, 8);
        t.checkInexact(fiw4.getHighestCell().height, 32.0, 8);
    }

    // to test getRandomLandPos
    void testRandomLandPos(Tester t) {
        this.initIslands();
        t.checkNumRange(this.fiw3.getRandomLandPos().x, 0, 64);
        t.checkNumRange(this.fiw3.getRandomLandPos().y, 0, 64);
        t.checkNumRange(this.fiw.getRandomLandPos().x, 0, 64);
        t.checkNumRange(this.fiw.getRandomLandPos().y, 0, 64);
    }

    // to test getCellAt
    void testGetCellAt(Tester t) {
        this.initIslands();
        t.checkInexact(this.fiw.getCellAt(0, 0).height, 0.0, 2);
        t.checkInexact(this.fiw.getCellAt(0, 64).height, 0.0, 2);
        t.checkInexact(this.fiw.getCellAt(64, 0).height, 0.0, 2);
        t.checkInexact(this.fiw.getCellAt(64, 64).height, 0.0, 2);
    }

    // to test makePiecesList
    void testMakePiecesList(Tester t) {
        this.initIslands();
        fiw5.makePiecesList(5);
        t.checkExpect(fiw5.pieces.size(), 5);
        this.initIslands();
        fiw5.makePiecesList(2);
        t.checkExpect(fiw5.pieces.size(), 2);
        this.initIslands();
        fiw5.makePiecesList(3);
        t.checkExpect(fiw5.pieces.size(), 3); 
        this.initIslands();
        fiw5.makePiecesList(1);
        t.checkExpect(fiw5.pieces.size(), 1); 
    }

    // to test makePiecesHelper
    void testMakePiecesHelper(Tester t) {
        this.initIslands();
        WorldScene result1 = fiw2.makeSceneHelper(); 
        for (Target w : fiw2.pieces) { 
            w.makeTarget(result1);
        }
        t.checkExpect(result1 == fiw2.makeSceneHelper(), false);
        WorldScene result2 = fiw4.makeSceneHelper(); 
        for (Target w : fiw4.pieces) { 
            w.makeTarget(result2);
        }
        t.checkExpect(result2 == fiw4.makeSceneHelper(), false);
    }

    // to test riseWaterHeight
    void testRiseWaterHeight(Tester t) {
        this.initIslands();
        fiw.riseWaterHeight(20);
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 1);
        this.initIslands();
        fiw.riseWaterHeight(100);
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 1);
        fiw.riseWaterHeight(100); 
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 2);
        fiw.riseWaterHeight(100); 
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 3);
    }

    // to test floodCoast
    void testFloodCoast(Tester t) {
        this.initIslands();
        fiw.floodCoast();
        t.checkExpect(fiw.board.get(64).isFlooded, true); 
        t.checkExpect(fiw.board.get(10).isFlooded, true); 
    }

    // to test makeScene
    void testMakeScene(Tester t) {
        this.initIslands();
        WorldScene scene = fiw.makePiecesHelper(4);
        scene.placeImageXY(fiw.pilot1.captain, pilot1.c.x * 10, pilot1.c.y * 10); // 10 is SCALE
        scene.placeImageXY(fiw.pilot2.mr, pilot2.c.x * 10, pilot2.c.y * 10);
        scene.placeImageXY(fiw.heli.hp, fiw.heli.c.x * 10, fiw.heli.c.y * 10);
        fiw.collection(); 
        scene.placeImageXY(new TextImage("CAPTAIN SCORE:" + fiw.pilot1.steps + 
                " " + "MR.WASD SCORE:" + fiw.pilot2.steps, 20, Color.YELLOW), 
                32 * 10, 1 * 10);
        t.checkExpect(scene == fiw.getEmptyScene(), false); 
    }

    // to test onTick
    // riseWaterHeight and floodCoast methods called in this method are tested separately 
    void testOnTick(Tester t) {
        this.initIslands();
        fiw.onTick();
        t.checkExpect(fiw.counter, 1); 
        fiw.onTick();
        t.checkExpect(fiw.counter, 2); 
        fiw.onTick();
        t.checkExpect(fiw.counter, 3); 
        fiw.onTick();
        t.checkExpect(fiw.counter, 4); 
    }

    // to test resetGame
    void testResetGame(Tester t) {
        this.initIslands();
        fiw.resetGame("m");
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 0);
        t.checkExpect(fiw.pilot1.steps, 0);
        t.checkExpect(fiw.pilot2.steps, 0);
        fiw.resetGame("r");
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 0);
        t.checkExpect(fiw.pilot1.steps, 0);
        t.checkExpect(fiw.pilot2.steps, 0);
        fiw.resetGame("t");
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 0);
        t.checkExpect(fiw.pilot1.steps, 0);
        t.checkExpect(fiw.pilot2.steps, 0);
    }

    // to test onKeyEvent
    void testOnKey(Tester t) {
        this.initIslands();
        fiw.onKeyEvent("m");
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 0);
        fiw.onKeyEvent("r");
        t.checkExpect(fiw.counter, 0);
        t.checkExpect(fiw.waterHeight, 0);
        fiw.onKeyEvent("t");
        this.initIslands();
        fiw.onKeyEvent("up");
        t.checkNumRange(fiw.pilot1.c.y, 0, 64); 
        t.checkNumRange(fiw.pilot2.c.y, 0, 64); 
        fiw.onKeyEvent("down");
        t.checkNumRange(fiw.pilot1.c.y, 0, 64); 
        t.checkNumRange(fiw.pilot2.c.y, 0, 64); 
        fiw.onKeyEvent("left");
        t.checkNumRange(fiw.pilot1.c.x, 0, 64); 
        t.checkNumRange(fiw.pilot2.c.x, 0, 64); 
        fiw.onKeyEvent("right");
        t.checkNumRange(fiw.pilot1.c.x, 0, 64);
        t.checkNumRange(fiw.pilot2.c.x, 0, 64); 
        fiw.onKeyEvent("w");
        t.checkNumRange(fiw.pilot1.c.y, 0, 64);
        t.checkNumRange(fiw.pilot2.c.y, 0, 64); 
        fiw.onKeyEvent("a");
        t.checkNumRange(fiw.pilot1.c.x, 0, 64);
        t.checkNumRange(fiw.pilot2.c.x, 0, 64); 
        fiw.onKeyEvent("s");
        t.checkNumRange(fiw.pilot1.c.y, 0, 64);
        t.checkNumRange(fiw.pilot2.c.y, 0, 64); 
        fiw.onKeyEvent("d");
        t.checkNumRange(fiw.pilot1.c.x, 0, 64);
        t.checkNumRange(fiw.pilot2.c.x, 0, 64); 
    }

    // to test collection
    void testCollection(Tester t) {
        this.initIslands();
        fiw5.collection(); 
        Cell c1 = pilot1.c; 
        Cell c2 = pilot2.c; 
        ArrayList<Target> pieces = fiw5.pieces;
        Iterator<Target> iterator = fiw5.pieces.iterator();
        while (iterator.hasNext()) {
            Target next = iterator.next();
            if (c1.sameCell(next.c) || c2.sameCell(next.c)) {
                iterator.remove(); 
            }
            t.checkExpect(pieces.size(), 0); 
        }
    }

    // to test lastScene
    void testLastScene(Tester t) {
        this.initIslands();
        fiw2.lastScene("so many tests ohmy");
        WorldScene scene1 = fiw2.makeSceneHelper(); 
        scene1.placeImageXY(new TextImage("so many tests ohmy", 40, Color.YELLOW), 
                32 * 10, 32 * 10); // 10 is SCALE
        t.checkExpect(scene1 == fiw3.getEmptyScene(), false); 
        fiw3.lastScene("Mr.WASD prefers WebCat");
        WorldScene scene2 = fiw3.makeSceneHelper(); 
        scene2.placeImageXY(new TextImage("Mr.WASD prefers WebCat", 40, Color.YELLOW), 
                32 * 10, 32 * 10); // 10 is SCALE
        t.checkExpect(scene2 == fiw3.getEmptyScene(), false); 
    }

    // to test worldEnds
    void testWorldEnds(Tester t) {
        this.initIslands();
        t.checkExpect(fiw.worldEnds() instanceof WorldEnd, true);  
    }

    // to run the game 
    void testGame(Tester t) {
        new ForbiddenIslandWorld(0, 6).bigBang(645, 645, 1); 
    }
}

