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
package com.rarysoft.u4.game;

import java.util.*;

public class RenderedTile {
    private final List<Tile> baseTiles;

    private final Tile transientTile;

    private final boolean render;

    public RenderedTile() {
        this.baseTiles = new ArrayList<>();
        this.transientTile = null;
        this.render = true;
    }

    private RenderedTile(List<Tile> baseTiles, Tile transientTile, boolean render) {
        this.baseTiles = baseTiles;
        this.transientTile = transientTile;
        this.render = render;
    }

    public List<Tile> baseTiles() {
        return this.baseTiles;
    }

    public Optional<Tile> transientTile() {
        return Optional.ofNullable(transientTile);
    }

    public boolean render() {
        return render;
    }

    public int walkability() {
        int walkability = 0;
        for (Tile tile : baseTiles) {
            if (tile.walkability() > walkability) {
                walkability = tile.walkability();
            }
        }
        return walkability;
    }

    public boolean isOpaque() {
        for (Tile tile : baseTiles) {
            if (tile.isOpaque()) {
                return true;
            }
        }
        return transientTile != null && transientTile.isOpaque();
    }

    public boolean canTalkThrough() {
        for (Tile tile : baseTiles) {
            if (tile.canTalkThrough()) {
                return true;
            }
        }
        return transientTile != null && transientTile.canTalkThrough();
    }

    public boolean isPortal() {
        for (Tile tile : baseTiles) {
            if (tile.isPortal()) {
                return true;
            }
        }
        return transientTile != null && transientTile.isPortal();
    }

    public RenderedTile withBaseTile(Tile baseTile) {
        List<Tile> newBaseTiles = new ArrayList<>(this.baseTiles);
        if (baseTile != null) {
            newBaseTiles.add(baseTile);
        }
        return new RenderedTile(newBaseTiles, transientTile, render);
    }

    public RenderedTile withTransientTile(Tile transientTile) {
        return new RenderedTile(baseTiles, transientTile, render);
    }

    public RenderedTile hidden() {
        return new RenderedTile(baseTiles, transientTile, false);
    }
}
