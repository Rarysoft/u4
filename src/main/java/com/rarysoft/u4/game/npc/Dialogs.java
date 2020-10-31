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
package com.rarysoft.u4.game.npc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map;

public class Dialogs {
    private final Map<Integer, List<Dialog>> locationConversations;

    private final Map<NonPlayerCharacter, Dialog> npcConversations;

    public static Dialogs fromFiles(String directory, DialogTemplate template) throws IOException {
        Map<Integer, List<Dialog>> locationConversations = new HashMap<>();
        locationConversations.put(0x01, loadCharacterConversations(path(directory, "lcb.tlk"), template));
        locationConversations.put(0x02, loadCharacterConversations(path(directory, "lycaeum.tlk"), template));
        locationConversations.put(0x03, loadCharacterConversations(path(directory, "empath.tlk"), template));
        locationConversations.put(0x04, loadCharacterConversations(path(directory, "serpent.tlk"), template));
        locationConversations.put(0x05, loadCharacterConversations(path(directory, "moonglow.tlk"), template));
        locationConversations.put(0x06, loadCharacterConversations(path(directory, "britain.tlk"), template));
        locationConversations.put(0x07, loadCharacterConversations(path(directory, "jhelom.tlk"), template));
        locationConversations.put(0x08, loadCharacterConversations(path(directory, "yew.tlk"), template));
        locationConversations.put(0x09, loadCharacterConversations(path(directory, "minoc.tlk"), template));
        locationConversations.put(0x0A, loadCharacterConversations(path(directory, "trinsic.tlk"), template));
        locationConversations.put(0x0B, loadCharacterConversations(path(directory, "skara.tlk"), template));
        locationConversations.put(0x0C, loadCharacterConversations(path(directory, "magincia.tlk"), template));
        locationConversations.put(0x0D, loadCharacterConversations(path(directory, "paws.tlk"), template));
        locationConversations.put(0x0E, loadCharacterConversations(path(directory, "den.tlk"), template));
        locationConversations.put(0x0F, loadCharacterConversations(path(directory, "vesper.tlk"), template));
        locationConversations.put(0x10, loadCharacterConversations(path(directory, "cove.tlk"), template));
        Map<NonPlayerCharacter, Dialog> npcConversations = new HashMap<>();
        npcConversations.put(NonPlayerCharacter.LORD_BRITISH, DialogBuilder.buildLordBritishDialog());
        npcConversations.put(NonPlayerCharacter.HAWKWIND, DialogBuilder.buildHawkwindDialog());
        return new Dialogs(locationConversations, npcConversations);
    }

    private static String path(String directory, String filename) {
        return String.format("%s%s%s", directory, File.separator, filename);
    }

    private static List<Dialog> loadCharacterConversations(String characterConversationsFilename, DialogTemplate template) throws IOException {
        List<Dialog> dialogs = new ArrayList<>();
        InputStream stream = Dialogs.class.getResourceAsStream(characterConversationsFilename);
        boolean done = false;
        while (! done) {
            Dialog dialog = buildCharacterConversationFromStream(stream, template);
            if (dialog == null) {
                done = true;
            }
            else {
                dialogs.add(dialog);
            }
        }
        return dialogs;
    }

    private static Dialog buildCharacterConversationFromStream(InputStream stream, DialogTemplate template) throws IOException {
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
        return new Dialog(
                questionFlag,
                responseAffectsHumility,
                turnAwayProbability,
                template.introTemplate(withinSentence(lookResponse)),
                conversationResponse(template.nameTemplate(name), pronoun, template),
                template.descriptionTemplate(withinSentence(lookResponse)),
                conversationResponse(jobResponse, pronoun, template),
                conversationResponse(healthResponse, pronoun, template),
                conversationResponse(template.noJoinTemplate(), pronoun, template),
                conversationResponse(keyword1Response, pronoun, template),
                conversationResponse(keyword2Response, pronoun, template),
                yesNoQuestion,
                conversationResponse(yesResponse, pronoun, template),
                conversationResponse(noResponse, pronoun, template),
                conversationResponse(template.unknownTemplate(), pronoun, template),
                conversationResponse(template.byeTemplate(), pronoun, template),
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

    private static String conversationResponse(String message, String pronoun, DialogTemplate template) {
        return template.speakingTemplate(pronoun) + " " + message;
    }

    private static String withinSentence(String message) {
        if (message.length() <= 1) {
            return message.toLowerCase();
        }
        return message.substring(0, 1).toLowerCase() + message.substring(1);
    }

    public Dialogs(Map<Integer, List<Dialog>> locationConversations, Map<NonPlayerCharacter, Dialog> npcConversations) {
        this.locationConversations = locationConversations;
        this.npcConversations = npcConversations;
    }

    public Optional<Dialog> findCharacterConversationFor(Integer locationCode, Person person) {
        if (person.nonPlayerCharacter() != NonPlayerCharacter.CITIZEN) {
            return Optional.ofNullable(npcConversations.get(person.nonPlayerCharacter()));
        }
        if (! locationConversations.containsKey(locationCode)) {
            return Optional.empty();
        }
        List<Dialog> dialogs = locationConversations.get(locationCode);
        return Optional.of(dialogs.get(person.conversationIndex() - 1));
    }
}
