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
package com.rarysoft.u4.game.state;

import com.rarysoft.u4.game.event.Event;
import com.rarysoft.u4.game.event.EventManager;

import java.util.Stack;
import java.util.function.Function;

public class StateManager {
    private final EventManager eventManager;
    private final Stack<GameState> gameStates;

    public StateManager(EventManager eventManager, GameState gameState) {
        this.eventManager = eventManager;
        this.gameStates = new Stack<>();
        this.gameStates.push(gameState);
    }

    public GameState gameState() {
        return currentState();
    }

    public void changeState(Function<GameState, GameState> stateSetter, Event event) {
        gameStates.push(stateSetter.apply(currentState()));
        eventManager.publish(event, currentState());
    }

    private GameState currentState() {
        return gameStates.peek();
    }
}
