package net.raezorx;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * User: Raezor
 * Date: 4/9/2014
 * Time: 11:51 PM
 */
public class ConnectionManager {
    private final Map<String, Connection> connections = new HashMap<>();

    public void add(String id, Connection connection) {
        if (!connections.containsKey(id) && !connections.containsValue(connection)) {
            connections.put(id, connection);
        }
    }

    public void remove(String id) {
        if (connections.containsKey(id)) {
            connections.remove(id);
        }
    }

    public void remove(Connection connection) {
        if (connections.containsValue(connection)) {
            connections.remove(connection);
        }
    }

    public Connection get(String id) {
        if (connections.containsKey(id)) {
            return connections.get(id);
        }
        return null;
    }

    public Collection<Connection> getConnections() {
        return new LinkedList<>(connections.values());
    }

    public Collection<String> getConnectionIDs() {
        return new LinkedList<>(connections.keySet());
    }
}
