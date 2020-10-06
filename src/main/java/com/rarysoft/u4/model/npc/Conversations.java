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
package com.rarysoft.u4.model.npc;

import com.rarysoft.u4.model.party.Location;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map;

public class Conversations {
    private final Map<Location, List<Conversation>> locationConversations;

    public static Conversations fromFiles(String directory) throws IOException {
        Map<Location, List<Conversation>> locationConversations = new HashMap<>();
        locationConversations.put(Location.BRITAIN, loadConversations(path(directory, "britain.tlk")));
        locationConversations.put(Location.COVE, loadConversations(path(directory, "cove.tlk")));
        locationConversations.put(Location.BUCCANEERS_DEN, loadConversations(path(directory, "den.tlk")));
        locationConversations.put(Location.EMPATH_ABBEY, loadConversations(path(directory, "empath.tlk")));
        locationConversations.put(Location.JHELOM, loadConversations(path(directory, "jhelom.tlk")));
        locationConversations.put(Location.CASTLE_BRITANNIA, loadConversations(path(directory, "lcb.tlk")));
        locationConversations.put(Location.THE_LYCAEUM, loadConversations(path(directory, "lycaeum.tlk")));
        locationConversations.put(Location.SERPENTS_HOLD, loadConversations(path(directory, "serpent.tlk")));
        locationConversations.put(Location.MAGINCIA, loadConversations(path(directory, "magincia.tlk")));
        locationConversations.put(Location.MINOC, loadConversations(path(directory, "minoc.tlk")));
        locationConversations.put(Location.MOONGLOW, loadConversations(path(directory, "moonglow.tlk")));
        locationConversations.put(Location.PAWS, loadConversations(path(directory, "paws.tlk")));
        locationConversations.put(Location.SKARA_BRAE, loadConversations(path(directory, "skara.tlk")));
        locationConversations.put(Location.TRINSIC, loadConversations(path(directory, "trinsic.tlk")));
        locationConversations.put(Location.VESPER, loadConversations(path(directory, "vesper.tlk")));
        locationConversations.put(Location.YEW, loadConversations(path(directory, "yew.tlk")));
        return new Conversations(locationConversations);
    }

    private static String path(String directory, String filename) {
        return String.format("%s%s%s", directory, File.separator, filename);
    }
    
    private static List<Conversation> loadConversations(String conversationsFilename) throws IOException {
        List<Conversation> conversations = new ArrayList<>();
        InputStream stream = Conversations.class.getResourceAsStream(conversationsFilename);
        boolean done = false;
        while (! done) {
            Conversation conversation = buildConversationFromStream(stream);
            if (conversation == null) {
                done = true;
            }
            else {
                conversations.add(conversation);
            }
        }
        return conversations;
    }

    private static Conversation buildConversationFromStream(InputStream stream) throws IOException {
        int byteCount = 0;
        int questionFlag = stream.read();
        if (questionFlag == -1) {
            return null;
        }
        byteCount ++;
        boolean responseAffectsHumility = stream.read() == 1;
        byteCount ++;
        int turnAwayProbability = stream.read();
        byteCount ++;
        String name = readNullTerminatedString(stream);
        byteCount += name.length() + 1;
        String pronoun = readNullTerminatedString(stream);
        byteCount += pronoun.length() + 1;
        String lookResponse = readNullTerminatedString(stream);
        byteCount += lookResponse.length() + 1;
        String jobResponse = readNullTerminatedString(stream);
        byteCount += jobResponse.length() + 1;
        String healthResponse = readNullTerminatedString(stream);
        byteCount += healthResponse.length() + 1;
        String keyword1Response = readNullTerminatedString(stream);
        byteCount += keyword1Response.length() + 1;
        String keyword2Response = readNullTerminatedString(stream);
        byteCount += keyword2Response.length() + 1;
        String yesNoQuestion = readNullTerminatedString(stream);
        byteCount += yesNoQuestion.length() + 1;
        String yesResponse = readNullTerminatedString(stream);
        byteCount += yesResponse.length() + 1;
        String noResponse = readNullTerminatedString(stream);
        byteCount += noResponse.length() + 1;
        String keyword1 = readNullTerminatedString(stream);
        byteCount += keyword1.length() + 1;
        String keyword2 = readNullTerminatedString(stream);
        byteCount += keyword2.length() + 1;
        stream.skip(288 - byteCount);
        return new Conversation(questionFlag, responseAffectsHumility, turnAwayProbability, name, pronoun, lookResponse, jobResponse, healthResponse, keyword1Response, keyword2Response, yesNoQuestion, yesResponse, noResponse, keyword1, keyword2);
    }

    private static String readNullTerminatedString(InputStream stream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        boolean done = false;
        while (! done) {
            int byteRead = stream.read();
            if (byteRead > 0) {
                if (byteRead == '\n') {
                    stringBuilder.append(" ");
                }
                else {
                    stringBuilder.append((char) byteRead);
                }
            }
            else {
                done = true;
            }
        }
        return stringBuilder.toString();
    }

    public Conversations(Map<Location, List<Conversation>> locationConversations) {
        this.locationConversations = locationConversations;
    }

    public Optional<Conversation> findConversationFor(Location location, Person person) {
        if (! locationConversations.containsKey(location)) {
            return Optional.empty();
        }
        List<Conversation> conversations = locationConversations.get(location);
        return Optional.of(conversations.get(person.conversationIndex() - 1));
    }
}
