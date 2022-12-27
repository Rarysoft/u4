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

//import com.rarysoft.u4.game.physics.ViewFinder;
//import com.rarysoft.u4.i18n.Messages;
//import com.rarysoft.u4.game.npc.Dialog;
//import com.rarysoft.u4.game.npc.Dialogs;
//import com.rarysoft.u4.game.npc.NonPlayerCharacter;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class GameTest {
//    @Mock
//    private Messages messages;
//
//    @Mock
//    private Dialogs dialogs;
//
//    @Mock
//    private InformationListener informationListener;
//
//    @Mock
//    private Dialog dialog;
//
//    @Mock
//    private ViewFinder viewFinder;
//
//    @InjectMocks
//    private Game game;
//
//    @Captor
//    private ArgumentCaptor<String> messageCaptor;
//
//    @Before
//    public void initializeGame() {
//        game.addInformationListener(informationListener);
//        RenderedTile empty = new RenderedTile().withBaseTile(Tile.BRICK_FLOOR);
//        RenderedTile[] emptyRow = new RenderedTile[] {
//                empty, empty, empty, empty, empty, empty, empty, empty, empty, empty,
//                empty,
//                empty, empty, empty, empty, empty, empty, empty, empty, empty, empty
//        };
//        RenderedTile[][] emptyMap = new RenderedTile[][] {
//                emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow,
//                emptyRow,
//                emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow, emptyRow
//        };
//        when(gameLogic.mapView(viewFinder, 10)).thenReturn(emptyMap);
//        when(gameLogic.dialog()).thenReturn(dialog);
//        when(messages.speechCitizenPrompt()).thenReturn("Prompt:");
//        game.start(gameLogic);
//    }
//
//    @Test
//    public void onUserInputWhenNotInConversationDoesNothing() {
//        when(gameLogic.inConversation()).thenReturn(false);
//
//        game.onUserInput('\n');
//
//        verify(informationListener, never()).responseRequested(any());
//    }
//
//    @Test
//    public void onUserInputWhenInConversationNotAwaitingResponseAndDescriptionRequestedRespondsWithDescription() {
//        when(gameLogic.inConversation()).thenReturn(true);
//        when(gameLogic.inConversationRespondingYesOrNo()).thenReturn(false);
//        when(gameLogic.input()).thenReturn("look");
//
//        when(dialog.getLookResponse()).thenReturn("You see a character.");
//
//        game.onUserInput('\n');
//
//        verifyResponse("You see a character.");
//    }
//
//    @Test
//    public void onUserInputWhenInConversationNotAwaitingResponseAndNameRequestedRespondsWithName() {
//        when(gameLogic.inConversation()).thenReturn(true);
//        when(gameLogic.inConversationRespondingYesOrNo()).thenReturn(false);
//        when(gameLogic.input()).thenReturn("name");
//
//        when(dialog.getNameResponse()).thenReturn("Pronoun says: I am Character Name.");
//
//        game.onUserInput('\n');
//
//        verifyResponse("Pronoun says: I am Character Name.");
//    }
//
//    @Test
//    public void onUserInputWhenInConversationNotAwaitingResponseAndJobRequestedRespondsWithJob() {
//        when(gameLogic.inConversation()).thenReturn(true);
//        when(gameLogic.inConversationRespondingYesOrNo()).thenReturn(false);
//        when(gameLogic.input()).thenReturn("job");
//
//        when(dialog.getJobResponse()).thenReturn("Pronoun says: I have a job.");
//
//        game.onUserInput('\n');
//
//        verifyResponse("Pronoun says: I have a job.");
//    }
//
//    @Test
//    public void onUserInputWhenInConversationNotAwaitingResponseAndHealthRequestedRespondsWithHealth() {
//        when(gameLogic.inConversation()).thenReturn(true);
//        when(gameLogic.inConversationRespondingYesOrNo()).thenReturn(false);
//        when(gameLogic.input()).thenReturn("health");
//
//        when(dialog.getHealthResponse()).thenReturn("Pronoun says: I have health.");
//
//        game.onUserInput('\n');
//
//        verifyResponse("Pronoun says: I have health.");
//    }
//
//    @Test
//    public void onUserInputWhenInConversationNotAwaitingResponseAndKeyword1RequestedRespondsWithKeyword1Response() {
//        when(gameLogic.inConversation()).thenReturn(true);
//        when(gameLogic.inConversationRespondingYesOrNo()).thenReturn(false);
//        when(gameLogic.input()).thenReturn("key1");
//
//        when(dialog.getNpc()).thenReturn(NonPlayerCharacter.CITIZEN);
//        when(dialog.getKeyword(0)).thenReturn("KEY1");
//        when(dialog.getKeywordResponse(0)).thenReturn("Pronoun says: Blah 1.");
//
//        game.onUserInput('\n');
//
//        verifyResponse("Pronoun says: Blah 1.");
//    }
//
//    @Test
//    public void onUserInputWhenInConversationNotAwaitingResponseAndKeyword2RequestedRespondsWithKeyword2Response() {
//        when(gameLogic.inConversation()).thenReturn(true);
//        when(gameLogic.inConversationRespondingYesOrNo()).thenReturn(false);
//        when(gameLogic.input()).thenReturn("key2");
//
//        when(dialog.getNpc()).thenReturn(NonPlayerCharacter.CITIZEN);
//        when(dialog.getKeyword(1)).thenReturn("KEY2");
//        when(dialog.getKeywordResponse(1)).thenReturn("Pronoun says: Blah 2.");
//
//        game.onUserInput('\n');
//
//        verifyResponse("Pronoun says: Blah 2.");
//    }
//
//    private void verifyResponse(String message) {
//        verify(informationListener).responseRequested(messageCaptor.capture());
//        String result = messageCaptor.getValue();
//        assertThat(result).isNotNull().isEqualTo(message + "\nPrompt:");
//    }
}
