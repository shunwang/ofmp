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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 *
 *
 * @version $Revision: 1.1 $ $Date: 2008/11/13 03:25:10 $
 */
public class StatementHandle implements Statement {
    protected final ConnectionHandle c;
    protected final Statement s;

    public StatementHandle(ConnectionHandle c, Statement s) {
        this.c = c;
        this.s = s;
    }

    public Connection getConnection() {
        return c;
    }

    private ResultSet wrapResultSet(ResultSet rs) {
        return rs == null ? null : new ResultSetHandle(this, rs);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        try {
            return wrapResultSet(s.executeQuery(sql));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        try {
            return wrapResultSet(s.getGeneratedKeys());
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getResultSet() throws SQLException {
        try {
            return wrapResultSet(s.getResultSet());
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void addBatch(String sql) throws SQLException {
        try {
            s.addBatch(sql);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void cancel() throws SQLException {
        try {
            s.cancel();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void clearBatch() throws SQLException {
        try {
            s.clearBatch();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void clearWarnings() throws SQLException {
        try {
            s.clearWarnings();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void close() throws SQLException {
        try {
            s.close();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean execute(String sql) throws SQLException {
        try {
            return s.execute(sql);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        try {
            return s.execute(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean execute(String sql, int columnIndexes[]) throws SQLException {
        try {
            return s.execute(sql, columnIndexes);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean execute(String sql, String columnNames[]) throws SQLException {
        try {
            return s.execute(sql, columnNames);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int[] executeBatch() throws SQLException {
        try {
            return s.executeBatch();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int executeUpdate(String sql) throws SQLException {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        try {
            return s.executeUpdate(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int executeUpdate(String sql, int columnIndexes[]) throws SQLException {
        try {
            return s.executeUpdate(sql, columnIndexes);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int executeUpdate(String sql, String columnNames[]) throws SQLException {
        try {
            return s.executeUpdate(sql, columnNames);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getFetchDirection() throws SQLException {
        try {
            return s.getFetchDirection();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getFetchSize() throws SQLException {
        try {
            return s.getFetchSize();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxFieldSize() throws SQLException {
        try {
            return s.getMaxFieldSize();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxRows() throws SQLException {
        try {
            return s.getMaxRows();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean getMoreResults() throws SQLException {
        try {
            return s.getMoreResults();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean getMoreResults(int current) throws SQLException {
        try {
            return s.getMoreResults(current);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getQueryTimeout() throws SQLException {
        try {
            return s.getQueryTimeout();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getResultSetConcurrency() throws SQLException {
        try {
            return s.getResultSetConcurrency();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getResultSetHoldability() throws SQLException {
        try {
            return s.getResultSetHoldability();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getResultSetType() throws SQLException {
        try {
            return s.getResultSetType();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getUpdateCount() throws SQLException {
        try {
            return s.getUpdateCount();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public SQLWarning getWarnings() throws SQLException {
        try {
            return s.getWarnings();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setCursorName(String name) throws SQLException {
        try {
            s.setCursorName(name);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        try {
            s.setEscapeProcessing(enable);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setFetchDirection(int direction) throws SQLException {
        try {
            s.setFetchDirection(direction);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setFetchSize(int rows) throws SQLException {
        try {
            s.setFetchSize(rows);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setMaxFieldSize(int max) throws SQLException {
        try {
            s.setMaxFieldSize(max);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setMaxRows(int max) throws SQLException {
        try {
            s.setMaxRows(max);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        try {
            s.setQueryTimeout(seconds);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }
}
