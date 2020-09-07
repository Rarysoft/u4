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
import com.rarysoft.u4.model.Game;
import com.rarysoft.u4.model.GameState;
import com.rarysoft.u4.model.Maps;
import com.rarysoft.u4.model.Tiles;
import com.rarysoft.u4.ui.GamePanel;
import com.rarysoft.u4.ui.GameWindow;
import com.rarysoft.u4.ui.KeyboardListener;
import com.rarysoft.u4.ui.util.FrameHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.MissingResourceException;
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
        Tiles tiles = Tiles.fromStream(ClassLoader.getSystemClassLoader().getResourceAsStream("data/shapes.ega"));
        GamePanel gamePanel = new GamePanel(tiles, 3);
        GameWindow gameWindow = createGameWindow(gamePanel);
        setGameWindowIcon(gameWindow);
        Game game = new Game(Maps.fromFiles("data/world.map"));
        game.addDisplayListener(gamePanel);
        gameWindow.addKeyListener(new KeyboardListener(game));
        FrameHelper.enableExitOnClose(gameWindow);
        FrameHelper.center(gameWindow);
        FrameHelper.maximize(gameWindow);
        FrameHelper.show(gameWindow);
        game.start(new GameState(78, 104));
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

    private GameWindow createGameWindow(GamePanel gamePanel) {
        try {
            Messages messages = new Messages();
            return new GameWindow(messages, gamePanel);
        }
        catch (MissingResourceException e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
            throw e;
        }
    }

    private void setGameWindowIcon(GameWindow gameWindow) {
        try {
            gameWindow.setIconImage(ImageIO.read(Launcher.class.getResource("/images/ankh.png")));
        }
        catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
        }
    }
}