package org.aesh.command.impl.shell;

import java.util.concurrent.TimeUnit;
import org.aesh.command.impl.operator.OutputDelegate;
import org.aesh.command.shell.Shell;
import org.aesh.readline.prompt.Prompt;
import org.aesh.terminal.Key;
import org.aesh.terminal.tty.Size;
import org.aesh.terminal.utils.Config;
import org.aesh.terminal.utils.Parser;

/**
 * @author Aesh team
 */
public class ShellOutputDelegate implements Shell {

    private final Shell delegate;

    private final OutputDelegate output;

    public ShellOutputDelegate(Shell delegate, OutputDelegate output) {
        this.delegate = delegate;
        this.output = output;
    }

    private void doWrite(String out) {
        output.write(out);
    }

    @Override
    public void write(String out, boolean paging) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writeln(String out, boolean paging) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(int[] out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void write(char out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String readLine() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String readLine(Prompt prompt) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Key read() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Key read(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Key read(Prompt prompt) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean enableAlternateBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean enableMainBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Size size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
