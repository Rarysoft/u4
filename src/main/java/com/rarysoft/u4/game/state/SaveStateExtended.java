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

import com.rarysoft.u4.game.Door;
import com.rarysoft.u4.game.RenderedTile;
import com.rarysoft.u4.game.npc.Dialog;
import com.rarysoft.u4.game.npc.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SaveStateExtended {
    private final RenderedTile[][] mapView;
    private final Dialog dialog;
    private final Person personConversingWith;
    private final boolean respondingToPerson;
    private final String input;
    private final Set<Door> doors;
    private final Door doorInteractingWith;
    private final List<String> messages;

    public SaveStateExtended() {
        this.mapView = null;
        this.dialog = null;
        this.personConversingWith = null;
        this.respondingToPerson = false;
        this.input = null;
        this.doors = new HashSet<>();
        this.doorInteractingWith = null;
        this.messages = new ArrayList<>();
    }

    private SaveStateExtended(RenderedTile[][] mapView, Dialog dialog, Person personConversingWith, boolean respondingToPerson, String input, Set<Door> doors, Door doorInteractingWith, List<String> messages) {
        this.mapView = mapView;
        this.dialog = dialog;
        this.personConversingWith = personConversingWith;
        this.respondingToPerson = respondingToPerson;
        this.input = input;
        this.doors = doors;
        this.doorInteractingWith = doorInteractingWith;
        this.messages = messages;
    }

    public RenderedTile[][] getMapView() {
        return mapView;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public Person getPersonConversingWith() {
        return personConversingWith;
    }

    public boolean isRespondingToPerson() {
        return respondingToPerson;
    }

    public String getInput() {
        return input;
    }

    public Set<Door> getDoors() {
        return doors;
    }

    public Door getDoorInteractingWith() {
        return doorInteractingWith;
    }

    public List<String> getMessages() {
        return messages;
    }

    public SaveStateExtended withMapView(RenderedTile[][] mapView) {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withDialog(Dialog dialog) {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withNoDialog() {
        return new SaveStateExtended(
                mapView,
                null,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withPersonConversingWith(Person personConversingWith) {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withNoPersonConversingWith() {
        return new SaveStateExtended(
                mapView,
                dialog,
                null,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withRespondingToPerson() {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                true,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withNotRespondingToPerson() {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                false,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withInput(String input) {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withAdditionalInput(char additionalInput) {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input + additionalInput,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withoutPreviousAdditionalInput() {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input == null || input.length() == 0 ? null : input.substring(0, input.length() - 1),
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withNoInput() {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                null,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withDoors(Set<Door> doors) {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withNoDoors() {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                new HashSet<>(),
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withDoorInteractingWith(Door doorInteractingWith) {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withDoorInteractingWithUnlocked() {
        doorInteractingWith.unlock();   // TODO: this should be an immutable change
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                messages
        );
    }

    public SaveStateExtended withNoDoorInteractingWith() {
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                null,
                messages
        );
    }

    public SaveStateExtended withMessage(String message) {
        List<String> newMessages = new ArrayList<>(messages);
        newMessages.add(message);
        return new SaveStateExtended(
                mapView,
                dialog,
                personConversingWith,
                respondingToPerson,
                input,
                doors,
                doorInteractingWith,
                newMessages
        );
    }
}
