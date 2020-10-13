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

public class DialogBuilder {
    public static Dialog buildLordBritishDialog() {
        return new Dialog(
                NonPlayerCharacter.LORD_BRITISH,
                Dialog.QUESTION_FLAG_HEALTH,
                false,
                0,
                "Thou see the King with the Royal Sceptre.",
                "He says: My name is Lord British, Sovereign of all Britannia!",
                "Thou see the King with the Royal Sceptre.",
                "He says: I rule all Britannia, and shall do my best to help thee!",
                "He says: I am well, thank ye.",
                "He says: I cannot join thee.",
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
                ),
                "Art thou well?",
                "He says: That is good.",
                "He says: Let me heal thy wounds!",
                "He says: That I cannot help thee with.",
                Arrays.asList(
                        "TRUT",
                        "LOVE",
                        "COUR",
                        "HONE",
                        "COMP",
                        "VALO",
                        "JUST",
                        "SACR",
                        "HONO",
                        "SPIR",
                        "HUMI",
                        "PRID",
                        "AVAT",
                        "QUES",
                        "BRIT",
                        "ANKH",
                        "ABYS",
                        "MOND",
                        "MINA",
                        "EXOD",
                        "VIRT"
                )
        );
    }

    public static Dialog buildHawkwindDialog() {
        return new Dialog(
                NonPlayerCharacter.HAWKWIND,
                Dialog.QUESTION_FLAG_NONE,
                false,
                0,
                "He says: That is not a subject for enlightenment.",
                "Welcome, {player}\nI am Hawkwind, Seer of Souls. I see that which is within thee and drives thee to deeds of good or evil...\n\nFor what path dost thou seek enlightenment?",
                "He says: That is not a subject for enlightenment.",
                "He says: That is not a subject for enlightenment.",
                "He says: That is not a subject for enlightenment.",
                "He says: That is not a subject for enlightenment.",
                Arrays.asList(
                        "Thou art a thief and a scoundrel. Thou may not ever become an Avatar!",
                        "Thou art a cold and cruel brute. Thou shouldst go to prison for thy crimes!",
                        "Thou art a coward, thou dost flee from the hint of danger!",
                        "Thou art an unjust wretch. Thou are a fulsome meddler!",
                        "Thou art a self-serving Tufthunter. Thou deservest not my help, yet I grant it!",
                        "Thou art a cad and a bounder. Thy presence is an affront. Thou art low as a slug!",
                        "Thy spirit is weak and feeble. Thou dost not strive for Perfection!",
                        "Thou art proud and vain. All other virtue in thee is a loss!",
                        "Thou art not an honest soul. Thou must live a more honest life to be an Avatar!",
                        "Thou dost kill where there is no need and give too little unto others!",
                        "Thou dost not display a great deal of Valor. Thou dost flee before the need!",
                        "Thou art cruel and unjust. In time thou will suffer for thy crimes!",
                        "Thou dost need to think more of the life of others and less of thy own!",
                        "Thou dost not fight with honor but with malice and deceit!",
                        "Thou dost not take time to care about thy inner being, a must to be an Avatar!",
                        "Thou art too proud of thy little deeds. Humility is the root of all Virtue!",
                        "Thou hast made little progress on the paths of Honesty. Strive to prove thy worth!",
                        "Thou hast not shown thy compassion well. Be more kind unto others!",
                        "Thou art not yet a valiant warrior. Fight to defeat evil and prove thyself!",
                        "Thou hast not proven thyself to be just. Strive to do justice unto all things!",
                        "Thy sacrifice is small. Give of thy life's blood so that others may live.",
                        "Thou dost need to show thyself to be more honorable. The path lies before thee!",
                        "Strive to know and master more of thine inner being. Meditation lights the path!",
                        "Thy progress on this path is most uncertain. Without Humility thou art empty!",
                        "Thou dost seem to be an honest soul. Continued honesty will reward thee!",
                        "Thou dost show thy compassion well. Continued goodwill should be thy guide!",
                        "Thou art showing Valor in the face of danger. Strive to become yet more so!",
                        "Thou dost seem fair and just. Strive to uphold Justice even more sternly!",
                        "Thou art giving of thyself in some ways. Seek ye now to find yet more!",
                        "Thou dost seem to be Honorable in nature. Seek to bring Honor upon others as well!",
                        "Thou art doing well on the path to inner sight continue to seek the inner light!",
                        "Thou dost seem a humble soul. Thou art setting strong stones to build virtues upon!",
                        "Thou art truly an honest soul. Seek ye now to reach Elevation!",
                        "Compassion is a virtue that thou hast shown well. Seek ye now Elevation!",
                        "Thou art a truly valiant warrior. Seek ye now Elevation in the virtue of valor!",
                        "Thou art just and fair. Seek ye now the Elevation!",
                        "Thou art giving and good. Thy self-sacrifice is great. Seek now Elevation!",
                        "Thou hast proven thyself to be Honorable. Seek ye now for the Elevation!",
                        "Spirituality is in thy nature. Seek ye now the Elevation!",
                        "Thy Humility shines bright upon thy being. Seek ye now for Elevation!\n\n",
                        "He says: Thou hast become a partial Avatar in that attribute. Thou need not my insights."
                ),
                null,
                null,
                null,
                "He says: That is not a subject for enlightenment.",
                Arrays.asList(
                        "HONE-1",
                        "COMP-1",
                        "VALO-1",
                        "JUST-1",
                        "SACR-1",
                        "HONO-1",
                        "SPIR-1",
                        "HUMI-1",
                        "HONE-2",
                        "COMP-2",
                        "VALO-2",
                        "JUST-2",
                        "SACR-2",
                        "HONO-2",
                        "SPIR-2",
                        "HUMI-2",
                        "HONE-3",
                        "COMP-3",
                        "VALO-3",
                        "JUST-3",
                        "SACR-3",
                        "HONO-3",
                        "SPIR-3",
                        "HUMI-3",
                        "HONE-4",
                        "COMP-4",
                        "VALO-4",
                        "JUST-4",
                        "SACR-4",
                        "HONO-4",
                        "SPIR-4",
                        "HUMI-4",
                        "HONE-5",
                        "COMP-5",
                        "VALO-5",
                        "JUST-5",
                        "SACR-5",
                        "HONO-5",
                        "SPIR-5",
                        "HUMI-5",
                        "HONE-6",
                        "COMP-6",
                        "VALO-6",
                        "JUST-6",
                        "SACR-6",
                        "HONO-6",
                        "SPIR-6",
                        "HUMI-6"
                )
        );
    }
}
