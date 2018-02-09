/**
 * 
 */
package org.jade.core.iml.sql;

import java.util.List;

import org.jade.core.api.ISqlMaker;
import org.jade.core.api.SQL;
import org.jade.core.api.SQLParam;
import org.jade.core.domain.SQLParamContext;
import org.jade.core.domain.SQLParamNode;
import org.jade.util.StringUtil;

/**
 * @author Jack Lei
 * @Email 895896736@qq.com
 */

public class SqlMakerIml implements ISqlMaker {

	@Override
	public String make(SQLParamContext context) {
		try {
			Object[] methodParams = context.getMethodParams();
			SQLParam[] methodParamAnnotations = context.getMethodParamAnnotations();
			SQL sqlAnnotation = context.getSqlAnnotation();
			String sql = sqlAnnotation.val();
			if (methodParams == null) {
				return sql;
			}

			int methodParamAnnotationNum = 0;
			int methodParamNum = 0;
			for (int index = 0; index < methodParams.length; index++) {
				if (methodParams != null) {
					methodParamNum++;
				}
			}

			for (int index = 0; index < methodParamAnnotations.length; index++) {
				if (methodParamAnnotations[index] != null) {
					methodParamNum++;
				}
			}
			List<SQLParamNode> findParamNodeFromSql = StringUtil.findParamNodeFromSql(sql);
			if (findParamNodeFromSql.size() != methodParamNum) {
				
				return "";
			}

			
			return String.format(sql, methodParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

}
