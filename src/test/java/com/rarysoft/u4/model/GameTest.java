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

import com.rarysoft.u4.i18n.Messages;
import com.rarysoft.u4.model.npc.CharacterConversation;
import com.rarysoft.u4.model.npc.ConversationType;
import com.rarysoft.u4.model.npc.CharacterConversations;
import com.rarysoft.u4.model.graphics.Tile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    @Mock
    private Messages messages;

    @Mock
    private CharacterConversations characterConversations;

    @Mock
    private DisplayListener displayListener;

    @Mock
    private GameState gameState;

    @Mock
    private CharacterConversation characterConversation;

    @InjectMocks
    private Game game;

    @Captor
    private ArgumentCaptor<List<String>> messagesCaptor;

    @Before
    public void initializeGame() {
        game.addDisplayListener(displayListener);
        RenderedTile empty = new RenderedTile(Tile.BRICK_FLOOR, null);
        RenderedTile[] emptyRow = new RenderedTile[] {
                empty, empty, empty, empty, empty, empty, empty, empty, empty, empty,
                empty,
                empty, empty, empty, empty, empty, empty, empty, empty, empty, empty
        };
        RenderedTile[][] emptyMap = new RenderedTile[][] {
                emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow,
                emptyRow,
                emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow
        };
        when(gameState.mapView(10)).thenReturn(emptyMap);
        when(gameState.conversation()).thenReturn(characterConversation);
        when(messages.speechCitizenPrompt()).thenReturn("Prompt:");
        game.start(gameState);
    }

    @Test
    public void onUserInputWhenNotInConversationDoesNothing() {
        when(gameState.inConversation()).thenReturn(false);

        game.onUserInput('\n');

        verify(displayListener, never()).responseRequested(any());
    }

    @Test
    public void onUserInputWhenInConversationNotAwaitingResponseAndDescriptionRequestedRespondsWithDescription() {
        when(gameState.inConversation()).thenReturn(true);
        when(gameState.inConversationRespondingYesOrNo()).thenReturn(false);
        when(gameState.input()).thenReturn("look");

        when(characterConversation.getLookResponse()).thenReturn("You see a character.");

        game.onUserInput('\n');

        verifyResponse("You see a character.");
    }

    @Test
    public void onUserInputWhenInConversationNotAwaitingResponseAndNameRequestedRespondsWithName() {
        when(gameState.inConversation()).thenReturn(true);
        when(gameState.inConversationRespondingYesOrNo()).thenReturn(false);
        when(gameState.input()).thenReturn("name");

        when(characterConversation.getNameResponse()).thenReturn("Pronoun says: I am Character Name.");

        game.onUserInput('\n');

        verifyResponse("Pronoun says: I am Character Name.");
    }

    @Test
    public void onUserInputWhenInConversationNotAwaitingResponseAndJobRequestedRespondsWithJob() {
        when(gameState.inConversation()).thenReturn(true);
        when(gameState.inConversationRespondingYesOrNo()).thenReturn(false);
        when(gameState.input()).thenReturn("job");

        when(characterConversation.getJobResponse()).thenReturn("Pronoun says: I have a job.");

        game.onUserInput('\n');

        verifyResponse("Pronoun says: I have a job.");
    }

    @Test
    public void onUserInputWhenInConversationNotAwaitingResponseAndHealthRequestedRespondsWithHealth() {
        when(gameState.inConversation()).thenReturn(true);
        when(gameState.inConversationRespondingYesOrNo()).thenReturn(false);
        when(gameState.input()).thenReturn("health");

        when(characterConversation.getHealthResponse()).thenReturn("Pronoun says: I have health.");

        game.onUserInput('\n');

        verifyResponse("Pronoun says: I have health.");
    }

    @Test
    public void onUserInputWhenInConversationNotAwaitingResponseAndKeyword1RequestedRespondsWithKeyword1Response() {
        when(gameState.inConversation()).thenReturn(true);
        when(gameState.inConversationRespondingYesOrNo()).thenReturn(false);
        when(gameState.input()).thenReturn("key1");

        when(characterConversation.getType()).thenReturn(ConversationType.CITIZEN);
        when(characterConversation.getKeyword(0)).thenReturn("KEY1");
        when(characterConversation.getKeywordResponse(0)).thenReturn("Pronoun says: Blah 1.");

        game.onUserInput('\n');

        verifyResponse("Pronoun says: Blah 1.");
    }

    @Test
    public void onUserInputWhenInConversationNotAwaitingResponseAndKeyword2RequestedRespondsWithKeyword2Response() {
        when(gameState.inConversation()).thenReturn(true);
        when(gameState.inConversationRespondingYesOrNo()).thenReturn(false);
        when(gameState.input()).thenReturn("key2");

        when(characterConversation.getType()).thenReturn(ConversationType.CITIZEN);
        when(characterConversation.getKeyword(1)).thenReturn("KEY2");
        when(characterConversation.getKeywordResponse(1)).thenReturn("Pronoun says: Blah 2.");

        game.onUserInput('\n');

        verifyResponse("Pronoun says: Blah 2.");
    }

    private void verifyResponse(String message) {
        verify(displayListener).responseRequested(messagesCaptor.capture());
        List<String> result = messagesCaptor.getValue();
        assertThat(result).isNotNull().isNotEmpty().hasSize(4).containsExactly("", message, "", "Prompt:");
    }
}
