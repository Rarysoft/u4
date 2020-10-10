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

import com.rarysoft.u4.i18n.Messages;
import com.rarysoft.u4.model.party.Location;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map;

public class Conversations {
    private final Map<Location, List<Conversation>> locationConversations;

    public static Conversations fromFiles(String directory, Messages messages) throws IOException {
        Map<Location, List<Conversation>> locationConversations = new HashMap<>();
        locationConversations.put(Location.BRITAIN, loadConversations(path(directory, "britain.tlk"), messages));
        locationConversations.put(Location.COVE, loadConversations(path(directory, "cove.tlk"), messages));
        locationConversations.put(Location.BUCCANEERS_DEN, loadConversations(path(directory, "den.tlk"), messages));
        locationConversations.put(Location.EMPATH_ABBEY, loadConversations(path(directory, "empath.tlk"), messages));
        locationConversations.put(Location.JHELOM, loadConversations(path(directory, "jhelom.tlk"), messages));
        locationConversations.put(Location.CASTLE_BRITANNIA, loadConversations(path(directory, "lcb.tlk"), messages, ConversationBuilder.buildLordBritishConversation()));
        locationConversations.put(Location.THE_LYCAEUM, loadConversations(path(directory, "lycaeum.tlk"), messages));
        locationConversations.put(Location.SERPENTS_HOLD, loadConversations(path(directory, "serpent.tlk"), messages));
        locationConversations.put(Location.MAGINCIA, loadConversations(path(directory, "magincia.tlk"), messages));
        locationConversations.put(Location.MINOC, loadConversations(path(directory, "minoc.tlk"), messages));
        locationConversations.put(Location.MOONGLOW, loadConversations(path(directory, "moonglow.tlk"), messages));
        locationConversations.put(Location.PAWS, loadConversations(path(directory, "paws.tlk"), messages));
        locationConversations.put(Location.SKARA_BRAE, loadConversations(path(directory, "skara.tlk"), messages));
        locationConversations.put(Location.TRINSIC, loadConversations(path(directory, "trinsic.tlk"), messages));
        locationConversations.put(Location.VESPER, loadConversations(path(directory, "vesper.tlk"), messages));
        locationConversations.put(Location.YEW, loadConversations(path(directory, "yew.tlk"), messages));
        return new Conversations(locationConversations);
    }

    private static String path(String directory, String filename) {
        return String.format("%s%s%s", directory, File.separator, filename);
    }

    private static List<Conversation> loadConversations(String conversationsFilename, Messages messages) throws IOException {
        return loadConversations(conversationsFilename, messages, null);
    }

    private static List<Conversation> loadConversations(String conversationsFilename, Messages messages, Conversation additionalConversation) throws IOException {
        List<Conversation> conversations = new ArrayList<>();
        if (additionalConversation != null) {
            conversations.add(additionalConversation);
        }
        InputStream stream = Conversations.class.getResourceAsStream(conversationsFilename);
        boolean done = false;
        while (! done) {
            Conversation conversation = buildConversationFromStream(stream, messages);
            if (conversation == null) {
                done = true;
            }
            else {
                conversations.add(conversation);
            }
        }
        return conversations;
    }

    private static Conversation buildConversationFromStream(InputStream stream, Messages messages) throws IOException {
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
        return new Conversation(
                questionFlag,
                responseAffectsHumility,
                turnAwayProbability,
                messages.speechCitizenIntro(withinSentence(lookResponse)),
                conversationResponse(messages.speechCitizenName(name), pronoun, messages),
                messages.speechCitizenDescribe(withinSentence(lookResponse)),
                conversationResponse(jobResponse, pronoun, messages),
                conversationResponse(healthResponse, pronoun, messages),
                conversationResponse(messages.speechCitizenNoJoin(), pronoun, messages),
                conversationResponse(keyword1Response, pronoun, messages),
                conversationResponse(keyword2Response, pronoun, messages),
                yesNoQuestion,
                conversationResponse(yesResponse, pronoun, messages),
                conversationResponse(noResponse, pronoun, messages),
                conversationResponse(messages.speechCitizenUnknown(), pronoun, messages),
                keyword1,
                keyword2
        );
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

    private static String conversationResponse(String message, String pronoun, Messages messages) {
        return messages.speechCitizenSpeaking(pronoun) + " " + message;
    }

    private static String withinSentence(String message) {
        if (message.length() <= 1) {
            return message.toLowerCase();
        }
        return message.substring(0, 1).toLowerCase() + message.substring(1);
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
