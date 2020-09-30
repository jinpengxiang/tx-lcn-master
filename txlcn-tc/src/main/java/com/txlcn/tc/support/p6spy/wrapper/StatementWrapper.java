/**
 * P6Spy
 * <p>
 * Copyright (C) 2002 - 2018 P6Spy
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.txlcn.tc.support.p6spy.wrapper;

import com.txlcn.tc.support.p6spy.common.ResultSetInformation;
import com.txlcn.tc.support.p6spy.common.StatementInformation;
import com.txlcn.tc.support.p6spy.event.JdbcEventListener;

import java.sql.*;

/**
 * This implementation wraps a {@link StatementWrapper}  and notifies a {@link JdbcEventListener}
 * about certain method invocations.
 * <p>
 * This class implements the Wrapper or Decorator pattern. Methods default
 * to calling through to the wrapped request object.
 *
 * @see Statement
 */
public class StatementWrapper extends AbstractWrapper implements Statement {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final Statement delegate;
    protected final JdbcEventListener eventListener;
    private final StatementInformation statementInformation;

    public static Statement wrap(Statement delegate, StatementInformation statementInformation, JdbcEventListener eventListener) {
        if (delegate == null) {
            return null;
        }
        return new StatementWrapper(delegate, statementInformation, eventListener);
    }

    protected StatementWrapper(Statement delegate, StatementInformation statementInformation, JdbcEventListener eventListener) {
        super(delegate);
        this.delegate = delegate;
        this.eventListener = eventListener;
        this.statementInformation = statementInformation;
        this.statementInformation.setStatement(this);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        SQLException e = null;
        long start = System.nanoTime();
        try {
            return ResultSetWrapper.wrap(delegate.getResultSet(), new ResultSetInformation(statementInformation), eventListener);
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterGetResultSet(statementInformation, System.nanoTime() - start, e);
        }
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        try {
          return ResultSetWrapper.wrap(delegate.executeQuery(eventListener.onBeforeExecuteQuery(statementInformation, sql)), new ResultSetInformation(statementInformation), eventListener);
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecuteQuery(statementInformation, System.nanoTime() - start, sql, e);
        }
    }

    @Override
    public int[] executeBatch() throws SQLException {
        SQLException e = null;
        long start = System.nanoTime();
        int[] updateCounts = null;
        try {
            eventListener.onBeforeExecuteBatch(statementInformation);
            updateCounts = delegate.executeBatch();
            return updateCounts;
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecuteBatch(statementInformation, System.nanoTime() - start, updateCounts, e);
        }
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        try {
            return delegate.execute(eventListener.onBeforeExecute(statementInformation, sql));
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecute(statementInformation, System.nanoTime() - start, sql, e);
        }
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        try {
            return delegate.execute(eventListener.onBeforeExecute(statementInformation, sql), autoGeneratedKeys);
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecute(statementInformation, System.nanoTime() - start, sql, e);
        }
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        try {
            return delegate.execute( eventListener.onBeforeExecute(statementInformation, sql), columnIndexes);
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecute(statementInformation, System.nanoTime() - start, sql, e);
        }
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        try {
            return delegate.execute(eventListener.onBeforeExecute(statementInformation, sql), columnNames);
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecute(statementInformation, System.nanoTime() - start, sql, e);
        }
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        int rowCount = 0;
        try {
            rowCount = delegate.executeUpdate(eventListener.onBeforeExecuteUpdate(statementInformation, sql));
            return rowCount;
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecuteUpdate(statementInformation, System.nanoTime() - start, sql, rowCount, e);
        }
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        int rowCount = 0;
        try {
            rowCount = delegate.executeUpdate( eventListener.onBeforeExecuteUpdate(statementInformation, sql), autoGeneratedKeys);
            return rowCount;
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecuteUpdate(statementInformation, System.nanoTime() - start, sql, rowCount, e);
        }
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        int rowCount = 0;
        try {
            rowCount = delegate.executeUpdate( eventListener.onBeforeExecuteUpdate(statementInformation, sql), columnIndexes);
            return rowCount;
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecuteUpdate(statementInformation, System.nanoTime() - start, sql, rowCount, e);
        }
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        statementInformation.setStatementQuery(sql);
        SQLException e = null;
        long start = System.nanoTime();
        int rowCount = 0;
        try {
            rowCount = delegate.executeUpdate(eventListener.onBeforeExecuteUpdate(statementInformation, sql), columnNames);
            return rowCount;
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterExecuteUpdate(statementInformation, System.nanoTime() - start, sql, rowCount, e);
        }
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        if (statementInformation.getStatementQuery() == null) {
            statementInformation.setStatementQuery(sql);
        } else {
            statementInformation.setStatementQuery(sql + LINE_SEPARATOR + statementInformation.getStatementQuery());
        }

        SQLException e = null;
        long start = System.nanoTime();
        try {
            delegate.addBatch(eventListener.onBeforeAddBatch(statementInformation, sql));
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterAddBatch(statementInformation, System.nanoTime() - start, sql, e);
        }
    }

    @Override
    public void close() throws SQLException {
        SQLException e = null;
        try {
            delegate.close();
        } catch (SQLException sqle) {
            e = sqle;
            throw e;
        } finally {
            eventListener.onAfterStatementClose(statementInformation, e);
        }
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return delegate.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        delegate.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return delegate.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        delegate.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        delegate.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return delegate.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        delegate.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        delegate.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return delegate.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        delegate.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        delegate.setCursorName(name);
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return delegate.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return delegate.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        delegate.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return delegate.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        delegate.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return delegate.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return delegate.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return delegate.getResultSetType();
    }

    @Override
    public void clearBatch() throws SQLException {
        delegate.clearBatch();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return delegate.getConnection();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return delegate.getMoreResults(current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return delegate.getGeneratedKeys();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return delegate.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return delegate.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        delegate.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return delegate.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        delegate.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return delegate.isCloseOnCompletion();
    }

}
