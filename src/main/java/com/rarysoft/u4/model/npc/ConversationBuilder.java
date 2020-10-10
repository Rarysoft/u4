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

import java.util.Arrays;

public class ConversationBuilder {
    public static Conversation buildLordBritishConversation() {
        return new Conversation(
                ConversationType.LORD_BRITISH,
                4,
                false,
                0,
                "Thou see the King with the Royal Sceptre.",
                "Hey says: My name is Lord British, Sovereign of all Britannia!",
                "Thou see the King with the Royal Sceptre.",
                "He says: I rule all Britannia, and shall do my best to help thee!",
                "He says: I am well, thank ye.",
                "He says: I cannot join thee.",
                Arrays.asList(
                        "truth",
                        "love",
                        "courage",
                        "honesty",
                        "compassion",
                        "valor",
                        "justice",
                        "sacrifice",
                        "honor",
                        "spirituality",
                        "humility",
                        "pride",
                        "avatar",
                        "quest",
                        "britannia",
                        "ankh",
                        "abyss",
                        "mondain",
                        "minax",
                        "exodus",
                        "virtue"
                ),
                "Art thou well?",
                "He says: That is good.",
                "He says: Let me heal thy wounds!",
                "He says: That I cannot help thee with.",
                Arrays.asList(
                        "He says: Many truths can be learned at the Lycaeum. It lies on the northwestern shore of Verity Isle!",
                        "He says: Look for the meaning of Love at Empath Abbey. The Abbey sits on the western edge of the Deep Forest!",
                        "He says: Serpent's Castle on the Isle of Deeds is where Courage should be sought!",
                        "He says: The fair towne of Moonglow on Verity Isle is where the virtue of Honesty thrives!",
                        "He says: The bards in the towne of Britain are well versed in the virtue of Compassion!",
                        "He says: Many valiant fighters come from Jhelom in the Valarian Isles!",
                        "He says: In the city of Yew, in the Deep Forest, Justice is served!",
                        "He says: Minoc, towne of self-sacrifice, lies on the eastern shores of Lost Hope Bay!",
                        "He says: The Paladins who strive for Honor are oft seen in Trinsic, north of the Cape of Heroes!",
                        "He says: In Skara Brae the Spiritual path is taught. Find it on an isle near Spiritwood!",
                        "He says: Humility is the foundation of Virtue! The ruins of proud Magincia are a testimony unto the Virtue of Humility! Find the Ruins of Magincia far off the shores of Britannia, on a small isle in the vast Ocean!",
                        "He says: Of the eight combinations of Truth, Love and Courage, that which contains neither Truth, Love nor Courage is Pride.\n\nPride being not a Virtue must be shunned in favor of Humility, the Virtue which is the antithesis of Pride!",
                        "Lord British says: To be an Avatar is to be the embodiment of the Eight Virtues.\n\nIt is to live a life constantly and forever in the Quest to better thyself and the world in which we live.",
                        "Lord British says: The Quest of the Avatar is to know and become the embodiment of the Eight Virtues of Goodness! It is known that all who take on this Quest must prove themselves by conquering the Abyss and Viewing the Codex of Ultimate Wisdom!",
                        "He says: Even though the Great Evil Lords have been routed evil yet remains in Britannia.\n\nIf but one soul could complete the Quest of the Avatar, our people would have a new hope, a new goal for life.\n\nThere would be a shining example that there is more to life than the endless struggle for possessions and gold!",
                        "He says: The Ankh is the symbol of one who strives for Virtue. Keep it with thee at all times for by this mark thou shalt be known!",
                        "He says: The Great Stygian Abyss is the darkest pocket of evil remaining in Britannia! It is said that in the deepest recesses of the Abyss is the Chamber of the Codex!\n\nIt is also said that only one of highest Virtue may enter this Chamber, one such as an Avatar!!!",
                        "He says: Mondain is dead!",
                        "He says: Minax is dead!",
                        "He says: Exodus is dead!",
                        "He says: The Eight Virtues of the Avatar are: Honesty, Compassion, Valor, Justice, Sacrifice, Honor, Spirituality, and Humility!"
                )
        );
    }
}
