/*
 * MIT License
 *
 * Copyright (c) 2020 Rarysoft Enterprises
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.rarysoft.u4.game.physics;

import com.rarysoft.u4.game.Area;
import com.rarysoft.u4.game.Movement;

public class WayFinder {
    private static final Movement NOWHERE = new Movement(0, 0);
    private static final Movement NORTH = new Movement(-1, 0);
    private static final Movement NORTHEAST = new Movement(-1, 1);
    private static final Movement EAST = new Movement(0, 1);
    private static final Movement SOUTHEAST = new Movement(1, 1);
    private static final Movement SOUTH = new Movement(1, 0);
    private static final Movement SOUTHWEST = new Movement(1, -1);
    private static final Movement WEST = new Movement(0, -1);
    private static final Movement NORTHWEST = new Movement(-1, -1);

    public Movement selectMovementTowardTarget(Area<Integer> area, int row, int col) {
        int centerRow = (area.rows() - 1) / 2;
        int centerCol = (area.cols() - 1) / 2;
        int deltaRow = row - centerRow;
        int deltaCol = col - centerCol;
        int absDeltaRow = Math.abs(deltaRow);
        int absDeltaCol = Math.abs(deltaCol);
        if (absDeltaRow <= 1 && absDeltaCol <= 1) {
            return NOWHERE;
        }
        Movement movement;
        if ((absDeltaRow == 2 && absDeltaCol == 0)
                || ((absDeltaRow == 3 || absDeltaRow == 4) && absDeltaCol <= 1)
                || ((absDeltaRow == 5 || absDeltaRow == 6) && absDeltaCol <= 2)
                || ((absDeltaRow == 7 || absDeltaRow == 8) && absDeltaCol <= 3)
                || ((absDeltaRow == 9 || absDeltaRow == 10) && absDeltaCol <= 4)) {
            movement = new Movement(deltaRow < 0 ? -1 : 1, 0);
        }
        else if ((absDeltaCol == 2 && absDeltaRow == 0)
                || ((absDeltaCol == 3 || absDeltaCol == 4) && absDeltaRow <= 1)
                || ((absDeltaCol == 5 || absDeltaCol == 6) && absDeltaRow <= 2)
                || ((absDeltaCol == 7 || absDeltaCol == 8) && absDeltaRow <= 3)
                || ((absDeltaCol == 9 || absDeltaCol == 10) && absDeltaRow <= 4)) {
            movement = new Movement(0, deltaCol < 0 ? -1 : 1);
        }
        else if (deltaRow < 0) {
            if (deltaCol < 0) {
                movement = NORTHWEST;
            }
            else {
                movement = NORTHEAST;
            }
        }
        else if (deltaCol < 0) {
            movement = SOUTHWEST;
        }
        else {
            movement = SOUTHEAST;
        }
        if (canMove(area, centerRow, centerCol, movement.getDeltaRow(), movement.getDeltaCol())) {
            return movement;
        }
        if (deltaRow == 0 || deltaCol == 0) {
            return NOWHERE;
        }
        movement = new Movement(deltaRow > 0 ? 1 : -1, 0);
        if (canMove(area, centerRow, centerCol, movement.getDeltaRow(), movement.getDeltaCol())) {
            return movement;
        }
        movement = new Movement(0, deltaCol > 0 ? 1 : -1);
        if (canMove(area, centerRow, centerCol, movement.getDeltaRow(), movement.getDeltaCol())) {
            return movement;
        }
        return NOWHERE;
    }

    private boolean canMove(Area<Integer> area, int centerRow, int centerCol, int deltaRow, int deltaCol) {
        return area.get(centerRow + deltaRow, centerCol + deltaCol) > 0;
    }
}
