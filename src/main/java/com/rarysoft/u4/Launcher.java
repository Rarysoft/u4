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

import com.rarysoft.u4.game.event.EventManager;
import com.rarysoft.u4.game.physics.ViewFinder;
import com.rarysoft.u4.game.state.GameState;
import com.rarysoft.u4.game.state.SaveState;
import com.rarysoft.u4.game.state.StateManager;
import com.rarysoft.u4.game.u5.U5MapEnhancer;
import com.rarysoft.u4.game.u5.U5SurfaceMapper;
import com.rarysoft.u4.game.u5.U5TileMapper;
import com.rarysoft.u4.i18n.Messages;
import com.rarysoft.u4.game.*;
import com.rarysoft.u4.game.npc.DialogTemplate;
import com.rarysoft.u4.game.npc.Dialogs;
import com.rarysoft.u4.ui.graphics.Charset;
import com.rarysoft.u4.game.Tiles;
import com.rarysoft.u4.game.physics.WayFinder;
import com.rarysoft.u4.ui.*;
import com.rarysoft.u4.ui.util.FrameHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
                new Launcher().run(args != null && args.length > 0 && args[0].equalsIgnoreCase("u5"));
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
    public void run(boolean useU5TileSet) throws IOException {
        initializeLogFile();
        Messages messages = initializeMessages("i18n/messages");
        Tiles tiles = initializeTiles(useU5TileSet ? "/data/u5/tiles.ega" : "/data/shapes.ega", useU5TileSet);
        Charset charset = initializeCharset("/data/charset.ega");
        BufferedImage icon = initializeIcon("/images/ankh.png");
        Maps maps = initializeMaps("/data", useU5TileSet);
        MapEnhancer mapEnhancer = useU5TileSet ? new U5MapEnhancer() : new DefaultMapEnhancer();
        Dialogs dialogs = initializeConversations("/data", messages);
        UiBuilder uiBuilder = new UiBuilder();
        JFrame gameWindow = uiBuilder.buildGameWindow(messages.windowTitle());
        EventManager eventManager = new EventManager();
        SaveState saveState = initializeParty("/data/party.sav");
        StateManager stateManager = new StateManager(eventManager, new GameState(saveState));
        Game game = initializeGame(stateManager, messages, maps, mapEnhancer, gameWindow, dialogs);
        uiBuilder.buildGamePanel(gameWindow, eventManager, tiles, charset);
        gameWindow.setIconImage(icon);
        FrameHelper.enableExitOnClose(gameWindow);
        FrameHelper.center(gameWindow);
        FrameHelper.maximize(gameWindow);
        FrameHelper.show(gameWindow);
        game.start();
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

    private Tiles initializeTiles(String filename, boolean useU5TileSet) throws IOException {
        return Tiles.fromStream(Launcher.class.getResourceAsStream(filename), useU5TileSet ? new U5TileMapper() : new DefaultTileMapper());
    }

    private Charset initializeCharset(String filename) throws IOException {
        return Charset.fromStream(Launcher.class.getResourceAsStream(filename));
    }

    private Maps initializeMaps(String directory, boolean useU5TileSet) throws IOException {
        return Maps.fromFiles(directory, useU5TileSet ? new U5SurfaceMapper() : null);
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

    private Game initializeGame(StateManager stateManager, Messages messages, Maps maps, MapEnhancer mapEnhancer, JFrame gameWindow, Dialogs dialogs) {
        Game game = new Game(stateManager, messages, maps, mapEnhancer, dialogs, new Random(), new ViewFinder(), new WayFinder());
        gameWindow.addKeyListener(new KeyboardListener(game));
        return game;
    }

    private SaveState initializeParty(String filename) throws IOException {
        PartyLoader partyLoader = new PartyLoader(new CharacterLoader());
        return partyLoader.load(Launcher.class.getResourceAsStream(filename));
    }
}
