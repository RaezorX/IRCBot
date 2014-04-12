package net.raezorx;

import net.raezorx.exceptions.ConnectionFailedException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * User: Raezor
 * Date: 4/7/2014
 * Time: 11:45 PM
 */
public abstract class Connection {
    public static final int BUFFER_LEN = 512;
    public static final byte NEW_LINE = (byte) '\n';
    public static final byte LINE_FEED = (byte) '\r';

    protected Socket socket;
    protected SocketChannel channel;
    protected String bindAddress;
    protected InetAddress localAddress;
    protected InetAddress remoteAddress;
    protected OutputStream output;
    protected InputStream input;
    protected BotConfig config;
    protected int bindPort;
    protected long uptime;

    public Connection() {
        this("", 0);
    }

    public Connection(String bindAddress, int bindPort) {
        if (bindAddress == null) {
            throw new IllegalArgumentException("Supplied bindAddress is null");
        }
        this.bindAddress = bindAddress.trim();
        this.bindPort = bindPort;
    }

    public Connection(Socket socket) throws IOException {
        this.socket = socket;

        if (socket.isBound()) {
            this.socket.setReuseAddress(true);
        }

        this.bindAddress = socket.getLocalAddress().getHostAddress();
        this.bindPort = socket.getLocalPort();
        this.localAddress = socket.getLocalAddress();
        this.channel = socket.getChannel();
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
    }

    public abstract void connect (String remoteAddress, int remotePort) throws ConnectionFailedException;

    public BotConfig getConfig() {
        return config;
    }

    public String getHostname() {
            return socket.getInetAddress().getHostName() ;
    }

    public InputStream getInput() {
        if (socket != null || socket.isConnected()) {
            return input;
        }
        return null;
    }

    public OutputStream getOutput() {
        if (socket != null || socket.isConnected()) {
            return output;
        }
        return null;
    }

    public int getLocalPort() {
        return socket == null ? 0 : socket.getLocalPort();
    }

    public int getPort() {
        return socket == null ? 0 : socket.getPort();
    }

    public void setConfig(BotConfig config) {
        this.config = config;
    }

    public long getUptime(boolean duration) {
        if (uptime == 0) {
            return 0;
        }
        if (duration) {
            return System.currentTimeMillis() - uptime;
        }
        return uptime;
    }
}
