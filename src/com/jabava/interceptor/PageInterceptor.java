package com.jabava.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jabava.utils.Page;

/**
 * mybatis分页拦截器
 * 
 * @author 郑长山
 * @datetime 2016年1月11日13:37:02
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	private static final Logger log = LoggerFactory
			.getLogger(PageInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final String DEFAULT_PAGE_SQLID = ".*Page$"; // 需要拦截的ID(正则匹配)

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		MetaObject metaStatementHandler = getOrignalStatementHandler((StatementHandler) invocation
				.getTarget());

		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
				.getValue("delegate.mappedStatement");
		// 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
		if (mappedStatement.getId().matches(DEFAULT_PAGE_SQLID)) {
			BoundSql boundSql = (BoundSql) metaStatementHandler
					.getValue("delegate.boundSql");
			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject == null) {
				throw new NullPointerException("parameterObject is null!");
			} else {
				Page<?> page = (Page<?>) metaStatementHandler
						.getValue("delegate.boundSql.parameterObject.page");
				if (page != null) {
					String sql = boundSql.getSql();
					// 重写sql
					String pageSql = buildPageSql(sql, page);

					metaStatementHandler.setValue("delegate.boundSql.sql",
							pageSql);
					// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
					metaStatementHandler.setValue("delegate.rowBounds.offset",
							RowBounds.NO_ROW_OFFSET);
					metaStatementHandler.setValue("delegate.rowBounds.limit",
							RowBounds.NO_ROW_LIMIT);
					Connection connection = (Connection) invocation.getArgs()[0];
					// 重设分页参数里的总页数等
					setPageParameter(sql, connection, mappedStatement,
							boundSql, page);
				}
			}
		}
		// 将执行权交给下一个拦截器
		return invocation.proceed();
	}

	private MetaObject getOrignalStatementHandler(
			StatementHandler statementHandler) {
		MetaObject metaStatementHandler = MetaObject.forObject(
				statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}

		return metaStatementHandler;
	}

	/**
	 * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
	 * <code>PageParameter</code>获得相关信息。
	 * 
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	private void setPageParameter(String sql, Connection connection,
			MappedStatement mappedStatement, BoundSql boundSql, Page<?> page) {
		// 仅在第一次请求时查询，提高效率
		String countSql = "select count(1) as total from (" + sql + ") tmp ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(countSql);
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),
					countSql, boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			// 解决foreach无法查询总数问题
			MetaObject countBSMeta = MetaObject.forObject(countBS,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
			MetaObject boundSqlMeta = MetaObject.forObject(boundSql,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
			countBSMeta.setValue("metaParameters",
					boundSqlMeta.getValue("metaParameters"));
			//
			setParameters(ps, mappedStatement, countBS,
					boundSql.getParameterObject());
			rs = ps.executeQuery();
			int recordsTotal = 0;
			if (rs.next()) {
				recordsTotal = rs.getInt(1);
			}
			page.setRecordsTotal(recordsTotal);
			page.setRecordsFiltered(recordsTotal);
		} catch (SQLException e) {
			log.error("Ignore this exception", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error("Ignore this exception", e);
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				log.error("Ignore this exception", e);
			}
		}

	}

	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}

	/**
	 * 重写sql，可根据不同数据库类型判断重写sql语句
	 * 
	 * @param sql
	 *            原始sql语句
	 * @param page
	 *            分页实体类
	 * @return
	 */
	private String buildPageSql(String sql, Page<?> page) {
		if (page != null) {
			StringBuilder pageSql = new StringBuilder();
			pageSql = buildPageSqlForMysql(sql, page);
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	/**
	 * mysql分页重写sql语句
	 * 
	 * @param sql
	 *            原始sql语句
	 * @param page
	 *            分页实体类
	 * @return
	 */
	public StringBuilder buildPageSqlForMysql(String sql, Page<?> page) {
		StringBuilder pageSql = new StringBuilder(64);
		pageSql.append(sql);
		pageSql.append(" limit " + Integer.toString(page.getStart()) + ","
				+ page.getLength());
		return pageSql;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
