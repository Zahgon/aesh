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
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aesh.tamboui.examples;

import static dev.tamboui.toolkit.Toolkit.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.aesh.AeshConsoleRunner;
import org.aesh.command.CommandDefinition;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.option.Option;
import org.aesh.tamboui.TuiAppCommand;
import org.aesh.tamboui.TuiCommand;
import dev.tamboui.layout.Constraint;
import dev.tamboui.style.Color;
import dev.tamboui.style.Style;
import dev.tamboui.toolkit.app.ToolkitRunner;
import dev.tamboui.toolkit.element.Element;
import dev.tamboui.tui.TuiConfig;
import dev.tamboui.tui.TuiRunner;
import dev.tamboui.tui.event.KeyEvent;
import dev.tamboui.tui.event.TickEvent;
import dev.tamboui.widgets.barchart.Bar;
import dev.tamboui.widgets.barchart.BarGroup;
import dev.tamboui.widgets.block.Block;
import dev.tamboui.widgets.gauge.Gauge;
import dev.tamboui.widgets.sparkline.Sparkline;
import dev.tamboui.widgets.table.TableState;
import dev.tamboui.widgets.tabs.TabsState;

/**
 * Demo example showing TUI commands integrated with aesh.
 * Run this class and type the command names at the aesh prompt.
 *
 * @author Aesh team
 */
public class TuiDemoExample {

    /**
     * Minimal TuiAppCommand that shows a styled panel with a welcome message.
     * Press 'q' to quit (handled by TuiAppCommand default key handling).
     */
    @CommandDefinition(name = "tui-hello", description = "Show a hello panel")
    public static class HelloCommand extends TuiAppCommand {

        @Override
        protected Element render() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * TuiCommand with an animated progress bar that advances on each tick event.
     * Uses TuiRunner's event loop directly for fine-grained control.
     */
    @CommandDefinition(name = "tui-gauge", description = "Animated progress bar")
    public static class GaugeCommand extends TuiCommand {

        @Option(name = "speed", shortName = 's', defaultValue = { "100" }, description = "Tick rate in milliseconds")
        private int speedMs;

        @Override
        protected TuiConfig.Builder configure(TuiConfig.Builder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void runTui(TuiRunner runner, CommandInvocation invocation) throws Exception {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * TuiAppCommand showing sample data in a table with keyboard navigation.
     * Uses the declarative Element DSL via ToolkitRunner.
     */
    @CommandDefinition(name = "tui-table", description = "Show a data table")
    public static class TableCommand extends TuiAppCommand {

        private final TableState tableState = new TableState();

        @Override
        protected Element render() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected boolean onKeyEvent(KeyEvent event, ToolkitRunner runner) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Live sparkline chart with randomly generated data points.
     * New data points are added on each tick, simulating a live feed.
     */
    @CommandDefinition(name = "tui-sparkline", description = "Live sparkline chart")
    public static class SparklineCommand extends TuiCommand {

        @Override
        protected TuiConfig.Builder configure(TuiConfig.Builder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void runTui(TuiRunner runner, CommandInvocation invocation) throws Exception {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Bar chart showing simulated server metrics.
     */
    @CommandDefinition(name = "tui-barchart", description = "Server metrics bar chart")
    public static class BarChartCommand extends TuiAppCommand {

        @Override
        protected Element render() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private static Bar bar(long value, String label, Color color) {
            return Bar.builder().value(value).label(label).style(Style.EMPTY.fg(color)).build();
        }
    }

    /**
     * Tabbed interface with different content per tab.
     * Use left/right arrow keys to switch tabs.
     */
    @CommandDefinition(name = "tui-tabs", description = "Tabbed interface")
    public static class TabsCommand extends TuiAppCommand {

        private final TabsState tabsState = new TabsState(0);

        @Override
        protected Element render() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected boolean onKeyEvent(KeyEvent event, ToolkitRunner runner) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Calendar widget showing the current month with today highlighted.
     * Use left/right arrows to navigate months.
     */
    @CommandDefinition(name = "tui-calendar", description = "Calendar view")
    public static class CalendarCommand extends TuiAppCommand {

        private final AtomicReference<LocalDate> currentDate = new AtomicReference<>(LocalDate.now());

        @Override
        protected Element render() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected boolean onKeyEvent(KeyEvent event, ToolkitRunner runner) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Multi-panel dashboard combining gauges, sparkline, and bar chart.
     * All data updates live on each tick.
     */
    @CommandDefinition(name = "tui-dashboard", description = "Live system dashboard")
    public static class DashboardCommand extends TuiCommand {

        @Override
        protected TuiConfig.Builder configure(TuiConfig.Builder builder) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void runTui(TuiRunner runner, CommandInvocation invocation) throws Exception {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private static int clamp(int value, int min, int max) {
            return Math.max(min, Math.min(max, value));
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
