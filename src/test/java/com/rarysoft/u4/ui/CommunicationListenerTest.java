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
package com.rarysoft.u4.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommunicationListenerTest {
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque id mollis orci. Pellentesque vitae augue non ligula laoreet sagittis posuere nec felis.";

    @Mock
    private CommunicationProvider communicationProvider;

    @InjectMocks
    private CommunicationListener communicationListener;

    @Captor
    private ArgumentCaptor<List<String>> textLinesCaptor;

    @Test
    public void initializeAlwaysDisplaysNoText() {
        communicationListener.initialize();

        verify(communicationProvider).showText(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().isEmpty();
    }

    @Test
    public void actionCompletedWhenTextDoesNotNeedWrappingShowsSingleLineOfText() {
        communicationListener.actionCompleted("Lorem ipsum");

        verify(communicationProvider).showText(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(1).containsExactly("Lorem ipsum");
    }

    @Test
    public void actionCompletedWhenTextContainsLineBreaksShowsMultipleTextLines() {
        communicationListener.actionCompleted("Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit.\nQuisque id mollis orci.");

        verify(communicationProvider).showText(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(3).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "consectetur adipiscing elit.",
                "Quisque id mollis orci."
        );
    }

    @Test
    public void actionCompletedWhenTextContainsConsecutiveLineBreaksShowsMultipleTextLinesWithBlankLines() {
        communicationListener.actionCompleted("Lorem ipsum dolor sit amet,\n\nconsectetur adipiscing elit.\n\nQuisque id mollis orci.");

        verify(communicationProvider).showText(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(5).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "",
                "consectetur adipiscing elit.",
                "",
                "Quisque id mollis orci."
        );
    }

    @Test
    public void actionCompletedWhenTextContainsLongLineShowsMultipleTextLines() {
        communicationListener.actionCompleted(LOREM_IPSUM);

        verify(communicationProvider).showText(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(6).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "consectetur adipiscing elit.",
                "Quisque id mollis orci.",
                "Pellentesque vitae augue non",
                "ligula laoreet sagittis",
                "posuere nec felis."
        );
    }

    @Test
    public void actionCompletedWhenTextContainsLongLineWithLineBreaksShowsMultipleTextLines() {
        communicationListener.actionCompleted(String.format("%s\nLorem\nipsum\ndolor sit\namet. %s", LOREM_IPSUM, LOREM_IPSUM));

        verify(communicationProvider).showText(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(15).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "consectetur adipiscing elit.",
                "Quisque id mollis orci.",
                "Pellentesque vitae augue non",
                "ligula laoreet sagittis",
                "posuere nec felis.",
                "Lorem",
                "ipsum",
                "dolor sit",
                "amet. Lorem ipsum dolor sit",
                "amet, consectetur adipiscing",
                "elit. Quisque id mollis",
                "orci. Pellentesque vitae",
                "augue non ligula laoreet",
                "sagittis posuere nec felis."
        );
    }

    @Test
    public void responseRequestedWhenTextDoesNotNeedWrappingShowsSingleLineOfText() {
        communicationListener.responseRequested("Lorem ipsum");

        verify(communicationProvider).showTextAndAwaitResponse(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(1).containsExactly("Lorem ipsum");
    }

    @Test
    public void responseRequestedWhenTextContainsLineBreaksShowsMultipleTextLines() {
        communicationListener.responseRequested("Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit.\nQuisque id mollis orci.");

        verify(communicationProvider).showTextAndAwaitResponse(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(3).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "consectetur adipiscing elit.",
                "Quisque id mollis orci."
        );
    }

    @Test
    public void responseRequestedWhenTextContainsConsecutiveLineBreaksShowsMultipleTextLinesWithBlankLines() {
        communicationListener.responseRequested("Lorem ipsum dolor sit amet,\n\nconsectetur adipiscing elit.\n\nQuisque id mollis orci.");

        verify(communicationProvider).showTextAndAwaitResponse(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(5).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "",
                "consectetur adipiscing elit.",
                "",
                "Quisque id mollis orci."
        );
    }

    @Test
    public void responseRequestedWhenTextContainsLongLineShowsMultipleTextLines() {
        communicationListener.responseRequested(LOREM_IPSUM);

        verify(communicationProvider).showTextAndAwaitResponse(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(6).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "consectetur adipiscing elit.",
                "Quisque id mollis orci.",
                "Pellentesque vitae augue non",
                "ligula laoreet sagittis",
                "posuere nec felis."
        );
    }

    @Test
    public void responseRequestedWhenTextContainsLongLineWithLineBreaksShowsMultipleTextLines() {
        communicationListener.responseRequested(String.format("%s\nLorem\nipsum\ndolor sit\namet. %s", LOREM_IPSUM, LOREM_IPSUM));

        verify(communicationProvider).showTextAndAwaitResponse(textLinesCaptor.capture());
        assertThat(textLinesCaptor.getValue()).isNotNull().hasSize(15).containsExactly(
                "Lorem ipsum dolor sit amet,",
                "consectetur adipiscing elit.",
                "Quisque id mollis orci.",
                "Pellentesque vitae augue non",
                "ligula laoreet sagittis",
                "posuere nec felis.",
                "Lorem",
                "ipsum",
                "dolor sit",
                "amet. Lorem ipsum dolor sit",
                "amet, consectetur adipiscing",
                "elit. Quisque id mollis",
                "orci. Pellentesque vitae",
                "augue non ligula laoreet",
                "sagittis posuere nec felis."
        );
    }

    @Test
    public void inputReceivedAlwaysShowsInput() {
        communicationListener.inputReceived("Lorem ipsum");

        verify(communicationProvider).showInput("Lorem ipsum");
    }
}
