// Assignment 1

/* Jain, Raveena 
 * raveena97
 * Sai, Sreeya
 * ssai123
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator; 
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

/* NOTE: 
 * Our ArrayList<ArrayList<T>> methods take in an int size so we can test 
 * on smaller array lists. 
 * When we run bigBang on the ForbiddenIslandGame class, we input the given
 * ForbiddenIslandWorld.ISLANDSIZE constant. 
 */

//to represent a single square of the game area
class Cell {
    double height; // represents absolute height of this cell, in feet
    int x; // In logical coordinates, with the origin at the top-left corner
    int y; // In logical coordinates, with the origin at the top-left corner
    Cell left;
    Cell top;
    Cell right; 
    Cell bottom; 
    boolean isFlooded; // reports whether this cell is flooded or not

    // constructor 
    Cell(double height, int x, int y, Cell left, Cell top, Cell right, 
            Cell bottom) {
        this.height = height; 
        this.x = x; 
        this.y = y; 
        this.left = null; 
        this.top = null; 
        this.right = null; 
        this.bottom = null; 
        this.isFlooded = false;
    }

    // convenience constructor 
    Cell(double height, int x, int y) {
        this.height = height; 
        this.x = x; 
        this.y = y; 
        this.isFlooded = false;
    }

    // to draw a Cell
    WorldImage makeCell(int waterheight) {
        int redScale;
        int greenScale;
        int blueScale;
        // change from blue to black
        if (this.height <= waterheight && isFlooded) {
            redScale = 0; 
            greenScale = 0;
            blueScale = 255 - (waterheight - (int)height) * 5;
        }
        // change from green to red 
        else if (this.height < waterheight && !isFlooded) {
            redScale = (waterheight - (int)height) * 5; 
            greenScale = 128 - (waterheight - (int)height) * 5;
            blueScale = 0;
        }
        // initial green to white scale
        else {
            redScale = (int)height * 7; 
            greenScale = 128 + (int)height * 3;
            blueScale = (int)height * 7;
        }
        if (redScale > 255) {
            redScale = 255;
        }
        if (blueScale > 255) {
            blueScale = 255;   
        }
        if (greenScale > 255) {
            greenScale = 255;
        } 
        return new RectangleImage(10, 
                10, OutlineMode.SOLID, 
                new Color(redScale, greenScale, blueScale)); 
    }


    // are two cells at the same location?
    boolean sameCell(Cell c) {
        return this.x == c.x && this.y == c.y;
    }
}

//to represent a single square of the ocean area
class OceanCell extends Cell {

    // constructor 
    OceanCell(double height, int x, int y) {
        super(0, x, y);
        this.isFlooded = true; 
    }

    // convenience constructor 
    OceanCell(double height, int x, int y, Cell left, Cell top, Cell right, 
            Cell bottom) {
        super(0, x, y);
        this.isFlooded = true; 
    }

    // to draw an OceanCell
    WorldImage makeCell(int waterheight) {
        return new RectangleImage(10, 
                10, OutlineMode.SOLID, 
                new Color(0, 0, 240)); 
    }
} 

// to represent an IListIterator of type T
class IListIterator<T> implements Iterator<T> {
    IList<T> items;

    // constructor 
    IListIterator(IList<T> items) {
        this.items = items;
    }

    // does this sequence have at least one more value?
    public boolean hasNext() {
        return this.items.isCons();
    }

    // get the next value in this sequence
    // EFFECT: advance the iterator to the subsequent value
    public T next() {
        ConsList<T> itemsAsCons = this.items.asCons();
        T answer = itemsAsCons.first;
        this.items = itemsAsCons.rest;
        return answer;
    }

    // throws exception: do not try to remove item
    public void remove() {
        throw new UnsupportedOperationException("Don't do this!");
    }
}

//to represent a list of type T
interface IList<T> extends Iterable<T> {

    // to iterate over an IList 
    Iterator<T> iterator();

    // is this a Cons?
    boolean isCons();

    // casts this IList<T> as ConsList<T> 
    ConsList<T> asCons();

    // EFFECT: to add the given T to this IList<T>
    void add(T t); 

    // to get the item at the given index
    T get(int i);
}

// to represent an empty list of type T
class MtList<T> implements IList<T> {

    // to iterate over a MtList 
    public Iterator<T> iterator() {
        return new IListIterator<T>(this);
    }

    // is this a Cons?
    public boolean isCons() {
        return false; 
    }

    // returns an error because a MtList<T> cannot be a ConsList<T>
    public ConsList<T> asCons() {
        throw new IllegalArgumentException("CANNOT MAKE MT INTO CONS");
    }

    // EFFECT: to add the given T to this MtList
    public void add(T t) {
        new ConsList<T>(t, this);
    }

    // throws exception: MtList does not contain any items
    public T get(int i) {
        throw new IndexOutOfBoundsException("CANNOT GET FROM AN EMPTY LIST");
    }
}

// to represent a non-empty list of type T
class ConsList<T> implements IList<T> {
    T first;
    IList<T> rest;

    // constructor
    ConsList(T first, IList<T> rest) {
        this.first = first;
        this.rest = rest;
    }

    // to iterate over a ConsList
    public Iterator<T> iterator() {
        return new IListIterator<T>(this);
    }

    // is this a ConsList? 
    public boolean isCons() {
        return true; 
    }

    // casts this as a ConsList<T>    
    public ConsList<T> asCons() {
        return this;
    }

    // EFFECT: to add the given T to this ConsList
    public void add(T t) {
        new ConsList<T>(t, this);
    }

    // throws exception if the given index is out of bounds
    // returns the T at the given index
    public T get(int index) {
        int i = index;
        if (i < 0) {
            throw new IndexOutOfBoundsException("INDEX TOO SMALL: " + index);
        }
        for (T t : this) {
            if (i == 0) {
                return t;
            }
            else {
                i = i - 1;
            }
        }
        throw new IndexOutOfBoundsException("INDEX TOO BIG: " + index);
    }
}

// to represent a pilot 
class Pilot {

    WorldImage captain = new FromFileImage("CAPTAIN.png"); // player 1
    WorldImage mr = new FromFileImage("MR.WASD.png"); // player 2
    Cell c; 
    int steps; // accumulates the number of steps a pilot has taken

    // constructor 
    Pilot() {
        // empty constructor
    }

    // convenience constructor for testing purposes 
    Pilot(Cell c) {
        this.c = c; 
    }

    // EFECT: to move the first pilot (captain) based on user input 
    void moveCaptain(String input) {
        // c.left is the neighbor of c which is always going to be a cell.
        // isFlooded is always going to be a boolean therefore this field is
        // guaranteed 
        // this applies for all other neighbors of this world's cell
        if (input.equals("left") && !(c.left.isFlooded)) {
            // this.c is a cell and this.c.left is always going to be a cell
            // because it is a neighbor. 
            this.c = this.c.left;  
            this.steps = this.steps + 1; 
        } 
        else if (input.equals("right") && !(c.right.isFlooded)) {
            this.c = this.c.right;
            this.steps = this.steps + 1; 
        }
        else if (input.equals("up") && !(c.top.isFlooded)) {
            this.c = c.top;
            this.steps = this.steps + 1; 
        }
        else if (input.equals("down") && !(c.bottom.isFlooded)) {
            this.c = c.bottom; 
            this.steps = this.steps + 1; 
        }
    }

    // EFFECT: to move the second pilot (mr) based on user input 
    void moveMr(String input) {
        // c.left is the neighbor of c which is always going to be a cell.
        // isFlooded is always going to be a boolean therefore this field is
        // guaranteed 
        // this applies for all other neighbors of this world's cell
        if (input.equals("a") && !(c.left.isFlooded)) {
            this.c = this.c.left;
            this.steps = this.steps + 1; 
        } 
        else if (input.equals("d") && !(c.right.isFlooded)) {
            this.c = this.c.right;
            this.steps = this.steps + 1; 
        }
        else if (input.equals("w") && !(c.top.isFlooded)) {
            this.c = c.top;
            this.steps = this.steps + 1; 
        }
        else if (input.equals("s") && !(c.bottom.isFlooded)) {
            this.c = c.bottom; 
            this.steps = this.steps + 1; 
        }
    }

    // is this pilot on the given cell?
    boolean onCell(Cell c) {
        return this.c.sameCell(c);
    }
}

// to represent the abstract class Target
abstract class Target {

    WorldImage hp;  
    Cell c;

    // constructor
    Target(Cell c) {
        this.c = c; 
    }

    // EFFECT: to place the target image on the given Scene 
    void makeTarget(WorldScene acc) {
        // this.c.x and this.c.y are always guaranteed to be an int 
        // because the cell has an x and y field and this is gauranteed
        // to be a cell
        acc.placeImageXY(this.hp, this.c.x * ForbiddenIslandWorld.SCALE, 
                this.c.y * ForbiddenIslandWorld.SCALE);
    }
}

// to represent all of the pieces that will be collected 
class HelicopterPieces extends Target {

    // constructor 
    HelicopterPieces(Cell c) {
        super(c);
        this.hp = new FromFileImage("gear.png");
    }
}

// to represent the Helicopter
class HelicopterTarget extends Target {

    // constructor
    HelicopterTarget(Cell c) {
        super(c);
        this.hp = new FromFileImage("helicopter.png"); 
    }
}

//to render the game 
class ForbiddenIslandWorld extends World {

    IList<Cell> board; // all the cells of the game, including the ocean
    int waterHeight; // the current height of the ocean
    static final int ISLAND_SIZE = 64; // defines an int constant
    static final int SCALE = 10; // to scale the game
    Random rand = new Random(); 
    int counter; // for onTick
    Pilot pilot1 = new Pilot(); // CAPTAIN, player 1
    Pilot pilot2 = new Pilot(); // MR.WASD, player 2
    Target heli;  // helicopter
    ArrayList<Target> pieces;
    int numPieces; // to represent the number of pieces in the game

    // constructor: for testing purposes 
    ForbiddenIslandWorld(int size, int waterHeight, ArrayList<Target> pieces, 
            Target heli) {
        this.board = this.flatten(size, 
                createCells(size, createMountainHeights(size)));
        this.waterHeight = waterHeight; 
        this.pieces = pieces; 
        this.heli = heli;
    }  

    // convenience constructor: used for running the game 
    ForbiddenIslandWorld(int waterHeight, int numPieces) {
        this.board = this.flatten(ForbiddenIslandWorld.ISLAND_SIZE, 
                createCells(ForbiddenIslandWorld.ISLAND_SIZE, 
                        createMountainHeights(ForbiddenIslandWorld.ISLAND_SIZE)));
        // FOR INITIAL RUN: 
        // change to createMountainHeights to make a Mountain Island
        // change to createRandHeights to make a Random Island
        // change to createRandTerrainHeights to make a Random Terrain Island 
        this.waterHeight = 0; 
        this.pieces = new ArrayList<Target>();
        this.heli = new HelicopterTarget(this.getHighestCell()); 
        this.numPieces = numPieces;
        this.pieces = this.makePiecesList(numPieces);
    }

    // convenience constructor for restarting the game 
    ForbiddenIslandWorld(IList<Cell> board) {
        this.board = board; 
    }

    // to create all the height options for Mountain Island cells 
    ArrayList<ArrayList<Double>> createMountainHeights(int size) {
        ArrayList<ArrayList<Double>> columnheights = new ArrayList<ArrayList<Double>>();  
        // x values 
        for (int i = 0; i <= size;  i = i + 1) {
            ArrayList<Double> rowheights = new ArrayList<Double>();
            // y values
            double maxHeight = size / 2; 
            for (int j = 0; j <=  size; j = j + 1) {
                double idis = Math.abs(maxHeight - i);
                double jdis = Math.abs(maxHeight - j); 
                double manhattanDistance = (idis + jdis);
                double height = maxHeight - manhattanDistance;
                rowheights.add(height); 
            } 
            columnheights.add(rowheights); 
        }
        return columnheights; 
    }

    // to create all the height options for Random Island cells 
    ArrayList<ArrayList<Double>> createRandHeights(int size) {
        ArrayList<ArrayList<Double>> columnheights = new ArrayList<ArrayList<Double>>(); 
        Random rand = new Random();
        // x values
        for (int i = 0; i <= size;  i = i + 1) {
            ArrayList<Double> rowheights = new ArrayList<Double>();
            double maxHeight = size / 2; 
            // y values
            for (int j = 0; j <=  size; j = j + 1) {
                double next = rand.nextDouble();
                double idis = Math.abs(maxHeight - i);
                double jdis = Math.abs(maxHeight - j); 
                double manhattanDistance = (idis + jdis);
                double height = maxHeight - manhattanDistance;
                if (manhattanDistance < maxHeight) {
                    height = next * maxHeight;
                }                
                rowheights.add(height); 
            } 
            columnheights.add(rowheights); 
        }
        return columnheights; 
    }  

    /** EFFECT: to produce the values that will be used to create  
    the cell heights in the RandTerrainHeights method
     * @param aad: given ArrayList<ArrayList<Doubles>>
     * @param xi: initial/lowest x value
     * @param xf: final/highest x value
     * @param yi: initial/lowest y value
     * @param yf: final/highest y value
     */
    void randTerrainHelper(ArrayList<ArrayList<Double>> aad, 
            int xi, int xf, int yi, int yf) {
        if ((xf - xi > 1) && (yf - yi > 1)) {

            // to get values of the corners
            // first get the columns then get the row
            double tl = aad.get(xi).get(yi);
            double bl = aad.get(xf).get(yi);
            double tr = aad.get(xi).get(yf);
            double br = aad.get(xf).get(yf);
            double nudge = (xf - xi);

            // apply the formulas to set middle values
            double top = (.45 - rand.nextDouble()) * nudge + (tl + tr) / 2; 
            double left = (.45 - rand.nextDouble()) * nudge + (tl + bl) / 2; 
            double middle = (.45 - rand.nextDouble()) * nudge 
                    + (tl + tr + bl + br) / 4;

            // set the corresponding midpoint values
            int midpointx = (xf + xi) / 2;
            int midpointy = (yf + yi) / 2;
            aad.get(xi).set(midpointy, top);
            aad.get(midpointx).set(yi, left);
            aad.get(midpointx).set(midpointy, middle);

            // recursive call on each subquadrant
            // bottom right
            randTerrainHelper(aad, midpointx, xf, midpointy, yf);
            // top left 
            randTerrainHelper(aad, midpointx, xf, yi, midpointy);
            // bottom left
            randTerrainHelper(aad, xi, midpointx, midpointy, yf);
            // top left
            randTerrainHelper(aad, xi, midpointx, yi, midpointy);
        }
    }

    // to create all the height options for Random Terrain cells  
    ArrayList<ArrayList<Double>> createRandTerrainHeights(int size) {
        ArrayList<ArrayList<Double>> columnheights = new ArrayList<ArrayList<Double>>(); 
        int midpoint = size / 2; 
        double middle = size / 2;
        // x values
        for (int i = 0; i < size + 1;  i = i + 1) {
            ArrayList<Double> rowheights = new ArrayList<Double>();
            // y values
            for (int j = 0; j < size + 1; j = j + 1) {
                // initialize the 2D array list to contain all zeros
                rowheights.add(0.0);            
            }
            columnheights.add(rowheights); 
        }    
        // to set the initial values: the corners to zero and the midpoints to one
        columnheights.get(0).set(midpoint, 1.0);
        columnheights.get(midpoint).set(0, 1.0);
        columnheights.get(0).set(size, 1.0);
        columnheights.get(size).set(midpoint, 1.0);  
        columnheights.get(midpoint).set(midpoint, middle);
        columnheights.get(0).set(size, 0.0);
        // recursive calls on each sub quadrant
        // bottom right
        randTerrainHelper(columnheights, midpoint, size, midpoint, size);
        // bottom left
        randTerrainHelper(columnheights, 0, midpoint, midpoint, size);
        // top right
        randTerrainHelper(columnheights, midpoint, size, 0, midpoint);
        // top left
        randTerrainHelper(columnheights, 0, midpoint, 0, midpoint);
        return columnheights;
    }

    // returns true if the cell isFlooded
    boolean underWater(Cell c) {
        return (c.height <= this.waterHeight); 
    }

    // to create all of the cells when given an 
    // ArrayList<ArrayList<Doubles>> (heights)
    ArrayList<ArrayList<Cell>> createCells(int size, 
            ArrayList<ArrayList<Double>> aad) {
        ArrayList<ArrayList<Cell>> columncells = new ArrayList<ArrayList<Cell>>();
        for (int i = 0; i <= size; i = i + 1) {
            ArrayList<Cell> rowcells = new ArrayList<Cell>(); 
            for (int j = 0; j <=  size; j = j + 1) {
                ArrayList<Double> ad = aad.get(i); 
                Cell c = new Cell(ad.get(j), j, i);
                if (this.underWater(c)) {
                    c = new OceanCell(ad.get(j), j, i);
                    c.isFlooded = true; 
                }
                rowcells.add(c); 
            }
            columncells.add(rowcells); 
        }
        return columncells; 
    }

    // to fix each cell's links with its neighbors 
    ArrayList<ArrayList<Cell>> fixCells(int size, ArrayList<ArrayList<Cell>> aac) {
        for (int i = 0; i <= size; i = i + 1) {
            for (int j = 0; j <=  size; j = j + 1) { 
                Cell c = aac.get(i).get(j); 
                if (i == 0) {
                    c.top = c;
                }
                else {
                    c.top = aac.get(i - 1).get(j);
                }
                if (j == 0) {
                    c.left = c;
                }
                else {
                    c.left = aac.get(i).get(j - 1);
                }
                if (j == size) {
                    c.right = c;
                }
                else {
                    c.right = aac.get(i).get(j + 1);
                }
                if (i == size) {
                    c.bottom = c;
                }
                else {
                    c.bottom = aac.get(i + 1).get(j);
                }
            }
        }
        return aac;
    }

    // to flatten a given ArrayList<ArrayList<Cell>> into an IList<Cell>
    IList<Cell> flatten(int size, ArrayList<ArrayList<Cell>> aac) {
        IList<Cell> list = new MtList<Cell>();
        aac = this.fixCells(size, aac); 
        for (int i = 0; i <= size; i = i + 1) {
            for (int j = 0; j <= size; j = j + 1) {               
                Cell c = aac.get(i).get(j);
                list = new ConsList<Cell>(c, list); 
            }
        }
        // EFFECT: to randomly place the pilots on Cells (not including OceanCells)
        Cell c1 = aac.get(rand.nextInt(size)).get(rand.nextInt(size)); 
        Cell c2 = aac.get(rand.nextInt(size)).get(rand.nextInt(size));
        while (c1.isFlooded) {
            c1 = aac.get(rand.nextInt(size)).get(rand.nextInt(size)); 
        }
        if (!(c1.isFlooded)) {
            pilot1.c = c1; 
        }
        while (c2.isFlooded) {
            c2 = aac.get(rand.nextInt(size)).get(rand.nextInt(size)); 
        }
        if (!(c2.isFlooded)) {
            pilot2.c = c2; 
        }
        return list; 
    }

    // EFFECT: to place the helicopter on the Cell with the greatest heights
    void placeHeli() {
        double max = 0;
        for (Cell c : this.board) { 
            if (c.height > max) {
                max = c.height;
                this.heli.c = c; 
            }
        }
    }

    // to build the scene of cells and place the helicopter onto the scene 
    WorldScene makeSceneHelper() {
        WorldScene result = getEmptyScene(); 
        for (Cell c : this.board) {
            WorldImage img = c.makeCell(this.waterHeight); 
            result.placeImageXY(img, c.x * SCALE, c.y * SCALE); 
        }
        this.placeHeli(); 
        return result; 
    }

    // to return the highest cell
    Cell getHighestCell() {
        double max = 0;
        Cell cell = null;
        for (Cell c : this.board) { 
            if (c.height > max) {
                max = c.height;
                cell = c; 
            }
        }
        return cell;
    }

    // to return a random cell that is not flooded (not an Ocean Cell)
    Cell getRandomLandPos() {
        int x = rand.nextInt(ISLAND_SIZE + 1);
        int y = rand.nextInt(ISLAND_SIZE + 1);
        Cell c = this.getCellAt(x, y);
        while (c.isFlooded) {
            x = rand.nextInt(ISLAND_SIZE + 1);
            y = rand.nextInt(ISLAND_SIZE + 1); 
            c = this.getCellAt(x, y);
        }
        return c;        
    }

    // to return the cell at the given x and y coordinates
    Cell getCellAt(int x, int y) {
        return this.board.get(x + (y * ISLAND_SIZE));
    }

    // to build the ArrayList of HelicopterPieces that will need to be collected
    ArrayList<Target> makePiecesList(int length) { 
        // increasing the length will add more pieces to the game
        for (int i = 0; i < length; i = i + 1) { 
            this.pieces.add(new HelicopterPieces(this.getRandomLandPos()));  
        }
        return this.pieces;
    }

    // to build the scene of pieces and add them to the result of makeSceneHelper
    WorldScene makePiecesHelper(int length) {
        WorldScene result = this.makeSceneHelper(); 
        for (Target w : this.pieces) { 
            w.makeTarget(result);
        }
        return result; 
    }

    // EFFECT: to rise the water level of the game (one foot) every ten ticks
    void riseWaterHeight(int size) {
        if (counter % 10 == 0 && this.waterHeight < size) {
            this.waterHeight += 1;
        }
    }

    // EFFECT: to flood all of the cells on the coast
    void floodCoast() {
        for (Cell c : this.board) {
            if ((c.left.isFlooded || c.top.isFlooded || c.right.isFlooded || 
                    c.bottom.isFlooded) && !(c.isFlooded) && 
                    c.height < this.waterHeight) {
                c.isFlooded = true; 
            }
        }
    }

    // to make the scene 
    public WorldScene makeScene() {
        // this.pilot1.captain is guaranteed to be an image because captain is
        // a WorldImage field of pilot.
        // creates board and pieces
        WorldScene scene = this.makePiecesHelper(4);
        // places player 1
        scene.placeImageXY(this.pilot1.captain, pilot1.c.x * SCALE, 
                pilot1.c.y * SCALE);
        // places player 2
        scene.placeImageXY(this.pilot2.mr, pilot2.c.x * SCALE, 
                pilot2.c.y * SCALE);
        // places the helicopter 
        scene.placeImageXY(this.heli.hp, this.heli.c.x * SCALE, 
                this.heli.c.y * SCALE);
        // places the pieces
        this.collection(); 
        // keeps track of steps each player has taken
        scene.placeImageXY(new TextImage("CAPTAIN SCORE:" + this.pilot1.steps + 
                " " + "MR.WASD SCORE:" + this.pilot2.steps, 20, Color.YELLOW), 
                32 * SCALE, 1 * SCALE);
        return scene;
    }

    // EFFECT: to flood cells every ten ticks
    public void onTick() { 
        counter += 1; 
        this.riseWaterHeight(ForbiddenIslandWorld.ISLAND_SIZE);     
        this.floodCoast(); 
    }

    // EFFECT: to reset the game depending on the user input
    void resetGame(String input) {
        if (input.equals("m")) { // to reset to a Mountain Island
            this.counter = 0;
            this.waterHeight = 0;
            this.pieces = new ArrayList<Target>();
            this.board = flatten(ForbiddenIslandWorld.ISLAND_SIZE, 
                    createCells(ForbiddenIslandWorld.ISLAND_SIZE,
                            createMountainHeights(ForbiddenIslandWorld.ISLAND_SIZE)));
            this.pieces = this.makePiecesList(this.numPieces);
            this.pilot1.steps = 0; 
            this.pilot2.steps = 0; 
        }
        else if (input.equals("r")) { // to reset to a Random Island
            this.counter = 0;
            this.waterHeight = 0;     
            this.pieces = new ArrayList<Target>();
            this.board = flatten(ForbiddenIslandWorld.ISLAND_SIZE, 
                    createCells(ForbiddenIslandWorld.ISLAND_SIZE, 
                            createRandHeights(ForbiddenIslandWorld.ISLAND_SIZE)));
            this.pieces = this.makePiecesList(this.numPieces);
            this.pilot1.steps = 0; 
            this.pilot2.steps = 0; 
        }
        else if (input.equals("t")) { // to reset to a Random Terrain Island
            this.counter = 0;  
            this.waterHeight = 0;
            this.pieces = new ArrayList<Target>();
            this.board = flatten(ForbiddenIslandWorld.ISLAND_SIZE, 
                    createCells(ForbiddenIslandWorld.ISLAND_SIZE, 
                            createRandTerrainHeights(ForbiddenIslandWorld.ISLAND_SIZE)));
            this.pieces = this.makePiecesList(this.numPieces);
            this.pilot1.steps = 0; 
            this.pilot2.steps = 0; 
        }
    }

    // EFFECT: to move the pilots and restart the game 
    public void onKeyEvent(String input) {
        resetGame(input); 
        pilot1.moveCaptain(input); 
        pilot2.moveMr(input);
    }

    // EFFECT: to remove pieces from the board once they 
    //         have been collected by either pilot 
    void collection() {
        Cell c1 = pilot1.c; 
        Cell c2 = pilot2.c; 
        ArrayList<Target> pieces = this.pieces;
        Iterator<Target> iterator = pieces.iterator();
        while (iterator.hasNext()) {
            Target next = iterator.next();
            if (c1.sameCell(next.c) || c2.sameCell(next.c)) {
                iterator.remove(); 
            }
        }
    }

    // to render the last scene
    public WorldScene lastScene(String s) {
        WorldScene scene = makeSceneHelper(); 
        scene.placeImageXY(new TextImage(s, 40, Color.YELLOW), 32 * SCALE, 
                32 * SCALE); 
        return scene; 
    }

    // to end the game and return the final text
    public WorldEnd worldEnds() {
        if (pilot1.c.isFlooded || pilot2.c.isFlooded) {
            WorldScene scene1 = this.lastScene("YOU HAVE DROWNED-_-"); 
            scene1.placeImageXY(new TextImage("CAPTAIN SCORE:" + 
                    this.pilot1.steps + " " + "MR.WASD SCORE:" + 
                    this.pilot2.steps, 20, Color.YELLOW), 32 * SCALE, 26 * SCALE);
            return new WorldEnd(true, scene1); 
        }
        else if ((pieces.size() == 0) && pilot1.onCell(heli.c) && 
                pilot2.onCell(heli.c)) {
            WorldScene scene2 = this.lastScene("SUCH SUCCESS!!");
            scene2.placeImageXY(new TextImage("CAPTAIN SCORE:" + 
                    this.pilot1.steps + " " + "MR.WASD SCORE:" + 
                    this.pilot2.steps, 20, Color.YELLOW), 32 * SCALE, 26 * SCALE);
            return new WorldEnd(true, scene2);
        }
        else {
            return new WorldEnd(false, this.makeScene());
        }
    }
}
