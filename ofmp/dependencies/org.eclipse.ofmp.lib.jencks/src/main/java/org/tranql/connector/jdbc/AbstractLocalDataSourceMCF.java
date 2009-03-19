/*
 * Copyright (c) 2004 - 2007, Tranql project contributors
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
 *
 */
package org.tranql.connector.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.InvalidPropertyException;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.security.auth.Subject;

import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.ExceptionSorter;
import org.tranql.connector.ManagedConnectionHandle;
import org.tranql.connector.UserPasswordManagedConnectionFactory;

/**
 * @version $Revision: 1.1 $ $Date: 2008/11/13 03:25:10 $
 */
public abstract class AbstractLocalDataSourceMCF implements UserPasswordManagedConnectionFactory {
    protected final javax.sql.DataSource dataSource;

    private final ExceptionSorter exceptionSorter;
    private final boolean commitBeforeAutocommit;

    protected AbstractLocalDataSourceMCF(javax.sql.DataSource xaDataSource, ExceptionSorter exceptionSorter, boolean commitBeforeAutocommit) {
        this.dataSource = xaDataSource;
        this.exceptionSorter = exceptionSorter;
        this.commitBeforeAutocommit = commitBeforeAutocommit;
    }

    public Object createConnectionFactory() throws ResourceException {
        throw new NotSupportedException("ConnectionManager is required");
    }

    public Object createConnectionFactory(ConnectionManager connectionManager) throws ResourceException {
        return new DataSource(this, connectionManager);
    }

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        CredentialExtractor credentialExtractor = new CredentialExtractor(subject, connectionRequestInfo, this);

        Connection jdbcConnection = getPhysicalConnection(subject, credentialExtractor);
        return new ManagedJDBCConnection(this, jdbcConnection, credentialExtractor, exceptionSorter, commitBeforeAutocommit);
    }

    protected Connection getPhysicalConnection(Subject subject, CredentialExtractor credentialExtractor) throws ResourceException {
        try {
            return dataSource.getConnection(credentialExtractor.getUserName(), credentialExtractor.getPassword());
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("Unable to obtain physical connection to " + dataSource, e);
        }
    }

    public ManagedConnection matchManagedConnections(Set set, Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        for (Iterator i = set.iterator(); i.hasNext();) {
            Object o = i.next();
            if (o instanceof ManagedConnectionHandle) {
                ManagedConnectionHandle mc = (ManagedConnectionHandle) o;
                if (mc.matches(this, subject, connectionRequestInfo)) {
                    return mc;
                }
            }
        }
        return null;
    }

    public PrintWriter getLogWriter() throws ResourceException {
        try {
            return dataSource.getLogWriter();
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException(e.getMessage(), e);
        }
    }

    public void setLogWriter(PrintWriter log) throws ResourceException {
        try {
            dataSource.setLogWriter(log);
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException(e.getMessage(), e);
        }
    }

    public Integer getLoginTimeout() {
        int timeout;
        try {
            timeout = dataSource.getLoginTimeout();
        } catch (SQLException e) {
            timeout = 0;
        }
        return new Integer(timeout);
    }

    public void setLoginTimeout(Integer timeout) throws ResourceException {
        try {
            dataSource.setLoginTimeout(timeout.intValue());
        } catch (SQLException e) {
            throw new InvalidPropertyException(e.getMessage());
        }
    }

    /**
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractLocalDataSourceMCF) {
            AbstractLocalDataSourceMCF other = (AbstractLocalDataSourceMCF) obj;
            return this.dataSource.equals(other.dataSource);
        }
        return false;
    }

    public int hashCode() {
        return dataSource.hashCode();
    }

    public String toString() {
        return "AbstractXADataSourceMCF[" + dataSource + "]";
    }

}
