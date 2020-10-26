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
package com.rarysoft.u4;

import com.rarysoft.u4.i18n.Messages;
import com.rarysoft.u4.model.*;
import com.rarysoft.u4.model.npc.DialogTemplate;
import com.rarysoft.u4.model.npc.Dialogs;
import com.rarysoft.u4.model.graphics.Charset;
import com.rarysoft.u4.model.Tiles;
import com.rarysoft.u4.model.WayFinder;
import com.rarysoft.u4.model.party.*;
import com.rarysoft.u4.model.party.Character;
import com.rarysoft.u4.ui.*;
import com.rarysoft.u4.ui.util.FrameHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Launcher().run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Launches the game.
     * <p>
     * The main details worth knowing about this method are:
     * <li>The system look and feel is used.</li>
     * <li>A log file is created in <code>${user.home}/.u4/u4.log</code>.</li>
     * <li>The icon <code>${project.dir}/images/icon.png</code> is used.</li>
     * <li>The main window is displayed centered on the user's screen.</li>
     */
    public void run() throws IOException {
        initializeLogFile();
        Messages messages = initializeMessages("i18n/messages");
        Tiles tiles = initializeTiles("/data/shapes.ega");
        Charset charset = initializeCharset("/data/charset.ega");
        BufferedImage icon = initializeIcon("/images/ankh.png");
        Maps maps = initializeMaps("/data");
        Dialogs dialogs = initializeConversations("/data", messages);
        UiBuilder uiBuilder = new UiBuilder();
        JFrame gameWindow = uiBuilder.buildGameWindow(messages.windowTitle());
        Game game = initializeGame(messages, gameWindow, dialogs);
        uiBuilder.buildGamePanel(gameWindow, game, tiles, charset);
        gameWindow.setIconImage(icon);
        FrameHelper.enableExitOnClose(gameWindow);
        FrameHelper.center(gameWindow);
        FrameHelper.maximize(gameWindow);
        FrameHelper.show(gameWindow);
        Party party = new Party();
        party.setPlayer0(new Character(20, 20, 0, 16, 14, 15, 13, 0, Weapon.HANDS, Armour.CLOTH, "Player Name", Sex.MALE, CharacterClass.BARD, Status.GOOD));
        party.setCurrentPartyLocation(Location.SURFACE);
        party.setDungeonLevel(1);
        party.setRow(maps.surface().startRow());
        party.setCol(maps.surface().startCol());
        game.start(new GameState(maps, party));
    }

    private void initializeLogFile() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            String logPath = String.format("%s%s.u4", System.getProperty("user.home"), File.separator);
            Path path = Paths.get(logPath);
            if (! path.toFile().exists()) {
                Files.createDirectory(path);
            }
            Logger.getGlobal().addHandler(new FileHandler(String.format("%s%su4.log", logPath, File.separator)));
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private Messages initializeMessages(String filename) {
        return new Messages(filename);
    }

    private Tiles initializeTiles(String filename) throws IOException {
        return Tiles.fromStream(Launcher.class.getResourceAsStream(filename));
    }

    private Charset initializeCharset(String filename) throws IOException {
        return Charset.fromStream(Launcher.class.getResourceAsStream(filename));
    }

    private Maps initializeMaps(String directory) throws IOException {
        return Maps.fromFiles(directory);
    }

    private Dialogs initializeConversations(String directory, Messages messages) throws IOException {
        return Dialogs.fromFiles(directory, new DialogTemplate() {
            @Override
            public String speakingTemplate(String pronoun) {
                return messages.speechCitizenSpeaking(pronoun);
            }

            @Override
            public String introTemplate(String lookResponse) {
                return messages.speechCitizenIntro(lookResponse);
            }

            @Override
            public String nameTemplate(String nameResponse) {
                return messages.speechCitizenName(nameResponse);
            }

            @Override
            public String descriptionTemplate(String lookResponse) {
                return messages.speechCitizenDescribe(lookResponse);
            }

            @Override
            public String noJoinTemplate() {
                return messages.speechCitizenNoJoin();
            }

            @Override
            public String unknownTemplate() {
                return messages.speechCitizenUnknown();
            }

            @Override
            public String byeTemplate() {
                return messages.speechCitizenBye();
            }
        });
    }

    private BufferedImage initializeIcon(String filename) {
        try {
            return ImageIO.read(Launcher.class.getResource(filename));
        }
        catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
            return null;
        }
    }

    private Game initializeGame(Messages messages, JFrame gameWindow, Dialogs dialogs) {
        Game game = new Game(messages, dialogs, new Random(), new ViewFinder(), new WayFinder());
        gameWindow.addKeyListener(new KeyboardListener(game));
        return game;
    }
}
