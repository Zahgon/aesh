/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples;

import java.io.IOException;
import java.nio.charset.Charset;
import org.aesh.command.Command;
import org.aesh.command.CommandDefinition;
import org.aesh.command.CommandResult;
import org.aesh.command.activator.CommandActivator;
import org.aesh.command.activator.OptionActivator;
import org.aesh.command.completer.CompleterInvocation;
import org.aesh.command.converter.ConverterInvocation;
import org.aesh.command.impl.registry.AeshCommandRegistryBuilder;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.registry.CommandRegistry;
import org.aesh.command.registry.CommandRegistryException;
import org.aesh.command.settings.SettingsBuilder;
import org.aesh.command.validator.ValidatorInvocation;
import org.aesh.console.ReadlineConsole;
import org.aesh.graphics.AeshGraphicsConfiguration;
import org.aesh.graphics.Graphics;
import org.aesh.graphics.GraphicsConfiguration;
import org.aesh.readline.prompt.Prompt;
import org.aesh.terminal.Connection;
import org.aesh.terminal.Key;
import org.aesh.terminal.formatting.Color;
import org.aesh.terminal.formatting.TerminalColor;
import org.aesh.terminal.tty.TerminalConnection;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class AeshGraphicsExample {

    public static void main(String[] args) throws CommandRegistryException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @CommandDefinition(name = "exit", description = "exit the program")
    public static class ExitCommand implements Command {

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    @CommandDefinition(name = "gfx", description = "")
    public static class GraphicsCommand implements Command {

        public GraphicsCommand(Connection connection) {
            gc = new AeshGraphicsConfiguration(connection);
        }

        private final GraphicsConfiguration gc;

        private CommandInvocation invocation;

        private Graphics g;

        @Override
        public CommandResult execute(CommandInvocation commandInvocation) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void waitForInput() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private void doGfx() {
            try {
                g = gc.getGraphics();
                g.setColor(new TerminalColor(Color.BLUE, Color.DEFAULT));
                g.drawRect(20, 10, 20, 4);
                Thread.sleep(500);
                g.flush();
                g.setColor(new TerminalColor(Color.RED, Color.DEFAULT));
                g.drawRect(50, 5, 10, 8);
                Thread.sleep(500);
                g.flush();
                g.setColor(new TerminalColor(Color.DEFAULT, Color.YELLOW));
                g.fillRect(0, 15, 16, 10);
                Thread.sleep(500);
                g.flush();
                g.setColor(new TerminalColor(Color.CYAN, Color.DEFAULT));
                g.drawString("ÆSH", 100, 25);
                g.drawString("RULES", 100, 26);
                Thread.sleep(500);
                g.flush();
                g.setColor(new TerminalColor(Color.WHITE, Color.WHITE));
                g.drawRect(80, 23, 40, 5);
                Thread.sleep(500);
                g.flush();
                g.setColor(new TerminalColor(Color.BLUE, Color.DEFAULT));
                g.drawCircle(100, 10, 5);
                g.flush();
                Thread.sleep(500);
                g.setColor(new TerminalColor(Color.DEFAULT, Color.DEFAULT));
                g.drawLine(0, 0, 50, 20);
                g.flush();
                Thread.sleep(1500);
                g.setColor(new TerminalColor(Color.DEFAULT, Color.RED));
                int j = 0;
                for (int i = 0; i < 100; i++) {
                    g.clear();
                    g.fillRect(i, 15 + j, 20, 8);
                    g.flush();
                    Thread.sleep(50);
                    if (i > 10 && i < 20 || (i > 30 && i < 40))
                        j++;
                    if (i < 10 || (i > 20 && i < 30) || (i > 40 && i < 50))
                        j--;
                }
                waitForInput();
            } catch (InterruptedException ignored) {
            }
        }
    }
}
