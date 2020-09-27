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
package com.rarysoft.u4.model;

import java.util.Optional;

public class RenderedTile {
    private final Tile tile;

    private final Person person;

    private final boolean render;

    public RenderedTile(Tile tile, Person person) {
        this.tile = tile;
        this.person = person;
        this.render = true;
    }

    private RenderedTile(Tile tile, Person person, boolean render) {
        this.tile = tile;
        this.person = person;
        this.render = render;
    }

    public Tile tile() {
        return tile;
    }

    public Optional<Person> person() {
        return Optional.ofNullable(person);
    }

    public boolean render() {
        return render;
    }

    public RenderedTile hidden() {
        return new RenderedTile(tile, person, false);
    }
}
