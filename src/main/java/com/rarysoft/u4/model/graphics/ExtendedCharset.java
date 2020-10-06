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
package com.rarysoft.u4.model.graphics;

import static com.rarysoft.u4.model.graphics.Colours.*;

public class ExtendedCharset {
    private static final int[] GRAY_HORIZONTAL_LINE = {
            COLOUR_LIGHT_GRAY,
            COLOUR_LIGHT_GRAY,
            COLOUR_LIGHT_GRAY,
            COLOUR_LIGHT_GRAY,
            COLOUR_LIGHT_GRAY,
            COLOUR_LIGHT_GRAY,
            COLOUR_LIGHT_GRAY,
            COLOUR_LIGHT_GRAY
    };
    private static final int[] BLUE_HORIZONTAL_LINE = {
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE
    };
    private static final int[] BLUE_BETWEEN_GRAY_EDGE = {
            COLOUR_LIGHT_GRAY,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_BRIGHT_BLUE,
            COLOUR_LIGHT_GRAY
    };

    private static final int[][] BORDER_HORIZONTAL = {
            GRAY_HORIZONTAL_LINE,
            BLUE_HORIZONTAL_LINE,
            BLUE_HORIZONTAL_LINE,
            BLUE_HORIZONTAL_LINE,
            BLUE_HORIZONTAL_LINE,
            BLUE_HORIZONTAL_LINE,
            BLUE_HORIZONTAL_LINE,
            GRAY_HORIZONTAL_LINE
    };
    private static final int[][] BORDER_VERTICAL = {
            BLUE_BETWEEN_GRAY_EDGE,
            BLUE_BETWEEN_GRAY_EDGE,
            BLUE_BETWEEN_GRAY_EDGE,
            BLUE_BETWEEN_GRAY_EDGE,
            BLUE_BETWEEN_GRAY_EDGE,
            BLUE_BETWEEN_GRAY_EDGE,
            BLUE_BETWEEN_GRAY_EDGE,
            BLUE_BETWEEN_GRAY_EDGE
    };
    private static final int[][] BORDER_CORNER_NE = {
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY
            }
    };
    private static final int[][] BORDER_CORNER_SE = {
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            },
            {
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK
            }
    };
    private static final int[][] BORDER_CORNER_SW = {
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY
            }
    };
    private static final int[][] BORDER_CORNER_NW = {
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY
            },
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_BLACK,
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            }
    };
    private static final int[][] BORDER_VERTICAL_JUNCTION_E = {
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            },
            {
                    COLOUR_LIGHT_GRAY,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE,
                    COLOUR_BRIGHT_BLUE
            }
    };

    public int[][] borderHorizontal() {
        return BORDER_HORIZONTAL;
    }

    public int[][] getBorderVertical() {
        return BORDER_VERTICAL;
    }

    public int[][] borderCornerNortheast() {
        return BORDER_CORNER_NE;
    }

    public int[][] borderCornerSoutheast() {
        return BORDER_CORNER_SE;
    }

    public int[][] borderCornerSouthwest() {
        return BORDER_CORNER_SW;
    }

    public int[][] borderCornerNorthwest() {
        return BORDER_CORNER_NW;
    }

    public int[][] getBorderVerticalJunctionEast() {
        return BORDER_VERTICAL_JUNCTION_E;
    }
}
