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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Area<T> {
    private final List<List<T>> data;

    public Area(T[][] data) {
        this.data = new ArrayList<>();
        for (T[] row : data) {
            List<T> rowData = new ArrayList<>();
            Collections.addAll(rowData, row);
            this.data.add(rowData);
        }
    }

    private Area(List<List<T>> data) {
        this.data = data;
    }

    public <O> Area<O> map(Function<T, O> mapper) {
        List<List<O>> mappedData = new ArrayList<>();
        for (List<T> row : data) {
            mappedData.add(row.stream().map(mapper).collect(Collectors.toList()));
        }
        return new Area<>(mappedData);
    }

    public int rows() {
        return data.size();
    }

    public int cols() {
        return data.get(0).size();
    }

    public T get(int row, int col) {
        return data.get(row).get(col);
    }

    public void set(int row, int col, T value) {
        data.get(row).set(col, value);
    }

    public boolean isWithin(int row, int col) {
        return ! (row < 0 || row >= data.size() || col < 0 || col >= data.get(0).size());
    }
}
