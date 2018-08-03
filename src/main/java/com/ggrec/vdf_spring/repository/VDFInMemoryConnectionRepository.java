package com.ggrec.vdf_spring.repository;

import org.springframework.social.connect.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VDFInMemoryConnectionRepository implements ConnectionRepository {

    // <providerId, Connection<provider API>>
    private MultiValueMap<String, Connection<?>> connections;

    private ConnectionFactoryLocator connectionFactoryLocator;

    public VDFInMemoryConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.connections = new LinkedMultiValueMap<>();
    }

    public MultiValueMap<String, Connection<?>> findAllConnections() {
        return connections;
    }

    public List<Connection<?>> findConnections(String providerId) {
        List<Connection<?>> emptyConnectionList = Collections.emptyList();
        return connections.containsKey(providerId) ? connections.get(providerId) : emptyConnectionList;
    }

    @SuppressWarnings("unchecked")
    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        List<?> providerConnections = findConnections(getProviderId(apiType));
        return (List<Connection<A>>) providerConnections;
    }

    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
        Assert.notEmpty(providerUserIds);
        MultiValueMap<String, Connection<?>> connectionsToUsers = new LinkedMultiValueMap<String, Connection<?>>(providerUserIds.size());
        for (Map.Entry<String, List<String>> providerConnectionEntry : providerUserIds.entrySet()) {
            String providerId = providerConnectionEntry.getKey();
            List<String> userIds = providerConnectionEntry.getValue();
            if (connections.containsKey(providerId)) {
                List<Connection<?>> providerConnections = connections.get(providerId);

                for (Connection<?> connection : providerConnections) {
                    if (userIds.contains(connection.getKey().getProviderUserId())) {
                        connectionsToUsers.add(providerId, connection);
                    }
                }
            }
        }
        return connectionsToUsers;
    }

    public Connection<?> getConnection(ConnectionKey connectionKey) {
        if (connections.containsKey(connectionKey.getProviderId())) {
            List<Connection<?>> providerConnections = connections.get(connectionKey.getProviderId());
            for (Connection<?> connection : providerConnections) {
                if (connection.getKey().equals(connectionKey)) {
                    return connection;
                }
            }
        }
        throw new NoSuchConnectionException(connectionKey);
    }

    @SuppressWarnings("unchecked")
    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        return (Connection<A>) getConnection(new ConnectionKey(getProviderId(apiType), providerUserId));
    }

    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        Connection<A> primaryConnection = findPrimaryConnection(apiType);
        if (primaryConnection == null) {
            throw new NotConnectedException(getProviderId(apiType));
        }
        return primaryConnection;
    }

    @SuppressWarnings("unchecked")
    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        String providerId = getProviderId(apiType);
        if (connections.containsKey(providerId)) {
            return (Connection<A>) connections.get(providerId).get(0);
        }
        return null;
    }

    public void addConnection(Connection<?> connection) {
        try {
            ConnectionKey connectionKey = connection.getKey();
            getConnection(connectionKey);
            throw new DuplicateConnectionException(connectionKey);
        } catch (NoSuchConnectionException e) {
            connections.add(connection.createData().getProviderId(), connection);
        }
    }

    public void updateConnection(Connection<?> connection) {
        connections.add(connection.createData().getProviderId(), connection);
    }

    public void removeConnections(String providerId) {
        connections.remove(providerId);
    }

    public void removeConnection(ConnectionKey connectionKey) {
        String providerId = connectionKey.getProviderId();
        if (connections.containsKey(providerId)) {
            List<Connection<?>> providerConnections = connections.get(providerId);
            for (Connection<?> connection : providerConnections) {
                if (connection.getKey().equals(connectionKey)) {
                    providerConnections.remove(connection);
                }
            }
        }
    }

    private <A> String getProviderId(Class<A> apiType) {
        return connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
    }

}
